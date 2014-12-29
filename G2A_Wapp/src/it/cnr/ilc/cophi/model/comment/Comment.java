package it.cnr.ilc.cophi.model.comment;

import it.cnr.ilc.cophi.model.view.SelectedTextBoundaries;

public class Comment {

	
	private final int SNIPPET_LENGTH = 15;
	
	private String type;
	private String commentId;
	private String linkId;
	private SelectedTextBoundaries greekSelectedBound;
	private SelectedTextBoundaries arabicSelectedBound;
	private String commentText;
	private String editor;
	private String versionId;
	private String data;
	private String xmlFileName;

	public Comment (){
		
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the commentId
	 */
	public String getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

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
	 * @return the greekSelectedBound
	 */
	public SelectedTextBoundaries getGreekSelectedBound() {
		return greekSelectedBound;
	}

	/**
	 * @param greekSelectedBound the greekSelectedBound to set
	 */
	public void setGreekSelectedBound(SelectedTextBoundaries greekSelectedBound) {
		this.greekSelectedBound = greekSelectedBound;
	}

	/**
	 * @return the arabicSelectedBound
	 */
	public SelectedTextBoundaries getArabicSelectedBound() {
		return arabicSelectedBound;
	}

	/**
	 * @param arabicSelectedBound the arabicSelectedBound to set
	 */
	public void setArabicSelectedBound(SelectedTextBoundaries arabicSelectedBound) {
		this.arabicSelectedBound = arabicSelectedBound;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the editor
	 */
	public String getEditor() {
		return editor;
	}

	/**
	 * @param editor the editor to set
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}

	/**
	 * @return the versionId
	 */
	public String getVersionId() {
		return versionId;
	}

	/**
	 * @param versionId the versionId to set
	 */
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the sNIPPET_LENGTH
	 */
	public int getSNIPPET_LENGTH() {
		return SNIPPET_LENGTH;
	}

	public String getXmlFileName() {
		return xmlFileName;
	}

	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public String getSnippet(){
		
		String snippet = getCommentText();
		snippet = snippet.replaceAll("\\<.+?\\>", "");
		if(snippet.length() > getSNIPPET_LENGTH()){
			snippet = snippet.substring(0, getSNIPPET_LENGTH()) + "...";
		}
		
		return  snippet;
	}
	
	
}
