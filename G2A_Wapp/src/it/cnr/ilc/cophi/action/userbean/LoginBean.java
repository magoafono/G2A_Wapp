package it.cnr.ilc.cophi.action.userbean;


import it.cnr.ilc.cophi.utils.Consts;
import it.cnr.ilc.cophi.utils.Utils;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.net.URLClassLoader;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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

	// Simple user database :)
	private static final String[] users = {"simone:simone:editor","ilcpisa:GApisa12:editor","elisa:agToyrbo:editor","cecilia:ASkl12:editor","cristina:zJn3gpWU:editor",
		"roche:xEN6e7irea:editor", "ouafae:riChZiZXQz:editor", "ge:5wYdX34t8i:editor", "torsten:MqjrW5VAG6:editor" ,"guest:guest:reader"};

	//	private static final String[] users = {"simone:simone"}; //in manutenzione

	private String username;
	private String password;
	private String role;
	private boolean loggedIn;
	private String arabicFontSize = Consts.ARABIC_FONT_SIZE;
	private String greekFontSize = Consts.GREEK_FONT_SIZE;

	@ManagedProperty(value="#{navigationBean}")
	private NavigationBean navigationBean;

	/**
	 * Login operation.
	 * @return
	 */
	public String doLogin() {
		// Get every user from our sample database :)
		for (String user: users) {
			String dbUsername = user.split(":")[0];
			String dbPassword = user.split(":")[1];
			String dbRole 	  = user.split(":")[2];

			// Successful login
			if (dbUsername.equals(username) && dbPassword.equals(password)) {
				loggedIn = true;
				setRole(dbRole);
				/*
				 *  XXX non funziona il getFlash...
				 * Utils.addInfoMessageToContext("Login done", "loginMessage");
				 * FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				 */
				//
				//Prendo l'indirizzo remoto del client
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				String ipAddress = request.getRemoteAddr();
				System.out.println(request.getRemoteAddr());
				
				logger.info("User '" + username + "' logged in from ip " + ipAddress);
				return navigationBean.redirectToWelcome();
			} 
		}
		logger.warn("User '" + username + "' with password '" + password + "' not logged in!!");

		// Set login ERROR
		/*
		 *  XXX non funziona il getFlash...
		 * Utils.addErrorMessageToContext("Login error! Check username and password", "loginMessage");
		 * FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		 */

		/*
		ClassLoader cl = ClassLoader.getSystemClassLoader();

		URL[] urls = ((URLClassLoader)cl).getURLs();

		for(URL url: urls){
			System.err.println(url.getFile());
		}
		*/
		return navigationBean.toLogin();

	}

	/**
	 * Logout operation.
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


	public void verifyUseLogin(ComponentSystemEvent event){
		if(!loggedIn){
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

	private void decreaseSize(int lang){
		switch (lang) {
		case Consts.GREEK:
			if (Utils.emToFloat(getGreekFontSize()) > 0.5) {
				setGreekFontSize((Utils.emToFloat(getGreekFontSize()) - 0.1 ) + "em");
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

	private void increaseSize(int lang){
		switch (lang) {
		case Consts.GREEK:
			if (Utils.emToFloat(getGreekFontSize()) < 4.0) {
				setGreekFontSize((Utils.emToFloat(getGreekFontSize()) + 0.1 ) + "em");
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

	private void resetSize (int lang){

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