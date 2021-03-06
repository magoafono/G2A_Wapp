package it.cnr.ilc.g2a.action.controller.analysis;

import it.cnr.ilc.g2a.action.controller.resource.ContextResource;
import it.cnr.ilc.g2a.action.controller.resource.XMLResourceBehaviour;
import it.cnr.ilc.g2a.model.Factory;
import it.cnr.ilc.g2a.model.Token;
import it.cnr.ilc.g2a.model.analysis.Analysis;
import it.cnr.ilc.g2a.model.analysis.AnalysisDB;
import it.cnr.ilc.g2a.model.analysis.AnalysisDelegate;
import it.cnr.ilc.g2a.model.analysis.AnalysisSet;
import it.cnr.ilc.g2a.model.analysis.BaseAnalysis;
import it.cnr.ilc.gtoa.model.xmlmapping.SequenceDocument;
import it.cnr.ilc.g2a.utils.Consts;
import it.cnr.ilc.g2a.utils.MessageProvider;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class AnalysisHandler {

	private static AnalysisHandler instance;
	
	// HM<refAnalysis,Delegate>
	private HashMap<String, AnalysisDelegate> refToAnalyses;

	private AnalysisHandler () {
		init();
	}

	private void init() {

		MessageProvider mp = new MessageProvider();
		/*
		 * Carico i database 
		 */
		ContextResource cr = Factory.getInstanceContextResource();
		cr.setResourceBehaviourType(new XMLResourceBehaviour());
		cr.loadResources(mp.getValue(Consts.CONFIGNAME, "db_tokenToAnalysis_name"));

		SequenceDocument refAnalysisSeqDoc = cr.retrieveContent(mp.getValue(Consts.CONFIGNAME, "analysis_name")); //analysis.xml

		cr.loadResources(mp.getValue(Consts.CONFIGNAME, "db_analyses_name"));
		
		HashMap<String, SequenceDocument> allCollectionContent = cr.retriveAllCollectionContent();
		//creare gli oggetti AnalysisDelegate

		HashMap<String, AnalysisDB> allAnalysisDB = new HashMap<String, AnalysisDB>();
		ContextAnalysis ca = Factory.getInstanceContextAnalysis();
		AnalysisContentBehaviour acb = new AnalysisContentBehaviour(null);
		
		for (Map.Entry<String, SequenceDocument> element : allCollectionContent.entrySet()) {

			acb.setSequenceDocument(element.getValue());
			ca.setAnalysisType(acb);
			allAnalysisDB.put(element.getKey(),ca.getAnalysisDB());
		}
		
		
		/*
		 * Carico i riferimenti dei token alle analisi
		 * Ci deve essere un refAnalysisDB per ogni documento della collezione
		 */
		ca.setAnalysisType(new AnalysisContentBehaviour(refAnalysisSeqDoc));
		AnalysisDB refAnalysisDB = ca.getAnalysisDB();
		
		for (Map.Entry<String, BaseAnalysis> refAnalysisToken : refAnalysisDB.getAnalisysDB().entrySet()) {
			
			BaseAnalysis ba = refAnalysisToken.getValue();
			AnalysisDelegate ad = Factory.getInstanceAnalysisDelegate();
			
			for (Map.Entry<String, String> refAnalysis : ba.getBaseAnalysis().entrySet() ) {
				String typeOfAnalysis = refAnalysis.getValue();
				String ref  = refAnalysis.getKey();
				Analysis analysis = (Analysis) allAnalysisDB.get(typeOfAnalysis).getAnalisysDB().get(ref);
				if (ad.getTokenAnalyses().containsKey(typeOfAnalysis)) {
					AnalysisSet as = ad.getTokenAnalyses().get(typeOfAnalysis);
					as.getListOfAnalysis().add(analysis);
				} else {
					AnalysisSet as = Factory.getInstanceAnalysisSet();
					as.getListOfAnalysis().add(analysis);
					ad.getTokenAnalyses().put(typeOfAnalysis, as);
				}
			}
			refToAnalyses.put(refAnalysisToken.getKey(), ad);
		}
		

	}
	
	/**
	 * 
	 * @return
	 */
	public static AnalysisHandler getInstanceeee() {
		if (instance == null) {
			synchronized(AnalysisHandler.class) {
				if (instance == null) {
					instance = new AnalysisHandler();
				}
			}
		}

		return instance;
	}


	/**
	 * Per un token ricerca l'analisi da associargli (fatta dall'application bean)
	 * @param tok
	 */
	public void loadAnalyses(Token tok) {
		
		String keyAnalysisDelegate = tok.getRefAnalysis();
		tok.setAnalysis(refToAnalyses.get(keyAnalysisDelegate));
	}
	

	/**
	 * @return the refToAnalyses
	 */
	public HashMap<String, AnalysisDelegate> getRefToAnalyses() {
		return refToAnalyses;
	}

	/**
	 * @param refToAnalyses the refToAnalyses to set
	 */
	public void setRefToAnalyses(HashMap<String, AnalysisDelegate> refToAnalyses) {
		this.refToAnalyses = refToAnalyses;
	} 
}
