package it.cnr.ilc.cophi.model.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Memorizza le varie analisi relative ad un Token. La hashmap ha per chiave il nome dell'analisi (es. morfo, metric ecc.)
 * e come value la lista delle potenziali letture (ambigue) di quelle analisi per quel token.
 * 
 * @author simone
 *
 */
public class AnalysisDelegate {

	private HashMap<String, AnalysisSet>  tokenAnalyses;

	/**
	 * @return the tokenAnalyses
	 */
	public HashMap<String, AnalysisSet> getTokenAnalyses() {
		return tokenAnalyses;
	}

	/**
	 * @param tokenAnalyses the tokenAnalyses to set
	 */
	public void setTokenAnalyses(HashMap<String, AnalysisSet> tokenAnalyses) {
		this.tokenAnalyses = tokenAnalyses;
	}

	/**
	 * 
	 * @param analysisType tipo della analisi: morpho, metric, ecc.
	 * @param key nome dell'attibuto dipendente da analysis (es. lemma di morpho ecc.)
	 * @return Lista dei valori associati alla feature specificata in <em>key</em> di quella analisi <em>analysis</em>
	 * (es. i lemmi di key)
	 */
	public List<String> getValuesByKey(String analysisType, String key) {
		//TODO FIXME controllare che key sia un valore ammissibile per analysis (qui o in Token?)

		List<String> res = null; 
		HashMap<String, AnalysisSet> tokenAnalyses = getTokenAnalyses();
		if (null != tokenAnalyses) {
			AnalysisSet la = tokenAnalyses.get(analysisType);
			if (null != la) {
				res = new ArrayList<String>();
				for (Analysis ana : la.getListOfAnalysis()) {
					res.add(ana.getBaseAnalysis().get(key));
				}
			}
		}

		return res;
	}
	
	/**
	 * 
	 * @param analysisType
	 * @param key
	 * @return
	 */
	public String getBestValueByKey(String analysisType, String key) {
		String res = null; 
		HashMap<String, AnalysisSet> tokenAnalyses = getTokenAnalyses();
		if (null != tokenAnalyses) {
			AnalysisSet la = tokenAnalyses.get(analysisType);
			if (null != la) {
				Analysis ana = la.getBestAnalysis();
				res = ana.getBaseAnalysis().get(key);
			}
		}
		
		return res;
	}
}
