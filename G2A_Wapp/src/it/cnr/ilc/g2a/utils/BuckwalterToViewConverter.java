package it.cnr.ilc.g2a.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.cnr.ilc.g2a.action.userbean.AnalysisBean;
import it.cnr.ilc.g2a.action.userbean.Pair;
import it.cnr.ilc.g2a.action.userbean.SubAnalysisBean;

public class BuckwalterToViewConverter {

	private static final Logger logger ;
	private static HashMap<String, String> buckwalter2ArabicPos;
	
	static {
		buckwalter2ArabicPos = Utils.loadFile2HashMap("/it/cnr/ilc/cophi/resources/buckwalterPos2Arabic.csv");
		logger = LogManager.getLogger("BuckwalterToViewConverter");
	}

	/**
	 * Crea le subanalysis per una data analisis morfologica.
	 * Partendo dalla analisi completa (come da XLSX) la "unstemma" la "splitta" in sottoanalisis e la traslittera in arabo
	 * @param sequenceBean
	 */
	public static void convert(AnalysisBean sequenceBean) {

		String unstemmedAnalysis = BuckwalterUnstemmer.unstemm(sequenceBean.getAnalysis());

		List<Pair<String, String>> parsedAnalysis = BuckwalterAnalysisParser.parse(unstemmedAnalysis);
		List<SubAnalysisBean> subanalysis = new ArrayList<SubAnalysisBean>();
		for (Pair<String, String> pair : parsedAnalysis) {

			SubAnalysisBean sab = new SubAnalysisBean();
			sab.setPost(pair.getRight());
			StringBuilder arabicPos = new StringBuilder();
			for (String f: pair.getRight().split("\n")) {
				if (! buckwalter2ArabicPos.containsKey(f)) {
					logger.error("No POS (" + f + ") in WebContent/resources/buckwalterPos2Arabic.csv");
				} else {
					if (arabicPos.length() > 0) {
						arabicPos.append(" ");
					}
					arabicPos.append(buckwalter2ArabicPos.get(f));
				}
			}
			sab.setPos(arabicPos.toString());
			sab.setWordt(pair.getLeft());
			sab.setWord(BuckwalterArabicConverter.buckwalterToUnicode(pair.getLeft()));
			subanalysis.add(sab);

		}
		sequenceBean.setForma(BuckwalterArabicConverter.buckwalterToUnicode(sequenceBean.getFormat()));
		sequenceBean.setLemma(BuckwalterArabicConverter.buckwalterToUnicode(sequenceBean.getLemmat()));
		sequenceBean.setRadice(BuckwalterArabicConverter.buckwalterToUnicode(sequenceBean.getRadicet()));

		sequenceBean.setSubanalysis(subanalysis);

	}
}
