package it.cnr.ilc.g2a.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Browser origin defense in depth; JSF ViewState and authorization remain mandatory. */
public final class CsrfFilter implements Filter {
    private static final int MAX_HEADER_LENGTH = 8192;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String method = req.getMethod();
        // GET/HEAD/OPTIONS must not perform persistent business writes.
        if ("GET".equals(method) || "HEAD".equals(method) || "OPTIONS".equals(method) || hasSameOrigin(req)) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Cache-Control", "no-store");
        res.sendError(HttpServletResponse.SC_FORBIDDEN, "Request origin not allowed");
    }

    private static boolean hasSameOrigin(HttpServletRequest request) {
        try {
            String fetchSite = singleHeader(request, "Sec-Fetch-Site");
            if (fetchSite != null && !"same-origin".equals(fetchSite)
                    && !"same-site".equals(fetchSite) && !"none".equals(fetchSite)) return false;
            String origin = singleHeader(request, "Origin");
            // Origin present but null/malformed/mismatching must not fall back to Referer.
            String source = origin != null ? origin : singleHeader(request, "Referer");
            if (source == null) return false;
            URI uri = new URI(source);
            if (uri.isOpaque() || uri.getHost() == null || uri.getRawUserInfo() != null
                    || uri.getRawFragment() != null || uri.getRawAuthority().endsWith(":")) return false;
            if (origin != null && ((uri.getRawPath() != null && !uri.getRawPath().isEmpty())
                    || uri.getRawQuery() != null)) return false;
            String scheme = uri.getScheme();
            String targetScheme = request.getScheme();
            if (!isHttp(scheme) || !isHttp(targetScheme) || !scheme.equalsIgnoreCase(targetScheme)) return false;
            // Container target only: never parse arbitrary client forwarding headers.
            if (!uri.getHost().equalsIgnoreCase(request.getServerName())) return false;
            int sourcePort = uri.getPort() == -1 ? defaultPort(scheme) : uri.getPort();
            int targetPort = request.getServerPort();
            return sourcePort > 0 && sourcePort <= 65535 && sourcePort == targetPort;
        } catch (IllegalArgumentException | URISyntaxException malformed) {
            return false;
        }
    }

    private static String singleHeader(HttpServletRequest request, String name) {
        Enumeration<String> values = request.getHeaders(name);
        if (values == null || !values.hasMoreElements()) return null;
        String value = values.nextElement();
        if (values.hasMoreElements() || value == null || value.isEmpty() || value.length() > MAX_HEADER_LENGTH)
            throw new IllegalArgumentException("Invalid origin metadata");
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c <= 0x20 || c >= 0x7f) throw new IllegalArgumentException("Invalid origin metadata");
        }
        return value;
    }

    private static boolean isHttp(String scheme) {
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }

    private static int defaultPort(String scheme) {
        return "https".equalsIgnoreCase(scheme) ? 443 : 80;
    }

    @Override public void init(FilterConfig config) throws ServletException { }
    @Override public void destroy() { }
}
