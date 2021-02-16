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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.faces.application.FacesMessage;

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
    private String password;
    private String role;
    private boolean loggedIn = false;
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

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("main/resources/users.properties");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getRemoteAddr();
        logger.info("Login request for username: " + username + " and password: " + password);

        try {
            while (reader.ready() && !loggedIn) {
                String line = reader.readLine();
                String dbUsername = line.split(":")[0];
                String dbPassword = line.split(":")[1];
                String dbRole = line.split(":")[2];

                if (dbUsername.equals(username) && dbPassword.equals(password)) {
                    loggedIn = true;
                    setRole(dbRole);
                } else {
                    loggedIn = false;
                }
            }
            reader.close();
            input.close();
            if (loggedIn) {
                logger.info("User '" + username + "' logged in from ip " + ipAddress);
                return navigationBean.redirectToWelcome();
            } else {
                FacesContext.getCurrentInstance().addMessage("errorMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: User/Password not found", ""));
                logger.error("User " + username + " not found!");
                return "error";
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        return "";
    }

    /**
     * Logout operation.
     *
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        logger.info("User '" + username + "' logged out");

        Utils.addInfoMessageToContext("Logout done", "loginMessage");
        return navigationBean.toLogin();
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
