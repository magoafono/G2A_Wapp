package it.cnr.ilc.cophi.action.userbean;

import java.io.IOException;

import javax.faces.context.FacesContext;

public class RemoteInvocation {

	private String remoteGreekTerm;
	private String remoteArabicTerm;
	private String remoteGreekPericopeId;
	private String remoteArabicPericopeId;
	private String remoteAction = "";

	/**
	 * @return the remoteGreekTerm
	 */
	public String getRemoteGreekTerm() {
		return remoteGreekTerm;
	}

	/**
	 * @param remoteGreekTerm the remoteGreekTerm to set
	 */
	public void setRemoteGreekTerm(String remoteGreekTerm) {
		this.remoteGreekTerm = remoteGreekTerm;
	}

	/**
	 * @return the remoteArabicTerm
	 */
	public String getRemoteArabicTerm() {
		return remoteArabicTerm;
	}

	/**
	 * @param remoteArabicTerm the remoteArabicTerm to set
	 */
	public void setRemoteArabicTerm(String remoteArabicTerm) {
		this.remoteArabicTerm = remoteArabicTerm;
	}

	
	/**
	 * @return the remoteGreekPericopeId
	 */
	public String getRemoteGreekPericopeId() {
		return remoteGreekPericopeId;
	}

	/**
	 * @param remoteGreekPericopeId the remoteGreekPericopeId to set
	 */
	public void setRemoteGreekPericopeId(String remoteGreekPericopeId) {
		this.remoteGreekPericopeId = remoteGreekPericopeId;
	}

	/**
	 * @return the remoteArabicPericopeId
	 */
	public String getRemoteArabicPericopeId() {
		return remoteArabicPericopeId;
	}

	/**
	 * @param remoteArabicPericopeId the remoteArabicPericopeId to set
	 */
	public void setRemoteArabicPericopeId(String remoteArabicPericopeId) {
		this.remoteArabicPericopeId = remoteArabicPericopeId;
	}

	
	public void action() {
        try {
        	switch (remoteAction) {
			case "search":
				FacesContext.getCurrentInstance().getExternalContext().redirect("Search.xhtml");
				break;

			case "comments":
			default:
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("Comment.xhtml");
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
