package it.cnr.ilc.cophi.action.controller.resource;

import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument;

import java.util.HashMap;

import org.jdom2.Document;
import org.xmldb.api.base.Resource;
import org.xmldb.api.modules.XMLResource;


public interface ResourceBehaviour {

	
	public Resource getResource();
	
	public void setResource();
	
	public HashMap<String, Resource> resourceLoad(String collectionName);
	
	public SequenceDocument retrieveContent (Resource resource);

	public boolean store(Document document, String dbName, String fileName);

	public boolean remove(String dbName, XMLResource xr);

	public boolean xqupdate(String id, String xmlString, String dbName, String fileName);
	
}
