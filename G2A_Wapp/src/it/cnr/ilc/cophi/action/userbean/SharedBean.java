package it.cnr.ilc.cophi.action.userbean;

import it.cnr.ilc.cophi.model.view.LinkViewEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="sharedBean")
@SessionScoped
public class SharedBean {

	
	private LinkViewEntity selectedLinkViewEntity = null;
	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean = null;
	
	private boolean loadNewAnalysis = true;
	
	private boolean onSearch = false;
	
	private String selectedMenuItem = "homeBody";
	/**
	 * @return the loginBean
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	/**
	 * @param loginBean the loginBean to set
	 */
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	/**
	 * @return the selectedViewLink
	 */
	public LinkViewEntity getSelectedLinkViewEntity() {
		return selectedLinkViewEntity;
	}

	/**
	 * @param selectedViewLink the selectedViewLink to set
	 */
	public void setSelectedLinkViewEntity(LinkViewEntity selectedViewLink) {
		this.selectedLinkViewEntity = selectedViewLink;
	}

	/**
	 * @return the loadNewAnalysis
	 */
	public boolean isLoadNewAnalysis() {
		return loadNewAnalysis;
	}

	/**
	 * @param loadNewAnalysis the loadNewAnalysis to set
	 */
	public void setLoadNewAnalysis(boolean loadNewAnalysis) {
		this.loadNewAnalysis = loadNewAnalysis;
	}

	/**
	 * @return the onSearch
	 */
	public boolean isOnSearch() {
		return onSearch;
	}

	/**
	 * @param onSearch the onSearch to set
	 */
	public void setOnSearch(boolean onSearch) {
		this.onSearch = onSearch;
	}

	/**
	 * @return the selectedMenuItem
	 */
	public String getSelectedMenuItem() {
		return selectedMenuItem;
	}

	/**
	 * @param selectedMenuItem the selectedMenuItem to set
	 */
	public void setSelectedMenuItem(String selectedMenuItem) {
		//System.err.println("setSelectedMenuItem("+ selectedMenuItem+ ")");
		this.selectedMenuItem = selectedMenuItem;
	}


	public String goToHomepage() {
		setSelectedMenuItem("homeBody");
	    return "Home.xhtml";
	}

}


