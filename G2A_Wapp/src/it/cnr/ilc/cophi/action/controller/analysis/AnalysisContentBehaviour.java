package it.cnr.ilc.cophi.action.controller.analysis;

import it.cnr.ilc.cophi.action.controller.content.BaseContent;
import it.cnr.ilc.cophi.model.Factory;
import it.cnr.ilc.cophi.model.analysis.Analysis;
import it.cnr.ilc.cophi.model.analysis.AnalysisDB;
import it.cnr.ilc.cophi.model.handler.EntityTypeHandler;
import it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element;
import it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param;
import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument;
import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence;

import java.util.HashMap;

public class AnalysisContentBehaviour extends BaseContent implements AnalysisBehaviour {

	public AnalysisContentBehaviour(SequenceDocument sd) {
		super(sd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AnalysisDB getAnalysisDB() {

		Sequence s = getSequenceDocument().getSequence();
		AnalysisDB analysisDB = Factory.getAnalysisDBInstance();

		if (EntityTypeHandler.isAnalysisDB(s)) {
			Element[] listOfAnalysis = s.getElementArray();

			for (Element analysisEntry: listOfAnalysis) {

				if (EntityTypeHandler.isAnalysis(analysisEntry)) {

					Analysis analysis = Factory.getAnalysisInstance();

					Param[] features =  analysisEntry.getParamArray();
					HashMap<String, String> analysisProperties = Factory.getStringHashMapInstance();
					for (Param feat : features) {
						analysisProperties.put(feat.getName(), feat.getValue());
					}
					analysis.setBaseAnalysis(analysisProperties);
					analysisDB.insert(analysisEntry.getId(), analysis);
				}
			}
		}

		return analysisDB;

	}

}