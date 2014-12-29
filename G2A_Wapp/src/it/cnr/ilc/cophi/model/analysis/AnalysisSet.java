package it.cnr.ilc.cophi.model.analysis;

import java.util.List;

public class AnalysisSet {

	private List<Analysis> listOfAnalysis;

	private Analysis bestAnalysis = null;
	
	/**
	 * @return the listOfAnalysis
	 */
	public List<Analysis> getListOfAnalysis() {
		return listOfAnalysis;
	}

	/**
	 * @param listOfAnalysis the listOfAnalysis to set
	 */
	public void setListOfAnalysis(List<Analysis> listOfAnalysis) {
		this.listOfAnalysis = listOfAnalysis;
	}

	/**
	 * @return the bestAnalysis
	 */
	public Analysis getBestAnalysis() {
		return bestAnalysis;
	}

	/**
	 * @param bestAnalysis the bestAnalysis to set
	 */
	public void setBestAnalysis(Analysis bestAnalysis) {
		this.bestAnalysis = bestAnalysis;
	}
	
}
