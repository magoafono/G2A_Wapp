package it.cnr.ilc.cophi.model.analysis;

import java.util.HashMap;

public class AnalysisDB {

	private HashMap<String, BaseAnalysis> analisysDB;

	/**
	 * @return the analisysDB
	 */
	public HashMap<String, BaseAnalysis> getAnalisysDB() {
		return analisysDB;
	}

	/**
	 * @param analisysDB the analisysDB to set
	 */
	public void setAnalisysDB(HashMap<String, BaseAnalysis> analisysDB) {
		this.analisysDB = analisysDB;
	}
	
	public void insert(String key, BaseAnalysis value) {
		
		if (null != analisysDB)
			analisysDB.put(key, value);
		else 
			System.err.println("analisysDB in null!");
	}
	
}
