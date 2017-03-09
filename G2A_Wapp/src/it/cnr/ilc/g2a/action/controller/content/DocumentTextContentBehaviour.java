package it.cnr.ilc.g2a.action.controller.content;

import it.cnr.ilc.g2a.model.Factory;
import it.cnr.ilc.g2a.model.Reference;
import it.cnr.ilc.g2a.model.ReferenceSet;
import it.cnr.ilc.g2a.model.handler.EntityTypeHandler;
import it.cnr.ilc.g2a.model.text.TokenText;
import it.cnr.ilc.g2a.model.xmlmapping.SequenceDocument;
import it.cnr.ilc.g2a.model.xmlmapping.ElementDocument.Element;
import it.cnr.ilc.g2a.model.xmlmapping.ParamDocument.Param;
import it.cnr.ilc.g2a.model.xmlmapping.SequenceDocument.Sequence;

import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;


public class DocumentTextContentBehaviour extends BaseContent implements ContentBehaviour<TokenText> {

	/*
	 * Gestito dal Session Bean (fa la new di questa classe e passa nel costruttore il puntatore alla sequence document) 
	 */

	public DocumentTextContentBehaviour(SequenceDocument sd) {
		super(sd);
	}

	/**
	 * Mi restituisce tutto il documento come stringa (?)
	 */
	public HashMap<String,TokenText> getValue() {

		Sequence s = getSequenceDocument().getSequence();
		HashMap<String,TokenText> lot = Factory.getTokenTextMap(); 
		/*
		 * controllo che stia analizzando una sequenze di tipo (class) Text
		 * e quindi gli elements siano le foglie.
		 */
		if (EntityTypeHandler.isText(s)) { 
			Element[] ele = s.getElementArray();
			for (Element element : ele) {

				TokenText tok = Factory.getTokenTextInstance();
				tok.setId(element.getId());
				Param[] params = element.getParamArray();
				for (Param param : params) {
					if ("from".equals(param.getName())) {
						tok.setFrom(Integer.parseInt(param.getValue()));
					} else if ("to".equals(param.getName())) {
						tok.setTo(Integer.parseInt(param.getValue()));
					} else if ("refAnalysis".equals(param.getName())) {
						String analysis = param.getValue();
						tok.setRefAnalysis(analysis);
						tok.setAnalysis(null);
						//TODO fare una hash di analisi AnalysisHM (forse meglio caricamento lazy? e quindi non caricare subito le analisi)
						//   AnalysisHM.get(analysis);
					} else if ("string".equals(param.getName())) {
						tok.setValue(param.getValue());
					} else {
						//System.err.println("Unknown parameter? " + param.getName() + " : " + param.getValue());
					}
				}
				lot.put(tok.getId(), tok);
			}
		}
 		return lot;
	}

	@Override
	public Document createJDOM(TokenText t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SequenceDocument createSequenceDocument(List<ReferenceSet<Reference>> t,
			String work) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sequence createSequence(ReferenceSet<Reference> refs) {
		// TODO Auto-generated method stub
		return null;
	}



}
