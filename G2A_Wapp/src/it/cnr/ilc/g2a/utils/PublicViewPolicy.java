package it.cnr.ilc.g2a.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/** Public pages are explicit; never use a request value as a Facelets path. */
public final class PublicViewPolicy {
    private static final Set<String> HOME_SECTIONS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            "homeBody", "teologia", "detti", "risala", "metafisica",
            "userManual", "tutorials", "sourceCode", "copyright")));
    private static final Set<String> DESCRIPTIONS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            "/descr/alQahira1.xhtml", "/descr/alQahira2.xhtml", "/descr/alQahira3.xhtml", "/descr/alQahirah.xhtml",
            "/descr/berlin1.xhtml", "/descr/istanbul1.xhtml", "/descr/istanbul2.xhtml", "/descr/istanbul.xhtml",
            "/descr/leiden1.xhtml", "/descr/marsh.xhtml", "/descr/paris1.xhtml", "/descr/paris2.xhtml")));
    private static final Set<String> STATIC_EXTENSIONS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            "css", "js", "png", "jpg", "jpeg", "gif", "ico", "svg", "woff", "woff2", "ttf", "otf", "eot",
            "pdf", "txt", "html", "mp4", "webm", "swf")));

    private PublicViewPolicy() { }

    public static String homeSection(String value) {
        if (value == null || value.isEmpty()) return "homeBody";
        if (!HOME_SECTIONS.contains(value)) throw new IllegalArgumentException("Unsupported public Home section");
        return value;
    }

    public static boolean isPublicRequest(String path, String method) {
        if ("/Home.xhtml".equals(path) || "/Login.xhtml".equals(path)) {
            return "GET".equals(method) || "HEAD".equals(method) || "POST".equals(method);
        }
        if (!"GET".equals(method) && !"HEAD".equals(method)) return false;
        if ("/favicon.ico".equals(path) || DESCRIPTIONS.contains(path)) return true;
        if (path.startsWith("/javax.faces.resource/")) {
            // DynamicContent/downloads must retain authentication, unlike CSS/JS for Login.
            return !path.substring("/javax.faces.resource/".length()).startsWith("dynamiccontent.");
        }
        if (path.startsWith("/resources/")) {
            int dot = path.lastIndexOf('.');
            return dot >= 0 && STATIC_EXTENSIONS.contains(path.substring(dot + 1));
        }
        return false;
    }
}
