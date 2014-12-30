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
		this.selectedMenuItem = selectedMenuItem;
	}

	/**
	 * @param selectedMenuItem the selectedMenuItem to set
	 */
	/*
	public String setSelectedMenuItem(String selectedMenuItem) {

		String retPage;
		System.err.println("setSelectedMenuItem("+ selectedMenuItem+ ")");
		this.selectedMenuItem = selectedMenuItem;

		switch (selectedMenuItem) {
		case "teologia":
			retPage = "../G2A_Wapp/Home.xhtml?centerPage=teologia&amp;faces-redirect=true";
			break;
		case "risala":
			retPage = "Home.xhtml?centerPage=risala&amp;faces-redirect=true";
			break;
		case "detti":
			retPage = "Home.xhtml?centerPage=detti&amp;faces-redirect=true";
			break;
		case "metafisica":
			//retPage = "../Metafisica_Wapp/Home.xhtml?centerPage=metafisica&amp;faces-redirect=true";
			retPage = "http://www.google.it&amp;faces-redirect=true";
			break;

		case "userManual":
			retPage = "Home.xhtml?centerPage=userManual&amp;faces-redirect=true";
			break;
		case "tutorials":
			retPage = "Home.xhtml?centerPage=tutorials&amp;faces-redirect=true";
			break;
		case "sourceCode":
			retPage = "Home.xhtml?centerPage=sourceCode&amp;faces-redirect=true";
			break;
		case "copyright":
			retPage = "Home.xhtml?centerPage=copyright&amp;faces-redirect=true";
			break;

		default:
			retPage = "Home.xhtml";
			break;
		}
		System.err.println("return page: " + retPage);
		return retPage;
	}

*/
	public String goToHomepage() {
		setSelectedMenuItem("homeBody");
		return "Home.xhtml";
	}

}


