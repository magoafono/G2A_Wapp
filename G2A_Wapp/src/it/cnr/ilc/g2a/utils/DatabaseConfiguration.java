package it.cnr.ilc.g2a.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/** Private immutable configuration per WAR classloader, never a Faces resource bundle. */
public final class DatabaseConfiguration {
    public static final String DIRECTORY_PROPERTY = "g2a.db.directory";
    public static final String DIRECTORY_ENV = "G2A_DB_DIRECTORY";
    private static volatile DatabaseConfiguration active;
    private final String contextPath;
    private final Map<String, String> settings;

    private DatabaseConfiguration(String contextPath, Properties properties) {
        this.contextPath = contextPath;
        Map<String,String> values = new HashMap<>();
        for (String key : properties.stringPropertyNames()) values.put(key, properties.getProperty(key));
        settings = Collections.unmodifiableMap(values);
    }

    public String get(String key) {
        String value = settings.get(key);
        if (value == null) throw new IllegalStateException("Missing database configuration key");
        return value; // Never log this value, including in exceptions/toString.
    }

    private static Properties uniqueProperties() {
        return new Properties() {
            @Override public synchronized Object put(Object key, Object value) {
                if (containsKey(key)) throw new IllegalArgumentException("Duplicate database configuration key");
                return super.put(key, value);
            }
        };
    }

    /** Pure loader for isolated validation; does not publish state or open database connections. */
    public static DatabaseConfiguration load(Path directory, String actualContext, String expectedContext,
                                             String expectedCollection) throws IOException {
        if (expectedCollection == null || !expectedCollection.matches("/[A-Za-z0-9_-]+")
                || actualContext == null || !actualContext.matches("/[A-Za-z0-9][A-Za-z0-9_-]{0,63}")
                || !actualContext.equals(expectedContext)) throw new IOException("Database application identity mismatch");
        if (directory == null || !directory.isAbsolute()) throw new IOException("Absolute external database directory required");
        Path file = directory.resolve(actualContext.substring(1) + ".properties");
        if (!Files.isRegularFile(file) || Files.size(file) > 65536) throw new IOException("Database configuration unavailable or exceeds bounds");
        Properties properties = uniqueProperties();
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            properties.load(reader);
        } catch (IllegalArgumentException malformed) { throw new IOException("Invalid database properties format"); }
        for (String key : new String[]{"db_driver", "db_server", "db_port", "db_protocol", "db_root_name", "db_name",
                "db_login", "db_password", "db_arabic_doc_path", "db_greek_doc_path", "db_comment_path", "db_link_path",
                "db_tokenToAnalysis_name", "db_analyses_name", "arabic_work_name", "greek_work_name", "analysis_name"}) {
            String value = properties.getProperty(key);
            if (value == null || value.isEmpty() || (!"db_password".equals(key) && value.contains("${"))) throw new IOException("Missing or unresolved database setting");
        }
        if (!expectedCollection.equals(properties.getProperty("db_name"))) throw new IOException("Database collection/profile mismatch");
        if (!"org.exist.xmldb.DatabaseImpl".equals(properties.getProperty("db_driver"))) throw new IOException("Unsupported remote database driver");
        try {
            int port = Integer.parseInt(properties.getProperty("db_port"));
            if (port < 1 || port > 65535) throw new IllegalArgumentException();
            String protocol = properties.getProperty("db_protocol");
            if (!protocol.startsWith("/") || !protocol.endsWith("/xmlrpc") || protocol.contains("..")
                    || protocol.contains("?") || protocol.contains("#") || protocol.contains(";") || protocol.contains("%")) throw new IllegalArgumentException();
            URI uri = new URI("http://" + properties.getProperty("db_server") + ":" + port + protocol);
            if (uri.getHost() == null || uri.getUserInfo() != null || uri.getQuery() != null || uri.getFragment() != null
                    || !protocol.equals(uri.getRawPath())) throw new IllegalArgumentException();
        } catch (IllegalArgumentException | URISyntaxException invalid) { throw new IOException("Invalid XML-RPC endpoint configuration"); }
        for (String key : new String[]{"db_root_name", "db_arabic_doc_path", "db_greek_doc_path", "db_comment_path", "db_link_path"}) {
            String path = properties.getProperty(key);
            if (!path.startsWith("/") || path.contains("..") || path.contains("\\") || path.contains("?") || path.contains("#")) {
                throw new IOException("Invalid database collection path");
            }
        }
        return new DatabaseConfiguration(actualContext, properties);
    }

    public static synchronized DatabaseConfiguration initialize(ServletContext context) throws IOException {
        if (active != null) {
            if (!active.contextPath.equals(context.getContextPath())) throw new IOException("Database configuration cannot be shared between application contexts");
            return active;
        }
        String configured = System.getProperty(DIRECTORY_PROPERTY);
        if (configured == null) configured = System.getenv(DIRECTORY_ENV);
        if (configured == null || configured.trim().isEmpty()) throw new IOException("External database directory required");
        Properties identity = new Properties();
        try (InputStream input = DatabaseConfiguration.class.getResourceAsStream("/main/resources/application.properties")) {
            if (input == null) throw new IOException("Packaged application identity missing");
            identity.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        }
        String expectedContext = identity.getProperty("application.context");
        String expectedCollection = identity.getProperty("database.collection");
        if (expectedContext == null || expectedCollection == null || expectedContext.contains("${")
                || expectedCollection.contains("${")) throw new IOException("Invalid packaged application identity");
        active = load(Paths.get(configured), context.getContextPath(), expectedContext, expectedCollection);
        return active;
    }

    public static DatabaseConfiguration current() {
        DatabaseConfiguration loaded = active;
        if (loaded != null) return loaded;
        // Mojarra's SCI may start eager JSF beans before explicit listeners run.
        // Its InitFacesContext supplies the actual ServletContext: load once,
        // with the same validation, rather than fall back to packaged secrets.
        FacesContext faces = FacesContext.getCurrentInstance();
        Object context = faces == null ? null : faces.getExternalContext().getContext();
        if (!(context instanceof ServletContext)) throw new IllegalStateException("Database configuration not initialized");
        try { return initialize((ServletContext) context); }
        catch (IOException invalid) { throw new IllegalStateException("Valid external database configuration required", invalid); }
    }
}
