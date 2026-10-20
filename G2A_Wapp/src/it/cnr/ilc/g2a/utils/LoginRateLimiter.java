package it.cnr.ilc.g2a.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.function.LongSupplier;

/** Bounded, in-memory per-peer budgets and concurrent password-verification cap. */
public final class LoginRateLimiter {
    private final int attemptsPerWindow, failuresPerWindow, maxPeers;
    private final long windowMillis, cooldownMillis, idleMillis;
    private final LongSupplier clock;
    private final Semaphore verificationSlots;
    private final Map<String, Entry> peers = new HashMap<>();
    private int housekeeping;

    private static final class Entry {
        long windowStart, failureStart, lastSeen, blockedUntil;
        int attempts, failures;
        boolean blocked;
        Entry(long now) { windowStart = failureStart = lastSeen = now; }
    }
    public static final class Decision {
        private final boolean allowed;
        private final int retrySeconds;
        private Decision(boolean allowed, int retrySeconds) { this.allowed = allowed; this.retrySeconds = retrySeconds; }
        public boolean isAllowed() { return allowed; }
        public int getRetrySeconds() { return retrySeconds; }
    }

    public LoginRateLimiter(int attempts, int failures, long windowMillis, long cooldownMillis,
                            long idleMillis, int maxPeers, int concurrent, LongSupplier clock) {
        if (attempts < 1 || failures < 1 || windowMillis < 1 || cooldownMillis < 1
                || idleMillis < Math.max(windowMillis, cooldownMillis) || maxPeers < 1 || concurrent < 1 || clock == null) {
            throw new IllegalArgumentException("Invalid login protection limits");
        }
        attemptsPerWindow = attempts; failuresPerWindow = failures;
        this.windowMillis = windowMillis; this.cooldownMillis = cooldownMillis;
        this.idleMillis = idleMillis; this.maxPeers = maxPeers; this.clock = clock;
        verificationSlots = new Semaphore(concurrent);
    }

    private static String key(String peer) {
        return peer == null || peer.isEmpty() || peer.length() > 64 ? "<unknown-peer>" : peer;
    }
    private static int seconds(long millis) { return (int) Math.max(1L, Math.min(Integer.MAX_VALUE, (millis + 999) / 1000)); }
    private void cleanup(long now) {
        Iterator<Entry> entries = peers.values().iterator();
        while (entries.hasNext()) if (now - entries.next().lastSeen >= idleMillis) entries.remove();
    }
    private synchronized Decision check(String peer, boolean charge) {
        long now = clock.getAsLong();
        if ((++housekeeping & 63) == 0) cleanup(now);
        String id = key(peer); Entry e = peers.get(id);
        if (e == null) {
            if (peers.size() >= maxPeers) cleanup(now);
            if (peers.size() >= maxPeers) return new Decision(false, seconds(windowMillis));
            e = new Entry(now); peers.put(id, e);
        }
        e.lastSeen = now;
        if (e.blocked) {
            if (now - e.blockedUntil < 0) return new Decision(false, seconds(e.blockedUntil - now));
            e.blocked = false; e.failures = 0; e.failureStart = now;
        }
        if (now - e.windowStart >= windowMillis) { e.windowStart = now; e.attempts = 0; }
        if (now - e.failureStart >= windowMillis) { e.failureStart = now; e.failures = 0; }
        if (charge && e.attempts >= attemptsPerWindow) return new Decision(false, seconds(windowMillis - (now - e.windowStart)));
        if (charge) e.attempts++;
        return new Decision(true, 0);
    }
    public Decision request(String peer) { return check(peer, true); }

    private synchronized void failure(String peer) {
        long now = clock.getAsLong(); Entry e = peers.get(key(peer));
        if (e == null || e.blocked) return;
        e.lastSeen = now;
        if (now - e.failureStart >= windowMillis) { e.failureStart = now; e.failures = 0; }
        if (++e.failures >= failuresPerWindow) { e.blocked = true; e.blockedUntil = now + cooldownMillis; }
    }

    public Permit begin(String peer, boolean requestAlreadyCharged) {
        Decision decision = check(peer, !requestAlreadyCharged);
        if (!decision.isAllowed()) return new Permit(peer, false, decision.getRetrySeconds());
        if (!verificationSlots.tryAcquire()) return new Permit(peer, false, 1);
        return new Permit(peer, true, 0);
    }

    public final class Permit implements AutoCloseable {
        private final String peer;
        private final boolean allowed;
        private final int retrySeconds;
        private boolean closed, recorded;
        private Permit(String peer, boolean allowed, int retrySeconds) {
            this.peer = peer; this.allowed = allowed; this.retrySeconds = retrySeconds;
        }
        public boolean isAllowed() { return allowed; }
        public int getRetrySeconds() { return retrySeconds; }
        public synchronized void recordFailure() {
            if (allowed && !closed && !recorded) { recorded = true; failure(peer); }
        }
        @Override public synchronized void close() {
            if (!closed) { closed = true; if (allowed) verificationSlots.release(); }
        }
    }
}
