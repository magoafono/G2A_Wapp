package it.cnr.ilc.cophi.utils;

import it.cnr.ilc.cophi.datahandler.ExistDBConnector;
import it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.XmlException;
import org.w3c.dom.Node;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;


public class XMLConverter {


	/**
	 * Costruisce un XMLResource a partire dal nome con il quale il file XML e' memorizzato in eXist
	 * @param resourceName 
	 * @return XMLResource
	 * @throws XMLDBException 
	 */
	private XMLResource getResource(String dbName, String resourceName) throws XMLDBException {

		XMLResource xr = null;
		ExistDBConnector connector = ExistDBConnector.getInstance();
		if (null != connector) {
			Collection root = connector.connect(dbName);
			xr = (XMLResource) root.getResource(resourceName);
		}
		
		return xr;
	}

	/**
	 * 
	 * @param dbName
	 * @return
	 */
	public HashMap<String, Resource> getResources (String dbName) {

		HashMap<String, Resource> resourcesHM = null;

		ExistDBConnector connector = ExistDBConnector.getInstance();

		if (null != connector) {
			Collection root = connector.connect(dbName);
			if (null != root) {
				resourcesHM = new HashMap<String, Resource>();
				try {
					for(String resourceName: root.listResources()){
						XMLResource xr = getResource(dbName, resourceName);
						resourcesHM.put(resourceName, xr);
					}
				} catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		
		return resourcesHM;
	}
	
	/**
	 * 
	 * @param resource
	 * @return
	 */
	public AddDocument convertDocument(XMLResource resource) {

		Node node = null;
		AddDocument ad = null;
		try {
			node = resource.getContentAsDOM();
			ad = AddDocument.Factory.parse(node);
		} catch (XMLDBException e) {
			e.printStackTrace();
		} catch (XmlException e) {
			e.printStackTrace();
		}

		return ad;

	}

	
	/*
	 * 1 - Prendo il testo originale (es. badaoui,txt)
	 * 2 - creo la struttura XML dei token (sequence/element)
	 * 3 - Per ogni file XML "vecchio" dove sono presenti le pericopi
	 * 3.1 - prendo il testo da "pericope_ar"
	 * 3.2 - "cerco" tutta la stringa di pericope_ar nel testo txt di originale
	 * 3.3 - se la trovo, calcolo l'offset dello start di pericope_ar nel txt originale
	 * 3.4 - cerco nella struttura XML il token con start quello precedentemente trovato
	 * 3.5 - se la trovo, calcolo l'offset dell'end di pericope_ar nel txt originale
	 * 3.6 - cerco nella struttura XML il token con end quello precedentemente trovato
	 * 4 - Trovato l'insieme dei token che costituiscono la pericope crea la struttura XML pericope
	 * 
	 */

	public static void main(String[] args) {
		
		HashMap<String, Resource> resources = null;
		
		XMLConverter  xmlConv = new XMLConverter();
		
		resources = xmlConv.getResources(null);
		
		for (Map.Entry<String, Resource> entry : resources.entrySet()) {
			
			XMLResource xmlRes = (XMLResource) entry.getValue();
			AddDocument ad = xmlConv.convertDocument(xmlRes);
			
		}

		
		
		
		
		
	}
}
