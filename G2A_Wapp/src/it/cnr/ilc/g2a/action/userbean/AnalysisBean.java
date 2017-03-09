package it.cnr.ilc.g2a.action.userbean;

import java.util.List;

public class AnalysisBean {
	
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
	public AnalysisBean() {
		// TODO Auto-generated constructor stub
	}

	private String id;
	private String forma;
	private String pos;
	private String lemma;
	private String radice;
	
	private String format;
	private String post;
	private String lemmat;
	private String radicet;
	private String analysis;
	private List<SubAnalysisBean> subanalysis;
	
	/**
	 * @return the forma
	 */
	public String getForma() {
		return forma;
	}
	/**
	 * @param forma the forma to set
	 */
	public void setForma(String forma) {
		this.forma = forma;
	}
	/**
	 * @return the pos
	 */
	public String getPos() {
		return pos;
	}
	/**
	 * @param pos the pos to set
	 */
	public void setPos(String pos) {
		this.pos = pos;
	}
	/**
	 * @return the lemma
	 */
	public String getLemma() {
		return lemma;
	}
	/**
	 * @param lemma the lemma to set
	 */
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}
	/**
	 * @return the radice
	 */
	public String getRadice() {
		return radice;
	}
	/**
	 * @param radice the radice to set
	 */
	public void setRadice(String radice) {
		this.radice = radice;
	}
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * @return the post
	 */
	public String getPost() {
		return post;
	}
	/**
	 * @param post the post to set
	 */
	public void setPost(String post) {
		this.post = post;
	}
	/**
	 * @return the lemmat
	 */
	public String getLemmat() {
		return lemmat;
	}
	/**
	 * @param lemmat the lemmat to set
	 */
	public void setLemmat(String lemmat) {
		this.lemmat = lemmat;
	}
	/**
	 * @return the radicet
	 */
	public String getRadicet() {
		return radicet;
	}
	/**
	 * @param radicet the radicet to set
	 */
	public void setRadicet(String radicet) {
		this.radicet = radicet;
	}
	/**
	 * @return the analysis
	 */
	public String getAnalysis() {
		return analysis;
	}
	/**
	 * @param analysis the analysis to set
	 */
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	/**
	 * @return the subanalysis
	 */
	public List<SubAnalysisBean> getSubanalysis() {
		return subanalysis;
	}
	/**
	 * @param subanalysis the subanalysis to set
	 */
	public void setSubanalysis(List<SubAnalysisBean> subanalysis) {
		this.subanalysis = subanalysis;
	}
	
	

}
