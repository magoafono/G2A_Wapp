/**
 * 
 */
package it.cnr.ilc.g2a.model;

import java.util.List;

import it.cnr.ilc.g2a.model.analysis.AnalysisDelegate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author simone
 *
 */
public abstract class Token {

	private String id;
	private String refAnalysis;
	private AnalysisDelegate analyses;
	private static final Logger log = LogManager.getLogger("Token");

	public List<String> getAnalysisValuesByKey(String analysisType, String key) {

		List<String> ret = null;
		if (null != analyses) {
			ret = analyses.getValuesByKey(analysisType, key);
		} else {
			//FIXME ??
			log.warn("Token.getAnalysisValuesByKey(" + analysisType + ", " + key + "): Analysis not loaded!");
		}
		
		return ret;
	}
	
	public String getBestAnalysisValueByKey(String analysisType, String key) {
		
		String ret = null;
		
		if (null != analyses) {
			ret = analyses.getBestValueByKey(analysisType, key);
		} else {
			//FIXME ??
			log.warn("Token.getBestAnalysisValueByKey(" + analysisType + ", " + key + "): Analysis not loaded!");
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

