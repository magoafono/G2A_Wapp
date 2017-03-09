/**
 * 
 */
package it.cnr.ilc.g2a.datahandler;

import it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument;
import it.cnr.ilc.g2a.model.xmlmapping.SequenceDocument;

import java.util.HashMap;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.w3c.dom.Node;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

/**
 * @author simone
 *
 */
public  class XMLHandler  {
	/**
	 * Costruisce un XMLResource a partire dal nome con il quale il file XML e' memorizzato in eXist
	 * @param resourceName 
	 * @return XMLResource
	 * @throws XMLDBException 
	 */
	public XMLResource getResource(String dbName, String resourceName) {

		XMLResource xr = null;
		ExistDBConnector connector = ExistDBConnector.getInstance();
		if (null != connector) {
			Collection root = connector.connect(dbName);
			try {
				xr = (XMLResource) root.getResource(resourceName);
			} catch (XMLDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (java.lang.ClassCastException ee) {
				ee.printStackTrace();	
				System.err.println("dbName (" + dbName + ") resourceName (" + resourceName + ")");
			}
		}

		return xr;
	}

	/**
	 * 
	 * @param dbName nome del database o meglio del repos es. new_GA/bada
	 * @return
	 */
	public HashMap<String, Resource> getResources (String dbName) {

		HashMap<String, Resource> resourcesHM = null;

		ExistDBConnector connector = ExistDBConnector.getInstance();

		if (null != connector) {
			try {
				Collection root = connector.connect(dbName);
				if (null != root) {
					resourcesHM = new HashMap<String, Resource>();
					for(String resourceName: root.listResources()){
						XMLResource xr = getResource(dbName, resourceName);
						resourcesHM.put(resourceName, (Resource) xr);
					}
				}
			} catch (XMLDBException e) {
				e.printStackTrace();
			}

		}

		return resourcesHM;
	}

	/**
	 * 
	 * @param resource
	 * @param document used as type
	 * @return
	 * @throws XMLDBException
	 */
	public XmlObject loadDocument(XMLResource resource, XmlObject document) throws XMLDBException {

		String node = null;
		XmlObject xmlDoc = null;
		try {
			if (null != resource) {
				node = (String) resource.getContent();
				if ( document instanceof SequenceDocument ){
					XmlOptions opts = new XmlOptions();	
					opts.setCompileNoValidation();
					opts.setUnsynchronized();
					xmlDoc = (XmlObject) SequenceDocument.Factory.parse(node, opts);
				} else if (document instanceof AddDocument) {
					//per importare i vecchi XML
					xmlDoc = (XmlObject) AddDocument.Factory.parse(node);
				} else {
					System.err.println("XMLHandler.loadDocument() error for " + document.toString());
				}
			}  else {
				System.err.println("XMLHandler.loadDocument() error for resource " + resource);

			}
		} catch (XmlException e) {
			e.printStackTrace();
		}
		return xmlDoc;

	}
	public XmlObject loadDocument2(XMLResource resource, XmlObject document) throws XMLDBException {

		Node node = null;
		XmlObject xmlDoc = null;
		try { 
			node = resource.getContentAsDOM();
			if ( document instanceof SequenceDocument ){
				xmlDoc = (XmlObject) SequenceDocument.Factory.parse(node);
			} else if (document instanceof AddDocument) {
				//per importare i vecchi XML
				xmlDoc = (XmlObject) AddDocument.Factory.parse(node);
			} else {
				System.err.println("XMLHandler.loadDocument() error for " + document.toString());
			}
		} catch (XmlException e) {
			e.printStackTrace();
		}

		return xmlDoc;

	}

	public boolean save(String dbName, String xmlContent, String filename) {

		ExistDBConnector connector = ExistDBConnector.getInstance();
		if (!filename.endsWith(".xml")) {
			filename = filename.concat(".xml");
		}

		return connector.save (dbName, xmlContent, filename);
	}


	public boolean remove(String dbName,  XMLResource xr) {

		ExistDBConnector connector = ExistDBConnector.getInstance();

		return connector.remove (dbName, xr);
	}
	
	public boolean xqupdate(String id, String xmlString, String dbName, String fileName) {
		
		ExistDBConnector connector = ExistDBConnector.getInstance();
		
		connector.updatePericopeById (dbName, id, xmlString);
		
		return false;
		
	}
	
	
}
