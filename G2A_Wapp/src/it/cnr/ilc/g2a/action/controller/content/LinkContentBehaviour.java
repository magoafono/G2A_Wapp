package it.cnr.ilc.g2a.action.controller.content;

import it.cnr.ilc.g2a.model.Factory;
import it.cnr.ilc.g2a.model.Link;
import it.cnr.ilc.g2a.model.RefPericope;
import it.cnr.ilc.g2a.model.Reference;
import it.cnr.ilc.g2a.model.ReferenceSet;
import it.cnr.ilc.g2a.model.handler.EntityTypeHandler;
import it.cnr.ilc.g2a.model.text.PericopeText;
import it.cnr.ilc.g2a.model.text.RefPericopeText;
import it.cnr.ilc.gtoa.model.xmlmapping.SequenceDocument;
import it.cnr.ilc.gtoa.model.xmlmapping.ElementDocument.Element;
import it.cnr.ilc.gtoa.model.xmlmapping.SequenceDocument.Sequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;

public class LinkContentBehaviour extends BaseContent implements
ContentBehaviour<Link<RefPericope>> {

	private HashMap<String, PericopeText> pericopes = null;

	public LinkContentBehaviour(SequenceDocument sd) {
		super(sd);
	}

	@Override
	public HashMap<String,Link<RefPericope>> getValue() {

		Sequence s = getSequenceDocument().getSequence();
		HashMap<String,Link<RefPericope>> hol = Factory.getLinkMap(); 

		if (EntityTypeHandler.isLinks(s)) {
			Sequence[] links = s.getSequenceArray();

			for (Sequence slnk : links) {

				if (EntityTypeHandler.isLink(slnk)) {

					Link<RefPericope> link = Factory.getLink();
					ArrayList<RefPericope> lorp = Factory.getRefPericopeList();

					link.setId(slnk.getId());
					link.setType(slnk.getExtended());

					Element[] refPericopes =  slnk.getElementArray();
					/*
					 * es: <element id="ref_43190.0406" ref="43190.0406" classname="hiddenRefPericope" extended="grc"/>
					 */
					for (Element refPericope : refPericopes) {
						RefPericopeText rp = Factory.getRefPericopeTextInstance(refPericope.getExtended());
						rp.setId(refPericope.getId());
						rp.setRef(refPericope.getRef());
						rp.setPericope(pericopes.get(refPericope.getRef()));
						rp.setExtended(refPericope.getExtended()); 
						rp.setClassname(refPericope.getClassname());

						lorp.add(rp);
					}
					link.setValue(lorp);
					hol.put(link.getId(),link);
				}
			}
		}
		return hol;
	}

	/**
	 * @return the pericopes
	 */
	public HashMap<String, PericopeText> getPericopes() {
		return pericopes;
	}

	/**
	 * @param pericopes the pericopes to set
	 */
	public void setPericopes(HashMap<String, PericopeText> pericopes) {
		this.pericopes = pericopes;
	}

	@Override
	public Document createJDOM(Link<RefPericope> t) {
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
