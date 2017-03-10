package it.cnr.ilc.g2a.action.controller.resource;

import it.cnr.ilc.g2a.datahandler.XMLHandler;
import it.cnr.ilc.gtoa.model.xmlmapping.SequenceDocument;

import java.io.IOException;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

public class XMLResourceBehaviour implements ResourceBehaviour {

	private static final Logger log = LogManager.getLogger("XMLResourceBehaviour");

	XMLHandler handler = new XMLHandler();

	public XMLResourceBehaviour () {
	}

	@Override
	public Resource getResource() {
		return null;
	}

	@Override
	public void setResource() {

	}

	@Override
	public HashMap<String, Resource> resourceLoad(String collectionName) {

		return handler.getResources(collectionName);
	}

	@Override
	public SequenceDocument retrieveContent(Resource resource) {
		SequenceDocument sd = null;
		try {
			sd = (SequenceDocument) handler.loadDocument((XMLResource) resource, SequenceDocument.Factory.newInstance());

		} catch (XMLDBException e) {
			log.error(e.getMessage());
		} finally {
			if(null == sd) {
				sd = SequenceDocument.Factory.newInstance();
			}
		}
		return sd;
	}

	@Override
	public boolean store(Document document, String dbName, String fileName) {

		XMLOutputter xmlout = new XMLOutputter(Format.getPrettyFormat());

		return handler.save(dbName, xmlout.outputString(document), fileName);
	}


	@Override
	public boolean remove( String dbName, XMLResource xr) {

		return handler.remove(dbName, xr);
	}

	@Override
	public boolean xqupdate(String id, String xmlString, String dbName, String fileName) {

		return handler.xqupdate(id, xmlString, dbName, fileName);
	}

	public static void main(String[] args) {
		SAXBuilder sax = new SAXBuilder();
		try {
			Document document = sax.build("/home/simone/test-pericopes.xml");
			// System.out.println(xFactory.getClass());
			XMLResourceBehaviour xmlrb = new XMLResourceBehaviour();
		//	xmlrb.xqupdate("67140.2224", document, "", "/home/simone/pericopes.xml");

			System.err.println("End.");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
