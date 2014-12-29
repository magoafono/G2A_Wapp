/**
 * 
 */
package it.cnr.ilc.cophi.model;

import it.cnr.ilc.cophi.model.analysis.AnalysisDelegate;

import java.util.List;

/**
 * @author simone
 *
 */
public abstract class Token {

	private String id;
	private String refAnalysis;
	private AnalysisDelegate analyses;

	public List<String> getAnalysisValuesByKey(String analysisType, String key) {

		List<String> ret = null;
		if (null != analyses) {
			ret = analyses.getValuesByKey(analysisType, key);
		} else {
			//FIXME ??
			System.err.println("Token.getAnalysisValuesByKey(" + analysisType + ", " + key + "): Analysis not loaded!");
		}
		
		return ret;
	}
	
	public String getBestAnalysisValueByKey(String analysisType, String key) {
		
		String ret = null;
		
		if (null != analyses) {
			ret = analyses.getBestValueByKey(analysisType, key);
		} else {
			//FIXME ??
			System.err.println("Token.getBestAnalysisValueByKey(" + analysisType + ", " + key + "): Analysis not loaded!");
		}
		
		return ret;
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
	 * @return the refAnalysis
	 */
	public String getRefAnalysis() {
		return refAnalysis;
	}
	/**
	 * @param refAnalysis the refAnalysis to set
	 */
	public void setRefAnalysis(String refAnalysis) {
		this.refAnalysis = refAnalysis;
	}
	/**
	 * @return the analysis
	 */
	public AnalysisDelegate getAnalysis() {
		return analyses;
	}
	/**
	 * @param analysis the analysis to set
	 */
	public void setAnalysis(AnalysisDelegate analysis) {
		this.analyses = analysis;
	}

}

