package it.cnr.ilc.cophi.action.controller.resource;

import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument;

import java.util.HashMap;

import org.jdom2.Document;
import org.xmldb.api.base.Resource;
import org.xmldb.api.modules.XMLResource;

public class ImageResourceBehaviour implements ResourceBehaviour {

	@Override
	public Resource getResource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResource() {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, Resource> resourceLoad(String collectionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SequenceDocument retrieveContent(Resource resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean store(Document document, String dbName, String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String dbName, XMLResource xr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xqupdate(String id, String xmlString, String dbName, String fileName) {
		// TODO Auto-generated method stub
		return false;
	}



}
