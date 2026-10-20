package it.cnr.ilc.g2a.utils;

import it.cnr.ilc.g2a.action.userbean.LoginBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/** Resolve the caller per invocation: never cache a session principal in application scope. */
public final class Authorization {
    private Authorization() { }

    public static boolean canEdit(LoginBean user) {
        return user != null && user.isLoggedIn()
                && ("editor".equals(user.getRole()) || "admin".equals(user.getRole()));
    }

    public static void requireEditor() {
        FacesContext faces = FacesContext.getCurrentInstance();
        HttpSession session = faces == null ? null : (HttpSession) faces.getExternalContext().getSession(false);
        Object value = session == null ? null : session.getAttribute("loginBean");
        if (!(value instanceof LoginBean) || !canEdit((LoginBean) value)) {
            throw new SecurityException("An authenticated editing role is required");
        }
    }
}
