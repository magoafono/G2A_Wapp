package it.cnr.ilc.g2a.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/** External per-context credentials. Configuration/file errors never fall back to a WAR resource. */
public final class CredentialStore {
    public static final String DIRECTORY_PROPERTY = "g2a.auth.directory";
    public static final String DIRECTORY_ENV = "G2A_AUTH_DIRECTORY";
    private static final long MAX_FILE_BYTES = 1024 * 1024;
    private CredentialStore() { }

    public static Path fileFor(String contextPath) throws IOException {
        String directory = System.getProperty(DIRECTORY_PROPERTY);
        if (directory == null) directory = System.getenv(DIRECTORY_ENV);
        if (directory == null || directory.trim().isEmpty()) throw new IOException("External authentication directory required");
        if (contextPath == null || !contextPath.matches("/[A-Za-z0-9][A-Za-z0-9_-]{0,63}")) {
            throw new IOException("Unsupported authentication context");
        }
        Path root = Paths.get(directory);
        if (!root.isAbsolute()) throw new IOException("Authentication directory must be absolute");
        return root.resolve(contextPath.substring(1) + ".users");
    }

    private static Map<String, String[]> records(Reader source) throws IOException {
        BufferedReader reader = source instanceof BufferedReader ? (BufferedReader) source : new BufferedReader(source);
        Map<String, String[]> result = new HashMap<>();
        String line; int total = 0;
        while ((line = reader.readLine()) != null) {
            total += line.length() + 1;
            if (total > MAX_FILE_BYTES || line.length() > 1024) throw new IOException("Credential store exceeds bounds");
            if (line.trim().isEmpty() || line.trim().startsWith("#")) continue;
            String[] fields = line.split(":", -1);
            if (fields.length != 3 || fields[0].isEmpty() || fields[0].length() > 128
                    || fields[0].matches(".*[\\p{Cntrl}].*")
                    || !("reader".equals(fields[2]) || "editor".equals(fields[2]) || "admin".equals(fields[2]))) {
                throw new IOException("Invalid credential record");
            }
            try { PasswordHashes.validate(fields[1]); }
            catch (IllegalArgumentException invalid) { throw new IOException("Invalid credential hash"); }
            if (result.put(fields[0], fields) != null) throw new IOException("Duplicate credential identity");
        }
        if (result.isEmpty()) throw new IOException("Empty credential store");
        return result;
    }

    public static String verify(Reader source, String username, char[] password) throws IOException {
        Map<String, String[]> entries = records(source);
        String[] entry = entries.get(username);
        if (entry == null) { PasswordHashes.dummyVerify(password); return null; }
        return PasswordHashes.verify(password, entry[1]) ? entry[2] : null;
    }

    private static BufferedReader open(String contextPath) throws IOException {
        Path file = fileFor(contextPath);
        if (!Files.isRegularFile(file) || Files.size(file) > MAX_FILE_BYTES) {
            throw new IOException("Credential store unavailable or exceeds bounds");
        }
        return Files.newBufferedReader(file, StandardCharsets.UTF_8);
    }

    public static void validateContext(String contextPath) throws IOException {
        try (BufferedReader source = open(contextPath)) { records(source); }
    }

    public static String authenticate(String contextPath, String username, char[] password) throws IOException {
        try (BufferedReader source = open(contextPath)) { return verify(source, username, password); }
    }
}
