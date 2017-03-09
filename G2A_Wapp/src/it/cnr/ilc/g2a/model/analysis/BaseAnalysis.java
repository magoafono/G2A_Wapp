package it.cnr.ilc.g2a.model.analysis;

import java.util.HashMap;

public abstract class BaseAnalysis {

	private HashMap<String, String> baseAnalysis;

	/**
	 * @return the analysis
	 */
	public HashMap<String, String> getBaseAnalysis() {
		return baseAnalysis;
	}

	/**
	 * @param baseAnalysis the analysis to set
	 */
	public void setBaseAnalysis(HashMap<String, String> baseAnalysis) {
		this.baseAnalysis = baseAnalysis;
	} 

}
