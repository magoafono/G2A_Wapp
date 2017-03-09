package it.cnr.ilc.g2a.model.view;

import java.util.List;

public class ResultViewEntity {

	private String arPericopeId;
	private String arPericopeInfo;
	private String grPericopeId;
	private String grPericopeInfo;
	private String linkId;
	private String noOfCOmments; //numero di commenti associati a quella coppia di pericopi

	private List<TokenViewEntity> greekTVE = null;
	private List<TokenViewEntity> arabicTVE = null;

	/**
	 * @return the linkId
	 */
	public String getLinkId() {
		return linkId;
	}
	/**
	 * @param linkId the linkId to set
	 */
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	/**
	 * @return the noOfCOmments
	 */
	public String getNoOfCOmments() {
		return noOfCOmments;
	}
	/**
	 * @param noOfCOmments the noOfCOmments to set
	 */
	public void setNoOfCOmments(String noOfCOmments) {
		this.noOfCOmments = noOfCOmments;
	}
	/**
	 * @return the arPericopeId
	 */
	public String getArPericopeId() {
		return arPericopeId;
	}
	/**
	 * @param arPericopeId the arPericopeId to set
	 */
	public void setArPericopeId(String arPericopeId) {
		this.arPericopeId = arPericopeId;
	}
	/**
	 * @return the arPericopeInfo
	 */
	public String getArPericopeInfo() {
		return arPericopeInfo;
	}
	/**
	 * @param arPericopeInfo the arPericopeInfo to set
	 */
	public void setArPericopeInfo(String arPericopeInfo) {
		this.arPericopeInfo = arPericopeInfo;
	}
	/**
	 * @return the grPericopeId
	 */
	public String getGrPericopeId() {
		return grPericopeId;
	}
	/**
	 * @param grPericopeId the grPericopeId to set
	 */
	public void setGrPericopeId(String grPericopeId) {
		this.grPericopeId = grPericopeId;
	}
	/**
	 * @return the grPericopeInfo
	 */
	public String getGrPericopeInfo() {
		return grPericopeInfo;
	}
	/**
	 * @param grPericopeInfo the grPericopeInfo to set
	 */
	public void setGrPericopeInfo(String grPericopeInfo) {
		this.grPericopeInfo = grPericopeInfo;
	}
	/**
	 * @return the greekTVE
	 */
	public List<TokenViewEntity> getGreekTVE() {
		return greekTVE;
	}
	/**
	 * @param greekTVE the greekTVE to set
	 */
	public void setGreekTVE(List<TokenViewEntity> greekTVE) {
		this.greekTVE = greekTVE;
	}
	/**
	 * @return the arabicTVE
	 */
	public List<TokenViewEntity> getArabicTVE() {
		return arabicTVE;
	}
	/**
	 * @param arabicTVE the arabicTVE to set
	 */
	public void setArabicTVE(List<TokenViewEntity> arabicTVE) {
		this.arabicTVE = arabicTVE;
	}

	
	
}
