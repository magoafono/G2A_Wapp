package it.cnr.ilc.g2a.utils;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/** Versioned PBKDF2 credential format; no plaintext/fast-hash compatibility. */
public final class PasswordHashes {
    public static final int ITERATIONS = 600_000;
    public static final int MAX_PASSWORD_CHARS = 4096;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String PREFIX = "pbkdf2-sha256";
    private static final String DUMMY = PREFIX + "$600000$AAAAAAAAAAAAAAAAAAAAAA==$AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=";

    private PasswordHashes() { }

    public static String create(char[] password) {
        if (password == null || password.length == 0 || password.length > MAX_PASSWORD_CHARS) {
            throw new IllegalArgumentException("Password length outside supported bounds");
        }
        byte[] salt = new byte[16]; RANDOM.nextBytes(salt);
        byte[] hash = derive(password, salt, ITERATIONS);
        try {
            return PREFIX + "$" + ITERATIONS + "$" + Base64.getEncoder().encodeToString(salt)
                    + "$" + Base64.getEncoder().encodeToString(hash);
        } finally { Arrays.fill(hash, (byte) 0); }
    }

    private static byte[] derive(char[] password, byte[] salt, int iterations) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, 256);
        try { return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded(); }
        catch (GeneralSecurityException unavailable) { throw new IllegalStateException("Password KDF unavailable", unavailable); }
        finally { spec.clearPassword(); }
    }

    private static byte[][] parse(String encoded) {
        if (encoded == null || encoded.length() > 180) throw new IllegalArgumentException("Invalid credential hash");
        String[] parts = encoded.split("\\$", -1);
        if (parts.length != 4 || !PREFIX.equals(parts[0]) || !parts[1].matches("[1-9][0-9]{5,6}")) {
            throw new IllegalArgumentException("Unsupported credential hash");
        }
        int rounds = Integer.parseInt(parts[1]);
        if (rounds < ITERATIONS || rounds > 2_000_000) throw new IllegalArgumentException("Unsupported KDF cost");
        byte[] salt = Base64.getDecoder().decode(parts[2]), hash = Base64.getDecoder().decode(parts[3]);
        if (salt.length != 16 || hash.length != 32
                || !Base64.getEncoder().encodeToString(salt).equals(parts[2])
                || !Base64.getEncoder().encodeToString(hash).equals(parts[3])) {
            throw new IllegalArgumentException("Invalid credential hash encoding");
        }
        return new byte[][] {salt, hash};
    }

    public static void validate(String encoded) { parse(encoded); }

    public static boolean verify(char[] password, String encoded) {
        byte[][] record = parse(encoded);
        if (password == null || password.length == 0 || password.length > MAX_PASSWORD_CHARS) return false;
        int rounds = Integer.parseInt(encoded.split("\\$", -1)[1]);
        byte[] actual = derive(password, record[0], rounds);
        try { return MessageDigest.isEqual(record[1], actual); }
        finally { Arrays.fill(actual, (byte) 0); Arrays.fill(record[1], (byte) 0); }
    }

    /** Avoid a cheap fast path for an unknown username; this does not replace rate limiting. */
    public static void dummyVerify(char[] password) { verify(password, DUMMY); }
}
