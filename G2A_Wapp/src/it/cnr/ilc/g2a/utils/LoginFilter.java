package it.cnr.ilc.g2a.utils;

import it.cnr.ilc.g2a.action.userbean.LoginBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Authenticate before JSF restore/decode/actions, not merely before rendering. */
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // Container-decoded mapping path: do not apply a second URL decode.
        String path = req.getServletPath();
        if (req.getPathInfo() != null) path += req.getPathInfo();

        if ("/Home.xhtml".equals(path)) {
            String[] pages = req.getParameterValues("centerPage");
            try {
                if (pages != null) {
                    if (pages.length != 1) throw new IllegalArgumentException();
                    PublicViewPolicy.homeSection(pages[0]);
                }
            } catch (IllegalArgumentException invalid) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported public Home section");
                return;
            }
        }
        if (PublicViewPolicy.isPublicRequest(path, req.getMethod())) {
            if ("POST".equals(req.getMethod()) && ("/Home.xhtml".equals(path) || "/Login.xhtml".equals(path))) {
                HttpSession existing = req.getSession(false);
                Object caller = existing == null ? null : existing.getAttribute("loginBean");
                // Do not throttle logout/navigation of an established session.
                // Every doLogin still passes the authoritative KDF gate below JSF.
                boolean authenticated = caller instanceof LoginBean && ((LoginBean) caller).isLoggedIn();
                if (!authenticated && !LoginProtection.filterRequest(req, res)) return;
            }
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = req.getSession(false);
        Object value = session == null ? null : session.getAttribute("loginBean");
        LoginBean user = value instanceof LoginBean ? (LoginBean) value : null;
        res.setHeader("Cache-Control", "no-store");
        if (user == null || !user.isLoggedIn()) {
            redirectToHome(req, res);
            return;
        }
        if ("/ManagePericopes.xhtml".equals(path) && !Authorization.canEdit(user)) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Editing role required");
            return;
        }
        chain.doFilter(request, response);
    }

    private static void redirectToHome(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String target = req.getContextPath() + "/Home.xhtml";
        if ("partial/ajax".equals(req.getHeader("Faces-Request"))
                || "true".equals(req.getParameter("javax.faces.partial.ajax"))) {
            res.setContentType("text/xml");
            res.setCharacterEncoding("UTF-8");
            String escaped = target.replace("&", "&amp;").replace("\"", "&quot;")
                    .replace("<", "&lt;").replace(">", "&gt;");
            res.getWriter().write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<partial-response><redirect url=\"" + escaped + "\"/></partial-response>");
        } else {
            res.sendRedirect(target);
        }
    }

    @Override public void init(FilterConfig config) throws ServletException { }
    @Override public void destroy() { }
}
