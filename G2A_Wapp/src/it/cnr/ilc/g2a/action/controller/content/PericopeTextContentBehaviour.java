package it.cnr.ilc.g2a.action.controller.content;

import it.cnr.ilc.g2a.model.Factory;
import it.cnr.ilc.g2a.model.Reference;
import it.cnr.ilc.g2a.model.ReferenceSet;
import it.cnr.ilc.g2a.model.handler.EntityTypeHandler;
import it.cnr.ilc.g2a.model.text.PericopeText;
import it.cnr.ilc.g2a.model.text.RefTokenText;
import it.cnr.ilc.g2a.model.text.TokenText;
import it.cnr.ilc.g2a.model.xmlmapping.SequenceDocument;
import it.cnr.ilc.g2a.model.xmlmapping.ElementDocument.Element;
import it.cnr.ilc.g2a.model.xmlmapping.SequenceDocument.Sequence;
import it.cnr.ilc.g2a.utils.CophiSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;

public class PericopeTextContentBehaviour extends BaseContent implements ContentBehaviour<PericopeText> {

	private HashMap<String, TokenText> tokens = null;

	public PericopeTextContentBehaviour(SequenceDocument sd) {
		super(sd);
	}

	public PericopeTextContentBehaviour() {

	}

	@Override
	public HashMap<String,PericopeText> getValue() {

		Sequence s = getSequenceDocument().getSequence();
		HashMap<String,PericopeText> lop = Factory.getPericopeTextMap(); 


		if (EntityTypeHandler.isPericopes(s)) {
			Sequence[] pericopes = s.getSequenceArray();

			for (Sequence pericope : pericopes) {
				if (EntityTypeHandler.isPericope(pericope)) {
					PericopeText pt = Factory.getPericopeText();
					ArrayList<Reference> lort = Factory.getReferenceList();

					pt.setId(pericope.getId());
					pt.setInfo(pericope.getExtended());
					pt.setType(pericope.getType());
					pt.setClassname(pericope.getClassname());
					pt.setExtended(pericope.getExtended());

					Element[] refTokens =  pericope.getElementArray();
					for (Element refToken : refTokens) {
						RefTokenText rtt = Factory.getRefTokenTextInstance();
						rtt.setId(refToken.getId());
						rtt.setRef(refToken.getRef());
						rtt.setTok(getTokens().get(refToken.getRef()));
						rtt.setExtended(refToken.getExtended());
						lort.add(rtt);
					}
					/*
					 * Sort necessario affinche' le insert con la XQuery non necessitino la clusola before che in exist non c'è'
					 * http://cdi.uvm.edu/exist/update_ext.xml#d50e288
					 */

					Collections.sort(lort, CophiSort.TOKENREF_FROM_ORDER);
					pt.setValue(lort);
					lop.put(pt.getId(),pt);
				}
			}
		}
		return lop;
	}

	/**
	 * @return the tokens
	 */
	public HashMap<String, TokenText> getTokens() {
		return tokens;
	}

	/**
	 * @param tokens the tokens to set
	 */
	public void setTokens(HashMap<String, TokenText> tokens) {
		this.tokens = tokens;
	}


	@Override
	public Document createJDOM(PericopeText t) {

		return null;
	}

	@Override
	public SequenceDocument createSequenceDocument(List<ReferenceSet<Reference>> listOfPericope,
			String work) {
		if (null != listOfPericope) {

			setSequenceDocument(SequenceDocument.Factory.newInstance());

			Sequence pericopesRootNode = getSequenceDocument().addNewSequence();
			//Sequence pericopesRootNode = Sequence.Factory.newInstance();
			//getSequenceDocument().setSequence(pericopesRootNode);
			pericopesRootNode.setClassname("pericopes");
			pericopesRootNode.setExtended(work);//work = plotino o badaoui
			//	List<Sequence> sequenceArray = new ArrayList<Sequence>();
			for (ReferenceSet<Reference> pericopeText : listOfPericope) {

				//Sequence seq = createSequence(pericopeText);
				//sequenceArray.add(seq);

				Sequence seq = pericopesRootNode.addNewSequence();
				seq.setClassname("pericope");
				seq.setType(pericopeText.getType());
				seq.setExtended(((PericopeText)pericopeText).getInfo());
				seq.setId(pericopeText.getId());
				for (Reference refToken : pericopeText.getValue()) {
					Element e = seq.addNewElement();
					e.setId(refToken.getId());
					e.setRef(refToken.getRef());
					e.setClassname("refToken");
					e.setExtended(refToken.getExtended());
				}


			}
			/*for (int i=0; i < sequenceArray.size(); i++) {
				pericopesRootNode.addNewSequence();
				pericopesRootNode.setSequenceArray(i, sequenceArray.get(i));
			}*/

		}
		return getSequenceDocument();
	}

	public Sequence createSequence(ReferenceSet<Reference> ref) {

		Sequence seq = null;
		if (null != ref) {
			seq = Sequence.Factory.newInstance();
			seq.setClassname("pericope");
			seq.setType(ref.getType());
			seq.setExtended(((PericopeText)ref).getInfo());
			seq.setId(ref.getId());
			for (Reference refToken : ref.getValue()) {
				Element e = seq.addNewElement();
				e.setId(refToken.getId());
				e.setRef(refToken.getRef());
				e.setClassname("refToken");
				e.setExtended(refToken.getExtended());
			}
		}
		return seq;

	}	
}

