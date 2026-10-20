package it.cnr.ilc.g2a.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Per WAR: no username/account lockout and no trust in client forwarding headers. */
public final class LoginProtection {
    private static final String CHARGED = LoginProtection.class.getName() + ".charged";
    private static final LoginRateLimiter LIMITER = new LoginRateLimiter(
            60, 8, 60_000, 60_000, 300_000, 4096, 4,
            () -> TimeUnit.NANOSECONDS.toMillis(System.nanoTime()));
    private LoginProtection() { }

    public static boolean filterRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Boolean.TRUE.equals(request.getAttribute(CHARGED))) return true;
        LoginRateLimiter.Decision decision = LIMITER.request(request.getRemoteAddr());
        if (!decision.isAllowed()) {
            response.setStatus(429);
            response.setHeader("Retry-After", Integer.toString(decision.getRetrySeconds()));
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            response.getWriter().write("Too many login attempts. Try again later.");
            return false;
        }
        request.setAttribute(CHARGED, Boolean.TRUE);
        return true;
    }

    public static LoginRateLimiter.Permit begin(HttpServletRequest request) {
        boolean charged = Boolean.TRUE.equals(request.getAttribute(CHARGED));
        request.removeAttribute(CHARGED); // A second login action in one request needs its own budget.
        return LIMITER.begin(request.getRemoteAddr(), charged);
    }
}
