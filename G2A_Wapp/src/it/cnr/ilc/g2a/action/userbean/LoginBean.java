package it.cnr.ilc.g2a.action.userbean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.cnr.ilc.g2a.utils.Consts;
import it.cnr.ilc.g2a.utils.Utils;
import it.cnr.ilc.g2a.utils.CredentialStore;
import it.cnr.ilc.g2a.utils.LoginProtection;
import it.cnr.ilc.g2a.utils.LoginRateLimiter;
import java.util.Arrays;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.event.ComponentSystemEvent;

/**
 * Simple login bean.
 *
 * @author itcuties
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4497884335833238273L;

    private static final Logger logger = LogManager.getLogger("LoginBean");

    private String username;
    private transient String password;
    private volatile String role;
    private volatile boolean loggedIn = false;
    private String arabicFontSize = Consts.ARABIC_FONT_SIZE;
    private String greekFontSize = Consts.GREEK_FONT_SIZE;

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    /**
     * Login operation.
     *
     * @return
     * @throws java.io.IOException
     * @throws javax.security.auth.login.LoginException
     */
    public String doLogin() throws IOException {

        // Always verify this attempt, even if the same session was already authenticated.
        loggedIn = false;
        role = null;
        char[] submittedPassword = password == null ? null : password.toCharArray();
        String submittedUser = username;
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipAddress = request.getRemoteAddr();
            String matchedRole;
            try (LoginRateLimiter.Permit permit = LoginProtection.begin(request)) {
                if (!permit.isAllowed()) {
                    ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
                    external.setResponseStatus(429);
                    external.setResponseHeader("Retry-After", Integer.toString(permit.getRetrySeconds()));
                    external.setResponseHeader("Cache-Control", "no-store");
                    FacesContext.getCurrentInstance().addMessage("errorMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Too many login attempts. Try again later.", ""));
                    return "error";
                }
                logger.info("Login request from ip {}", ipAddress);
                matchedRole = CredentialStore.authenticate(request.getContextPath(), submittedUser, submittedPassword);
                if (matchedRole == null) permit.recordFailure();
            }
            if (matchedRole != null) {
                // Retain JSF/session state but invalidate the pre-login identifier.
                // If rotation fails, do not establish an authenticated principal.
                request.getSession(true);
                request.changeSessionId();
                role = matchedRole;
                loggedIn = true;
                logger.info("User '{}' logged in from ip {}", logUsername(), ipAddress);
                return navigationBean.redirectToWelcome();
            }
        } catch (IOException unavailable) {
            logger.error("Credentials source unavailable");
        } finally {
            if (submittedPassword != null) Arrays.fill(submittedPassword, '\u0000');
            password = null;
        }
        FacesContext.getCurrentInstance().addMessage("errorMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: User/Password not found", ""));
        logger.error("User '{}' not found", logUsername());
        return "error";
    }

    /**
     * Logout operation.
     *
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;
        role = null;
        password = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        logger.info("User '{}' logged out", logUsername());

        Utils.addInfoMessageToContext("Logout done", "loginMessage");
        return navigationBean.toLogin();
    }

    private String logUsername() {
        if (username == null) return "<empty>";
        return username.substring(0, Math.min(username.length(), 128)).replaceAll("[\\p{Cntrl}\\p{Cf}]", "_");
    }

    public void verifyUseLogin(ComponentSystemEvent event) {
        if (!loggedIn) {
            //System.err.println("User is NOT logged in");
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + navigationBean.redirectToLogin());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            //System.err.println("User is logged in");
        }
    }

    // ------------------------------
    // Getters & Setters 
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

    /**
     * @return the arabicFontSize
     */
    public String getArabicFontSize() {
        return arabicFontSize;
    }

    /**
     * @param arabicFontSize the arabicFontSize to set
     */
    public void setArabicFontSize(String arabicFontSize) {
        this.arabicFontSize = arabicFontSize;
    }

    /**
     * @return the greekFontSize
     */
    public String getGreekFontSize() {
        return greekFontSize;
    }

    /**
     * @param greekFontSize the greekFontSize to set
     */
    public void setGreekFontSize(String greekFontSize) {
        this.greekFontSize = greekFontSize;
    }

    public void decreaseArabicSize() {
        decreaseSize(Consts.ARABIC);
    }

    public void decreaseGreekSize() {
        decreaseSize(Consts.GREEK);
    }

    private void decreaseSize(int lang) {
        switch (lang) {
            case Consts.GREEK:
                if (Utils.emToFloat(getGreekFontSize()) > 0.5) {
                    setGreekFontSize((Utils.emToFloat(getGreekFontSize()) - 0.1) + "em");
                }
                break;

            case Consts.ARABIC:
                if (Utils.emToFloat(getArabicFontSize()) > 0.5) {
                    setArabicFontSize((Utils.emToFloat(getArabicFontSize()) - 0.1) + "em");
                }
                break;

            default:
                break;
        }

    }

    public void increaseArabicSize() {
        increaseSize(Consts.ARABIC);
    }

    public void increaseGreekSize() {
        increaseSize(Consts.GREEK);
    }

    private void increaseSize(int lang) {
        switch (lang) {
            case Consts.GREEK:
                if (Utils.emToFloat(getGreekFontSize()) < 4.0) {
                    setGreekFontSize((Utils.emToFloat(getGreekFontSize()) + 0.1) + "em");
                }
                break;

            case Consts.ARABIC:
                if (Utils.emToFloat(getArabicFontSize()) < 4.0) {
                    setArabicFontSize((Utils.emToFloat(getArabicFontSize()) + 0.1) + "em");
                }
                break;

            default:
                break;
        }

    }

    public void resetArabicSize() {
        resetSize(Consts.ARABIC);
    }

    public void resetGreekSize() {
        resetSize(Consts.GREEK);
    }

    private void resetSize(int lang) {

        switch (lang) {
            case Consts.GREEK:
                setGreekFontSize(Consts.GREEK_FONT_SIZE);
                break;

            case Consts.ARABIC:
                setArabicFontSize(Consts.ARABIC_FONT_SIZE);
                break;

            default:
                break;
        }
    }

}
