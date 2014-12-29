package it.cnr.ilc.cophi.action.controller.content;

import it.cnr.ilc.cophi.model.Reference;
import it.cnr.ilc.cophi.model.ReferenceSet;
import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument;
import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence;

import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;

public interface ContentBehaviour<T> {

	public HashMap<String,T> getValue();
	
	public Document createJDOM (T t);
	
	public SequenceDocument createSequenceDocument(List<ReferenceSet<Reference>> t, String work);	
	
	public Sequence createSequence(ReferenceSet<Reference> refs);


}
