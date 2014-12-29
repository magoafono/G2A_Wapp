package it.cnr.ilc.cophi.action.controller.analysis;

import it.cnr.ilc.cophi.model.analysis.AnalysisDB;

public class ContextAnalysis {

	private AnalysisDB value;

	private AnalysisBehaviour analysisType = null;
	
	/**
	 * @return the value
	 */
	public AnalysisDB getAnalysisDB() {
		value = analysisType.getAnalysisDB();
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(AnalysisDB value) {
		this.value = value;
	}

	/**
	 * @return the analysisType
	 */
	public AnalysisBehaviour getAnalysisType() {
		return analysisType;
	}

	/**
	 * @param analysisType the analysisType to set
	 */
	public void setAnalysisType(AnalysisBehaviour analysisType) {
		this.analysisType = analysisType;
	}

	

}
