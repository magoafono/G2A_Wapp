package it.cnr.ilc.g2a.model.view;

public class TokenViewEntity {

	private String id;
	private String text;
	private String pericopeId;
	private String pericopeInfo;
	private String linkId;
	private TokenViewEntity prec = null;
	private TokenViewEntity succ = null;
	private int from;
	private int to;
	private int color;
	private String style = null;
	private int hisLoc;
	private int index; //posizione del token all'interno della Lista di TokenView
	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the pericopeId
	 */
	public String getPericopeId() {
		return pericopeId;
	}
	/**
	 * @param pericopeId the pericopeId to set
	 */
	public void setPericopeId(String pericopeId) {
//		setColor(Integer.parseInt(pericopeId.replace(".", "")));
		this.pericopeId = pericopeId;
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
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(int from) {
		this.from = from;
	}
	/**
	 * @return the prec
	 */
	public TokenViewEntity getPrec() {
		return prec;
	}
	/**
	 * @param prec the prec to set
	 */
	public void setPrec(TokenViewEntity prec) {
		this.prec = prec;
	}
	/**
	 * @return the succ
	 */
	public TokenViewEntity getSucc() {
		return succ;
	}
	/**
	 * @param succ the succ to set
	 */
	public void setSucc(TokenViewEntity succ) {
		this.succ = succ;
	}
	/**
	 * @return the pericopeInfo
	 */
	public String getPericopeInfo() {
		return pericopeInfo;
	}
	/**
	 * @param pericopeInfo the pericopeInfo to set
	 */
	public void setPericopeInfo(String pericopeInfo) {
		this.pericopeInfo = pericopeInfo;
	}
	/**
	 * @return the to
	 */
	public int getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(int to) {
		this.to = to;
	}
	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}
	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	/**
	 * @return the hisPage
	 */
	public int getHisLoc() {
		return hisLoc;
	}
	/**
	 * @param hisPage the hisPage to set
	 */
	public void setHisLoc(int hisLoc) {
		this.hisLoc = hisLoc;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
}
