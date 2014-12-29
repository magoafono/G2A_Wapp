package it.cnr.ilc.cophi.action.controller.analysis;

import it.cnr.ilc.cophi.action.controller.content.BaseContent;
import it.cnr.ilc.cophi.model.Factory;
import it.cnr.ilc.cophi.model.analysis.AnalysisDB;
import it.cnr.ilc.cophi.model.analysis.RefAnalysis;
import it.cnr.ilc.cophi.model.handler.EntityTypeHandler;
import it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element;
import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument;
import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence;

import java.util.HashMap;

public class RefAnalysisContentBehaviour extends BaseContent implements AnalysisBehaviour {

	public RefAnalysisContentBehaviour(SequenceDocument sd) {
		super(sd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AnalysisDB getAnalysisDB() {

		Sequence s = getSequenceDocument().getSequence();
		AnalysisDB analysisDB = Factory.getAnalysisDBInstance();

		if (EntityTypeHandler.isTokenToAnalysis(s)) {
			Sequence[] listOfTokenAnalysis = s.getSequenceArray();

			for (Sequence tokenAnalysisSeq : listOfTokenAnalysis) {

				if (EntityTypeHandler.isTokenAnalyses(tokenAnalysisSeq)) {

					RefAnalysis analysis = Factory.getRefAnalysisInstance();

					Element[] features =  tokenAnalysisSeq.getElementArray();
					HashMap<String, String> refTokenAnalysis = Factory.getStringHashMapInstance();
					for (Element feat : features) {
						refTokenAnalysis.put(feat.getRef(), feat.getExtended());
					}
					analysis.setBaseAnalysis(refTokenAnalysis);
					analysisDB.insert(tokenAnalysisSeq.getId(), analysis);
				}
			}
		}

		return analysisDB;

	}

}
