package it.cnr.ilc.g2a.action.controller.content;

import it.cnr.ilc.g2a.model.Reference;
import it.cnr.ilc.g2a.model.ReferenceSet;
import it.cnr.ilc.gtoa.model.xmlmapping.SequenceDocument;
import it.cnr.ilc.gtoa.model.xmlmapping.SequenceDocument.Sequence;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.xmlbeans.XmlOptions;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ContextContent<T> {

	private HashMap<String,T> value;

	private ContentBehaviour contentType = null;

	private Document document = null;

	private String xmlString = null; 
	/**
	 * @return the value
	 */
	public HashMap<String,T> getValue() {
		value = contentType.getValue();
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(HashMap<String,T> value) {
		this.value = value;
	}

	/**
	 * @return the contentType
	 */
	public ContentBehaviour getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(ContentBehaviour contentType) {
		this.contentType = contentType;
	}


	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(Document document) {
		this.document = document;
	}

	/**
	 * @return the xmlString
	 */
	public String getXmlString() {
		return xmlString;
	}

	/**
	 * @param xmlString the xmlString to set
	 */
	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}

	public void contentToJDOM (T t) {

		setDocument(this.contentType.createJDOM(t));

	}

	public void contentToSequenceDocument(List listOfReference, String work) { 

		SequenceDocument sd = this.contentType.createSequenceDocument(listOfReference, work);  //FIXME verificare: lenta
		SAXBuilder sb = new SAXBuilder();
		Document doc;
		try {
			String sdString = sd.toString(); //FIXME verificare lenta!!
			StringReader sr = new StringReader(sdString);
			doc = sb.build(sr); //FIXME verificare lenta!!
			setDocument(doc);		

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void contentToDocumentString(List listOfReference, String work) { 

		SequenceDocument sd = this.contentType.createSequenceDocument(listOfReference, work);  
		setXmlString(sd.getSequence().toString());

	}


	public void referenceToString(ReferenceSet<Reference> ref) { 

		Sequence seq = this.contentType.createSequence(ref);  

		XmlOptions opts = new XmlOptions();
		opts.setSaveSyntheticDocumentElement(new QName("xm:sequence"));//per eliminare tutti gli xml-fragment
		opts.setSavePrettyPrint();

		/* per togliere la dichiarazione del namespace */
		Map prefixes = new HashMap();
		prefixes.put("xm", "http://ilc.cnr.it/gtoa/Model/xmlmapping");
		opts.setSaveImplicitNamespaces(prefixes);

		/*
		 * per togliere il prefix da ogni tag ma funziona se non usata insieme alla setSaveImplicitNamespaces!!!
		HashMap suggestedPrefixes = new HashMap();
		suggestedPrefixes.put("http://ilc.cnr.it/G2A/Model/xmlmapping", "");
		opts.setSaveSuggestedPrefixes(suggestedPrefixes);
		 */
		setXmlString(seq.xmlText(opts));

	//	System.err.println(getXmlString());
	}

}
