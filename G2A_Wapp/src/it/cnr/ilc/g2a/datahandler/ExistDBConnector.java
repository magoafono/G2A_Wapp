/**
 * 
 */
package it.cnr.ilc.g2a.datahandler;

import it.cnr.ilc.g2a.action.userbean.Pair;
import it.cnr.ilc.g2a.utils.BuckwalterArabicConverter;
import it.cnr.ilc.g2a.utils.Consts;
import it.cnr.ilc.g2a.utils.MessageProvider;
import it.cnr.ilc.g2a.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exist.xmldb.EXistResource;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

/**
 * @author simone
 *
 */
public class ExistDBConnector {

	// Default values

	MessageProvider mp = new MessageProvider();

	private static final Logger log = LogManager.getLogger("ExistDBConnector");
	
	private String dbDriver = mp.getValue(Consts.CONFIGNAME, "db_driver");
	private String dbServerName = mp.getValue(Consts.CONFIGNAME,"db_server");
	private String dbServerPort = mp.getValue(Consts.CONFIGNAME,"db_port");
	private String dbServerProtocol = mp.getValue(Consts.CONFIGNAME,"db_protocol");
	private String dbRootName = mp.getValue(Consts.CONFIGNAME,"db_root_name");
	private String dbLogin = mp.getValue(Consts.CONFIGNAME,"db_login");
	private String dbPassword = mp.getValue(Consts.CONFIGNAME,"db_password");

	 


	//the following variables are set in config.properties
/*
	private String dbDriver = "org.exist.xmldb.DatabaseImpl";
	private String dbServerName = "localhost";
	private String dbServerPort = "8085";
	private String dbServerProtocol = "/exist/xmlrpc/db";
	private String dbRootName = "/ga";
	private String dbLogin = "admin";
	private String dbPassword = "angelodel80";
*/
	private HashMap<String, Collection> connectionsCache = new HashMap<String, Collection>();
	private HashMap<String, CompiledExpression> compiledXPathExprCache = new HashMap<String, CompiledExpression>();


	private static ExistDBConnector instance = null;

	private ExistDBConnector ()	{

	};


	/**
	 * @return the dbDriver
	 */
	public String getDbDriver() {
		return dbDriver;
	}

	/**
	 * @param dbDriver the dbDriver to set
	 */
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	/**
	 * @return the dbServerName
	 */
	public String getDbServerName() {
		return dbServerName;
	}

	/**
	 * @param dbServerName the dbServerName to set
	 */
	public void setDbServerName(String dbServerName) {
		this.dbServerName = dbServerName;
	}

	/**
	 * @return the dbServerPort
	 */
	public String getDbServerPort() {
		return dbServerPort;
	}

	/**
	 * @param dbServerPort the dbServerPort to set
	 */
	public void setDbServerPort(String dbServerPort) {
		this.dbServerPort = dbServerPort;
	}

	/**
	 * @return the dbRootName
	 */
	public String getDbRootName() {
		return dbRootName;
	}

	/**
	 * @param dbRootName the dbRootName to set
	 */
	public void setDbRootName(String dbRootName) {
		this.dbRootName = dbRootName;
	}

	/**
	 * @return the dbLogin
	 */
	public String getDbLogin() {
		return dbLogin;
	}

	/**
	 * @param dbLogin the dbLogin to set
	 */
	public void setDbLogin(String dbLogin) {
		this.dbLogin = dbLogin;
	}

	/**
	 * @return the dbPassword
	 */
	public String getDbPassword() {
		return dbPassword;
	}

	/**
	 * @param dbPassword the dbPassword to set
	 */
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public static ExistDBConnector getInstance() {

		if (null == instance) {
			synchronized (ExistDBConnector.class) {
				if (null == instance) {
					instance = new ExistDBConnector();
				}				
			}
		}
		return instance;
	}

	private Database getDatabaseInstance () {

		Class<?> c;
		Database db = null;
		try {
			c = Class.forName(dbDriver);
			db = (Database)c.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} 

		return db;
	}

	private Collection getNewCollection (String dbName) throws XMLDBException  {

		Collection resourceCollection = null;
		if (null != dbName) {
			Database db = getDatabaseInstance();
			DatabaseManager.registerDatabase(db);
			String URL = "xmldb:exist://" + dbServerName + ":" + dbServerPort +  dbServerProtocol +  dbRootName +  dbName;
			resourceCollection = DatabaseManager.getCollection(URL, dbLogin, dbPassword);
			log.debug("getNewCollection() " + URL);
		} else {
			log.fatal("No dbName!!!");
		}
		return resourceCollection;
	}

	private Collection getCollection (String dbName) {

		Collection resourceCollection = null;
		try {
			if (! connectionsCache.containsKey(dbName)) {
				resourceCollection = getNewCollection(dbName);
				if (null != resourceCollection) {
					log.debug("Get new collection "  + resourceCollection.getName());
				} else {
					log.fatal("Collection not found!");
				}

			} else {
				resourceCollection = connectionsCache.get(dbName);
				if (! resourceCollection.isOpen()) {
					log.debug("Collection is close => get a new one");
					resourceCollection = getNewCollection(dbName);
				}
			}
			storeConnection (dbName, resourceCollection);

		} catch (XMLDBException ex) {
			ex.printStackTrace();
		}

		return resourceCollection;
	}

	private void storeConnection (String dbName, Collection resourceCollection) throws XMLDBException {

		if (connectionsCache != null) {
			if (null != resourceCollection) {
				log.trace("Store collection");
				connectionsCache.put(dbName, resourceCollection);
			} else {
				log.fatal("Error in storeConnection("+ dbName + ", "+ resourceCollection +")");
				throw new XMLDBException();
			}
		} else {
			log.fatal("Error in storeConnection("+ dbName + ", "+ resourceCollection +"): connections hash map is null!");
		}
	}

	public Collection connect (String dbName) {

		return getCollection(dbName);

	}

	public boolean save (String dbName, String xmlContent, String filename) {

		boolean ret = false;
		XMLResource xr = null;
		Collection col = getCollection(dbName);
		try {
			xr = (XMLResource) col.createResource(filename, "XMLResource");
			xr.setContent(xmlContent);
			col.storeResource(xr);
			ret = true;

		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		if(null != xr) {
			try { 
				((EXistResource)xr).freeResources(); 
			} 
			catch(XMLDBException xe) {xe.printStackTrace();}
		}

		return ret;
	}

	public boolean save (String dbName, XMLResource xr, String filename) {

		boolean ret = false;
		Collection col = getCollection(dbName);

		File f = new File(filename);
		try {
			xr.setContent(f);
			col.storeResource(xr);
			ret = true;
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		if(null != xr) {
			try { 
				((EXistResource)xr).freeResources(); 
			}
			catch(XMLDBException xe) {xe.printStackTrace();}
		}
		return ret;
	}

	public boolean remove (String dbName, XMLResource xr) {

		boolean ret = false;
		Collection col = getCollection(dbName);

		try {
			col.removeResource(xr);
			ret = true;
		} catch (XMLDBException e1) {
			e1.printStackTrace();
		}

		if(null != xr) {
			try { 
				((EXistResource)xr).freeResources(); 
			} 
			catch(XMLDBException xe) {xe.printStackTrace();}
		}
		return ret;
	}

	/**
	 * Remove all open connection
	 */
	public void disconnectAll () {

		Iterator<String> it = connectionsCache.keySet().iterator();
		while (it.hasNext()) {
			String dbName = it.next();
			disconnect (dbName);
		}
	}


	/**
	 * Close and remove the connection to <i>dbName</i> collection
	 * @param dbName
	 */
	public void disconnect (String dbName) {

		if (connectionsCache.containsKey(dbName)) {
			Collection resourceCollection = connectionsCache.get(dbName);
			try {
				if (resourceCollection.isOpen()) {
					resourceCollection.close();
					connectionsCache.remove(dbName);
				}
			} catch (XMLDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/********* XQuery ******/


	/**
	 * 
	 * @param URI
	 * @param tokenId
	 * @return the result as a string. If no results the ret.isEmpty() is true.
	 */
	public ArrayList<String> searchTokenInPericopeById (String URI, String tokenId) {
		ArrayList<String> ret = null;
		if (null != tokenId) {
			String doc = "doc('pericopes.xml')";
			String path = "/xm:sequence/xm:sequence/xm:element";
			String id = "[@id='"+ tokenId +"']";
			String query = doc + path + id;
			ret = xqRunWithMultipleResults (URI, query);
			//se non ci sono risultati ret e' una string vuota
		}

		return ret;
	}

	/**
	 * 
	 * @param URI
	 * @param tokenId
	 * @return the result as a string. If no results the ret.isEmpty() is true.
	 */
	public String searchPericopeIdByTokenRef (String URI, String tokenId) {

		return searchByRef("pericopes.xml", URI, tokenId);
	}


	public String searchLinkIdByPericopeRef(String URI, String pericopeId) {

		return searchByRef("links.xml", URI, pericopeId);
	}


	private String searchByRef(String XML, String URI, String ref) {
		String ret = null;
		if (null != ref) {
			String doc = "for $c in doc('"+XML+"')";
			String path = "//xm:sequence[xm:element";
			String id = "/@ref='"+ ref +"']";
			String output = " return data($c/@id)";
			String query = doc + path + id + output;
			ret = xqRunWithSingleResult(URI, query);
			//se non ci sono risultati ret e' una string vuota
		}

		return ret;
	}

	/**
	 * Recupera il sottoalbero XML della pericope individuata da pericopeId
	 * @param URI 
	 * @param pericopeId
	 * @return
	 */
	public ArrayList<String> searchPericopeById (String URI, String pericopeId) {
		ArrayList<String> ret = null;
		if (null != pericopeId) {
			String doc = "doc('pericopes.xml')";
			String path = "/sequence/sequence";
			String id = "[@id='"+ pericopeId +"']";
			String query = doc + path + id;
			ret = xqRunWithMultipleResults (URI, query);
		}

		//se non ci sono risultati ret e' una string vuota
		return ret;
	}

	/**
	 * 
	 * @param URI
	 * @param ext
	 * @return
	 */
	//@TODO mai usata??
	public ArrayList<String> searchTokenInPericopeByExtended (String URI, String ext) {

		ArrayList<String> ret = null;
		if (null != ext) {
			String doc = "doc('pericopes.xml')";
			String path = "/sequence/sequence/element";
			String extended = "[@extended='"+ ext +"']";
			String query = doc + path + extended;
			ret = xqRunWithMultipleResults (URI, query);
			//se non ci sono risultati ret e' una string vuota
		}

		return ret;
	}


	public ArrayList<String> removeTokenInPericopeById (String URI, String tokenId) {

		ArrayList<String> ret = null;
		if (null != tokenId) {
			String doc = "doc('pericopes.xml')";
			String path = "/sequence/sequence/element";
			String id = "[@id='"+ tokenId +"']";
			String query = "update delete " + doc + path + id;

			ret = xqRunWithMultipleResults (URI, query);
			//se non ci sono risultati ret e' una string vuota
		}

		return ret;
	}

	public ArrayList<String> insertTokenInPericopeById (String URI, String pericopeId, String node) {

		ArrayList<String> ret = null;
		if (null != pericopeId) {
			String doc = "doc('pericopes.xml')";
			String path = "/sequence/sequence";
			String id = "[@id='"+ pericopeId +"']";
			String query = "update insert " + node + " into "  + doc + path + id ;
			log.debug("query " + query);
			ret = xqRunWithMultipleResults (URI, query);
			//se non ci sono risultati ret e' una string vuota
		}

		return ret;
	}


	public ArrayList<String>  updatePericopeById (String URI, String pericopeId, String node) {

		ArrayList<String> ret = null;
		if (null != pericopeId) {
			String doc = "doc('pericopes.xml')";
			String path = "/xm:sequence/xm:sequence";
			String id = "[@id='"+ pericopeId +"']";
			String query = "update replace " + doc + path + id + " with "  + node ;
			log.debug("query: " + query);
			ret = xqRunWithMultipleResults (URI, query);
			//se non ci sono risultati ret e' una string vuota
		}

		return ret;
	}


	//////// Search in ANALYSIS.XML

	/**
	 * Recupero tutti gli identificativi dei token appartenenti ad una determinata pericope
	 * @param URI
	 * @param pericopeId Identificativo della pericope
	 * @return Lista di identificativi (String) di token
	 */
	public ArrayList<String> searchTokensInPericopeById (String URI, String pericopeId) {
		ArrayList<String> ret = null;
		if (null != pericopeId) {
			/*
			 * Es: string(/xm:sequence/xm:sequence[@id=43190.0406]/xm:element/@ref)
			 */
			String doc = "doc('pericopes.xml')";
			String path = "//xm:sequence";
			String id = "[@id='"+ pericopeId +"']";
			String element = "/xm:element/@ref";
			String query = "data("+doc + path + id + element+")"; //prendo solo il valore senza il nome dell'attributo
			//String query = doc + path + id + element; //prendo solo il valore senza il nome dell'attributo
			//String xquery = "data(doc('pericopes.xml')//xm:sequence[@id='00301.00312']/xm:element/@ref|doc('pericopes.xml')//xm:sequence[@id='00301.00312']/xm:element/@id)";
			//String xquery = "data(doc('pericopes.xml')//xm:sequence[@id='00301.00312']/xm:element/@*)";
			ret = xqRunWithMultipleResults (URI, query);
			log.info(query);
		}

		//se non ci sono risultati ret e' una string vuota
		return ret;
	}

	/**
	 * Ricerca in linkAnalysis.xml l'elemento che ha il riferimento al token di tokenRef uguale a tokenId e
	 * restituisce il valore del ref alla analisi morfologica 
	 * @param URI
	 * @param tokenId
	 * @return Identificativo dell'entrata morfologica in morphoDB.xml
	 */
	public String searchLinkAnalysisByTokenId (String URI, String tokenId) {

		String ret = null;
		if (null != tokenId) {
			/*
			 * Es: string(/xm:sequence/xm:element/xm:param[@name='tokenRef' and @value='42010.000001']/following-sibling::xm:param[@name="analysisRef"]/@value)
			 */
			String doc = "doc('linkAnalysis.xml')";
			//String path = "/xm:sequence/xm:element/xm:param[@name='tokenRef' and ";
			//String path = "//xm:param[@name='tokenRef' and "; //piu' lento che specificando tutto il path

			String path = "//xm:element/xm:param[@value='"+ tokenId +"' and ";
			//String path = "/xm:sequence/xm:element/xm:param["; //piu' veloce ma ci puo' essere il pericolo di selezionare il nodo sbagliato (cioe' che non sia un tokenRef)
			//String path = "//xm:param[@name='tokenRef' and "; //piu' lento che specificando tutto il path //NON FUNZIONA CON LA VERSIONE 2.2RC2 di exist

			//String id = "@value='"+ tokenId +"']/following-sibling::xm:param[@name='analysisRef']/@value";
			//String id = "@value='"+ tokenId +"']/following-sibling::xm:param[1]/@value";

			//String id = "@name='tokenRef']/following-sibling::xm:param[@name='analysisRef']/@value";
			String id = "@name='tokenRef']/../xm:param[@name='analysisRef']/@value";
			//			String id = "@value='"+ tokenId +"']/parent::node()/xm:param[@name='analysisRef']/@value"; //piu' lento che con il sibling
			String query = "data(" + doc + path + id + ")";

			log.debug(query);
			ret = xqRunWithSingleResult(URI, query);
			//se non ci sono risultati ret e' una string vuota
		}

		return ret;

	}


	public String searchPericopeIdByTokenId (String URI, String tokenId) {

		//	Profiler profiler = new Profiler("searchPericopeIdByTokenId");
		//		profiler.start("searchPericopeIdByTokenId");

		String ret = null;
		if (null != tokenId) {
			/*
			 * Es: string(/xm:sequence/xm:element/xm:param[@name='tokenRef' and @value='42010.000001']/following-sibling::xm:param[@name="analysisRef"]/@value)
			 */
			String doc = "doc('pericopes.xml')";
			//String path = "/xm:sequence/xm:element/xm:param[@name='tokenRef' and ";
			//String path = "//xm:param[@name='tokenRef' and "; //piu' lento che specificando tutto il path

			//	String path = "//xm:sequence[@classname='pericope']/xm:element[@ref='"+ tokenId +"']";
			String path = "//xm:element[@ref='"+ tokenId +"']";


			//String path = "//xm:element[@ref='"+ tokenId +"']";

			//String path = "/xm:sequence/xm:element/xm:param["; //piu'a veloce ma ci puo' essere il pericolo di selezionare il nodo sbagliato (cioe' che non sia un tokenRef)
			//String path = "//xm:param[@name='tokenRef' and "; //piu' lento che specificando tutto il path //NON FUNZIONA CON LA VERSIONE 2.2RC2 di exist

			//String id = "@value='"+ tokenId +"']/following-sibling::xm:param[@name='analysisRef']/@value";
			//String id = "@value='"+ tokenId +"']/following-sibling::xm:param[1]/@value";

			//String id = "@name='tokenRef']/following-sibling::xm:param[@name='analysisRef']/@value";
			String id = "/..[@classname='pericope']/@id";
			//String id = "";
			//			String id = "@value='"+ tokenId +"']/parent::node()/xm:param[@name='analysisRef']/@value"; //piu' lento che con il sibling
			String query = "data(" + doc + path + id + ")";
			log.debug(query);
			ret = xqRunWithSingleResult(URI, query);
			//se non ci sono risultati ret e' una string vuota
		}
		//		profiler.stop().print();
		return ret;

	}

	public String searchLinkIdByPericopeIdAndLang (String URI, String pericopeId, String lang) {

		String ret = null;
		if (null != pericopeId) {
			/*
			 * Es: string(/xm:sequence/xm:element/xm:param[@name='tokenRef' and @value='42010.000001']/following-sibling::xm:param[@name="analysisRef"]/@value)
			 */
			String doc = "doc('links.xml')";
			String path = "//xm:element[@ref='"+ pericopeId +"'][@classname='refPericope'][@extended='"+ lang +"']";
			//String path = "/xm:sequence/xm:element/xm:param["; //piu' veloce ma ci puo' essere il pericolo di selezionare il nodo sbagliato (cioe' che non sia un tokenRef)
			//String path = "//xm:param[@name='tokenRef' and "; //piu' lento che specificando tutto il path //NON FUNZIONA CON LA VERSIONE 2.2RC2 di exist

			//String id = "@value='"+ tokenId +"']/following-sibling::xm:param[@name='analysisRef']/@value";
			//String id = "@value='"+ tokenId +"']/following-sibling::xm:param[1]/@value";

			//String id = "@name='tokenRef']/following-sibling::xm:param[@name='analysisRef']/@value";
			String id = "/../@id";
			//			String id = "@value='"+ tokenId +"']/parent::node()/xm:param[@name='analysisRef']/@value"; //piu' lento che con il sibling
			String query = "data(" + doc + path + id + ")";

			log.debug(query);
			ret = xqRunWithSingleResult(URI, query);
			log.debug("return value " + ret);
		}

		return ret;

	}

	/**
	 * Recupera le informazioni morfologiche (morphoDB.xml) di una entrata con id <i>analysisId</i>
	 * @param URI
	 * @param analysisId Identificativo della analisi da recuperare
	 * @return HashMap contenente le informazioni morfologiche. 
	 * Le keys sono le etichette (forma, lemma, pos) mentre i values sono i valori ad esse associate
	 */

	public HashMap<String, String> searchArabicAnalysisById (String URI, String analysisId) {

		HashMap<String, String> ret = null;
		if (null != analysisId) {
			// 
			// Es: /xm:sequence/xm:element[@id='4']/xm:param/@*/(string())
			//ritorna una lista di valori di attributi: caso delle analisi ritorna valori del tipo:
			// forma . pos punct lemma .

			String doc = "doc('morphoDB.xml')";
			String path = "//xm:element[@id='"+ analysisId +"']/xm:param/@value/(string())";

			String query = doc + path;
			//ret = xqRunWithMultipleResults(URI, query);
			/*hm = new HashMap<String, String>();
			for (int i = 0; i < ret.size(); i+=2) {
				String key = ret.get(i);
				String value = ret.get(i+1);
				hm.put(key, value); //coppie degli attributi della analisi linguistica
			}*/
			//se non ci sono risultati ret e' una string vuota
		}

		return ret;
	}
	public String searchAnalysisAttibutesById (String URI, String analysisId) {

		String ret= null;
		if (null != analysisId) {
			// 
			// Es: /xm:sequence/xm:element[@id='4']/xm:param/@*/(string())
			//ritorna una lista di valori di attributi: caso delle analisi ritorna valori del tipo:
			// forma . pos punct lemma .

			String doc = "doc('morphoDB.xml')";
			String path = "//xm:sequence[";
			//			String id = "@id='"+ analysisId +"']/xm:param/@*/(string())";
			String id = "@id='"+ analysisId +"']";
			String query = doc + path + id;
			ret = xqRunWithSingleResult(URI, query);


			/*hm = new HashMap<String, String>();
			for (int i = 0; i < ret.size(); i+=2) {
				String key = ret.get(i);
				String value = ret.get(i+1);
				hm.put(key, value); //coppie degli attributi della analisi linguistica
			}*/
			//se non ci sono risultati ret e' una string vuota
		}

		return ret;
	}

	public ArrayList<String> searchAnalysisById (String URI, String analysisId) {

		ArrayList<String> ret = null;
		if (null != analysisId) {
			// 
			// Es: /xm:sequence/xm:element[@id='4']/xm:param/@*/(string())
			//ritorna una lista di valori di attributi: caso delle analisi ritorna valori del tipo:
			// forma . pos punct lemma .

			String doc = "doc('morphoDB.xml')";
			String path = "//xm:element[";
			//			String id = "@id='"+ analysisId +"']/xm:param/@*/(string())";
			String id = "@id='"+ analysisId +"']/xm:param/@value/(string())";
			String query = doc + path + id;
			ret = xqRunWithMultipleResults(URI, query);
			/*hm = new HashMap<String, String>();
			for (int i = 0; i < ret.size(); i+=2) {
				String key = ret.get(i);
				String value = ret.get(i+1);
				hm.put(key, value); //coppie degli attributi della analisi linguistica
			}*/
			//se non ci sono risultati ret e' una string vuota
		}

		return ret;

	}

	/**
	 * Recupera l'id del token (Stringa)
	 * @param URI
	 * @param token
	 * @return
	 */
	public ArrayList<String> searchTokenIdsByTokenValue (String URI, String token) {

		ArrayList<String> ret = null;
		if (null != token) {
			String doc = "doc('tokens.xml')";
			String path = "//xm:param[";
			String id = "@value='"+ token +"'][@name='string']/../@id/(string())";
			String query = doc + path + id;
			ret = xqRunWithMultipleResults(URI, query);
		}
		return ret;
	}

	/**
	 * Recupera l'id del token (Stringa)
	 * @param URI
	 * @param tokenId
	 * @return
	 */
	public String searchTokenValueByTokenId (String URI, String tokenId) {

		String ret = null;
		if (null != tokenId) {
			String doc = "doc('tokens.xml')";
			String path = "//xm:element[";
			String id = "@id='"+ tokenId +"']/xm:param[@name='string']/@value/(string())";
			String query = doc + path + id;
			ret = xqRunWithSingleResult(URI, query);
		}
		return ret;
	}



	public ArrayList<String> searchAnalysisIdByParameter (String URI, List<String> itemName, List<String> itemValue, int lang) {

		ArrayList<String> ret = null;
		if (itemValue.size() > 0) {
			String doc = "doc('morphoDB.xml')";
			StringBuilder path = new StringBuilder();
			path.append("//xm:param");
			StringBuilder restriction = new StringBuilder();
			for (int i = 0; i < itemName.size(); i++) {
				String name = itemName.get(i);
				String value = itemValue.get(i);
				switch (lang) {
				case Consts.ARABIC:
					value = BuckwalterArabicConverter.unicodeToBuckwalter(value);
					break;
				case Consts.GREEK:
					value = Utils.extendToComposeGreekCharacter(value);
					break;
				default:
					break;
				}
				if (restriction.length() == 0) {
					if (lang == Consts.ARABIC && "forma".equals(name)) {
						restriction.append ("[@name='"+name+"'][contains(@value,'" + value +"')]"); //meglio prima name e poi value
					} else {
						restriction.append ("[@name='"+name+"'][@value = '" + value +"']");
					}
				} else {
					if (lang == Consts.ARABIC && "forma".equals(name)) {
						restriction.append ("/../xm:param[@name='"+name+"'][contains(@value,'" + value +"']");
					} else {
						restriction.append ("/../xm:param[@name='"+name+"'][@value = '" + value +"']");
					}
				}
			}
			path.append(restriction.toString());
			if (Consts.ARABIC == lang) {
				path.append ("/..");
			}
			path.append ("/../@id/string()");

			String query = doc + path.toString();
			//System.err.println("query: " + query);
			ret = xqRunWithMultipleResults(URI, query);
		}
		return  ret;

	}


	public ArrayList<String> searchTokenIdByForma (String URI, String forma, int lang) {

		ArrayList<String> ret = null;
		if (forma != null) {
			String doc = "doc('tokens.xml')";
			StringBuilder path = new StringBuilder();
			path.append("//xm:param");
			StringBuilder restriction = new StringBuilder();

			if (lang == Consts.ARABIC) {
				restriction.append ("[@name='string'][contains(@value,'" + forma +"')]"); //meglio prima name e poi value
			} else {
				restriction.append ("[@name='string'][@value = '" + forma +"']");
			}

			path.append(restriction.toString());
			path.append ("/../@id/string()");

			String query = doc + path.toString();
			//System.err.println("query: " + query);
			ret = xqRunWithMultipleResults(URI, query);
		}
		return  ret;

	}


	private ArrayList<String> searchAnalysisIdByParameter (String URI, String forma, String pos, String lemma, String radice) {
		ArrayList<String> ret = null;
		if (null != forma || null != pos || null != lemma) {

			// 
			// Es: /xm:sequence/xm:element[@id='4']/xm:param/@*/(string())
			//ritorna una lista di valori di attributi: caso delle analisi ritorna valori del tipo:
			// forma . pos punct lemma .
			String doc = "doc('morphoDB.xml')";
			StringBuilder path = new StringBuilder();
			path.append("//xm:param");

			if (null != forma) {
				path.append ("[@name='forma'][@value = '" + forma +"']");
			}
			if (null != pos) {
				if (null != forma){
					path.append ("/../xm:param[@name='pos'][@value = '" + pos +"']");
				} else {
					path.append ("[@name='pos'][@value = '" + pos +"']");
				}
			}
			if (null != lemma) {
				if (null != forma || null != pos){
					path.append ("/../xm:param[@name='lemma'][@value = '" + lemma +"']");
				} else {
					path.append ("[@name='lemma'][@value = '" + lemma +"']");
				}

			}
			if (null != radice) {
				if (null != forma || null != pos){
					path.append ("/../xm:param[@name='radice'][@value = '" + radice +"']");
				} else {
					path.append ("[@name='radice'][@value = '" + radice +"']");
				}

			}
			path.append ("/../@id/string()");
			//			String id = "@id='"+ analysisId +"']/xm:param/@*/(string())";
			String query = doc + path.toString();
			//System.err.println("query: " + query);
			ret = xqRunWithMultipleResults(URI, query);
			/*hm = new HashMap<String, String>();
			for (int i = 0; i < ret.size(); i+=2) {
				String key = ret.get(i);
				String value = ret.get(i+1);
				hm.put(key, value); //coppie degli attributi della analisi linguistica
			}*/
			//se non ci sono risultati ret e' una string vuota
		}

		return ret;
	}

	public ArrayList<String> searchTokenIdByAnalysisId (String URI, String analysisId) {

		ArrayList<String> ret = null;
		if (null != analysisId) {
			/*
			 * Es: string(/xm:sequence/xm:element/xm:param[@name='tokenRef' and @value='42010.000001']/following-sibling::xm:param[@name="analysisRef"]/@value)
			 */
			String doc = "doc('linkAnalysis.xml')";
			//String path = "/xm:sequence/xm:element/xm:param[@name='tokenRef' and ";
			//String path = "//xm:param[@name='tokenRef' and "; //piu' lento che specificando tutto il path

			String path = "//xm:element/xm:param[@value='"+ analysisId +"' and ";
			//String path = "/xm:sequence/xm:element/xm:param["; //piu' veloce ma ci puo' essere il pericolo di selezionare il nodo sbagliato (cioe' che non sia un tokenRef)
			//String path = "//xm:param[@name='tokenRef' and "; //piu' lento che specificando tutto il path //NON FUNZIONA CON LA VERSIONE 2.2RC2 di exist

			//String id = "@value='"+ tokenId +"']/following-sibling::xm:param[@name='analysisRef']/@value";
			//String id = "@value='"+ tokenId +"']/following-sibling::xm:param[1]/@value";

			//String id = "@name='analysisRef']/preceding-sibling::xm:param[@name='tokenRef']/@value"; //piu' lenta che la seguente!!!
			String id = "@name='analysisRef']/../xm:param[@name='tokenRef']/@value";
			//			String id = "@value='"+ tokenId +"']/parent::node()/xm:param[@name='analysisRef']/@value"; //piu' lento che con il sibling
			String query = "data(" + doc + path + id + ")";

			System.err.println(query);
			ret = xqRunWithMultipleResults(URI, query);
			//se non ci sono risultati ret e' una string vuota
		}
		return ret;

	}

	private XQueryService xqGetService (String dbName) {

		XQueryService xqs = null;
		try {
			Collection col = getCollection(dbName);
			xqs = (XQueryService) col.getService("XPathQueryService", "1.0");
			xqs.setNamespace("xm", "http://ilc.cnr.it/gtoa/Model/xmlmapping");
			xqs.setProperty("indent", "no");

		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xqs;
	}

	/**
	 * Executes an XQuery on DB specified by URI
	 * @param URI It Identifies the database (e.g. xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA2/doc/bada)
	 * @param xquery The XQuery to be executed
	 * @return A string representing the result set retrieved by the XQuery
	 */
	public ArrayList<String> xqRunWithMultipleResults (String URI, String xquery) {

		ArrayList<String> ret = null;
		try {

			ResourceSet result = xqExecute(URI, xquery);
			ResourceIterator i = result.getIterator();
			if (i.hasMoreResources()) {
				ret = new ArrayList<String>();
				Resource res = null;
				while(i.hasMoreResources()) {
					try {
						res = i.nextResource();
						ret.add((String)res.getContent());
					} finally {
						//dont forget to cleanup resources
						try { 
							((EXistResource)res).freeResources(); 
						}
						catch (XMLDBException xe) {
							xe.printStackTrace();
						}
					}
				}
			}	
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return ret;

	}

	public List<Pair<String, String>> xqRunAsDomWithMultipleResults (String URI, String xquery) {
		List<Pair<String, String>> ret = null;
		try {

			System.err.println(xquery);
			ResourceSet result = xqExecute(URI, xquery);
			ResourceIterator i = result.getIterator();
			System.err.println(((XMLResource)result.getResource(0)).getContentAsDOM());
			System.exit(0);
			if (i.hasMoreResources()) {
				//ret = new ArrayList<String>();
				XMLResource res = null;
				while(i.hasMoreResources()) {
					try {
						res = (XMLResource) i.nextResource();
						Node node = (Node) res.getContentAsDOM();
						System.err.println(">>> " + res.getContentAsDOM());
						NodeList nodeList = node.getChildNodes();
						for (int index = 0; index < nodeList.getLength(); index++) {
							System.err.println(node.getNodeName() + " " + nodeList.item(index));
						}
						//ret.add((String)res.getContent());
					} finally {
						//dont forget to cleanup resources
						try { 
							((EXistResource)res).freeResources(); 
						}
						catch (XMLDBException xe) {
							xe.printStackTrace();
						}
					}
				}
			}	
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return ret;
	}


	private String xqRunWithSingleResult (String URI, String xquery) {

		String ret = null;
		try {

			ResourceSet result = xqExecute(URI, xquery);
			if (result.getSize() == 1) {
				Resource res = result.getResource(0);
				ret = (String) res.getContent();
				try { 
					((EXistResource)res).freeResources(); 
				}
				catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			} 
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return ret;
	}

	public ResourceSet xqExecute (String URI, String xquery) {

		ResourceSet ret = null;
		try {
			long start_time1 = System.currentTimeMillis();

			XQueryService xqs = xqGetService (URI);
			//CompiledExpression compiled = xqs.compile(xquery);
			/*CompiledExpression compiled =	getCompiledXPathExpr (xqs, xquery);
			ret = xqs.execute(compiled);*/
			ret = xqs.query(xquery);
			long end_time1 = System.currentTimeMillis();
			double difference1 = (end_time1 - start_time1);///1e6;
			log.debug("xqExecute " + xquery + " in " + difference1 + "ms");
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return ret;

	}


	private CompiledExpression getCompiledXPathExpr (XQueryService xqs, String xquery) {

		CompiledExpression expr = null;
		try {
			if (! compiledXPathExprCache.containsKey(xquery)) {
				expr = xqs.compile(xquery);
				compiledXPathExprCache.put(xquery, expr);
			} else {
				expr = compiledXPathExprCache.get(xquery);
				expr.reset();
			}

		} catch (XMLDBException ex) {
			ex.printStackTrace();
		}
		return expr;
	}

	/**
	 * Controlla l'esistenza del path (al file) individuato da URI nella database dbName (es. "/new_GA/doc/bada", "tokens.xml")
	 * @param dbName
	 * @param URI
	 * @return
	 */
	public boolean exists(String dbName, String URI) {
		boolean ret = false;
		if (null != dbName && null != URI) {
			Collection col = getCollection(dbName);
			try {
				ret = (col.getResource(URI) != null);
			} catch (XMLDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ret;
	}


	public static void main(String[] args) {

		ExistDBConnector dbconn = ExistDBConnector.getInstance();

		/*ArrayList<String> output = dbconn.searchPericopeById("xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA2/doc/bada", "08909.08916");
		System.err.println(output);
		output = dbconn.searchPericopeById("xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA2/doc/plot", "58010.3236");
		System.err.println(output);
		output = dbconn.searchTokenInPericopeById("xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA2/doc/bada", "ref_089.000144");
		System.err.println(output);
		output = dbconn.searchTokenInPericopeByExtended("xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA2/doc/plot", "λόγους");
		System.err.println(output);
		output = dbconn.removeTokenInPericopeById("xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA2/doc/bada", "ref_089.000144");
		System.err.println("remove (" + output + ")");
		output = dbconn.searchPericopeById("xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA2/doc/bada", "08909.08916");
		System.err.println(output);

		dbconn.insertTokenInPericopeById("xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA2/doc/bada", "08909.08916", 
				"<element id=\"XXref_089.000144\" ref=\"089.000144\" classname=\"refToken\" extended=\"خلّى\"/>");
		System.err.println(dbconn.searchPericopeById("xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA2/doc/bada", "08909.08916"));
		 */
		//dbconn.updatePericopeById("xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA2/doc/plot", "43190.0104", 
		/*
		dbconn.updatePericopeById("new_GA2/doc/plot", "43190.0104",
				"<sequence classname=\"pericope\" type=\"gr\" extended=\"IV 3, 19.1-4\" id=\"43190.0104\">de</sequence>");
		System.err.println("After update: " + dbconn.searchPericopeById("new_GA2/doc/plot", "43190.0104"));
		dbconn.updatePericopeById("new_GA2/doc/plot", "43190.0104",
		"<sequence classname=\"pericope\" type=\"gr\" extended=\"IV 3, 19.4-6\" id=\"43190.0104\">\n" +
		" <element id=\"ref_43190.000036\" ref=\"43190.000036\" classname=\"refToken\" extended=\"τὸ\"/>\n" + 
		"</sequence>");
		System.err.println("After update: " + dbconn.searchPericopeById("new_GA2/doc/plot", "43190.0104"));
		 */
		//System.err.println("Searching for tokenId in all pericopes: "+ dbconn.searchPericopeIdByTokenRef("new_GA2/doc/plot", "42010.000002"));

		/*
		for (int i=0; i< 100; i++){
			//	System.err.println("De, " + i + " "	 + dbconn.searchAnalysisById("new_GA/doc/plot", "56"));
			long start_time = System.currentTimeMillis();
			//System.err.println("De, " + i + " "	 + dbconn.searchLinkAnalysisByTokenId("new_GA/doc/plot", "42010.000002"));
			dbconn.searchLinkAnalysisByTokenId("new_GA/doc/plot", "42010.000002");
			long end_time = System.currentTimeMillis();
			double difference = (end_time - start_time);///1e6;
			System.err.println(difference);
		}*/
		/**/


		dbconn.exists("/new_GA/doc/bada", "tokenss.xml");
		System.exit(0);
		for (int i=0; i< 10; i++){
			long start_time1 = System.currentTimeMillis();


			//ArrayList<String> ret = dbconn.searchAnalysisIdByParameter("new_GA/doc/plot", "θειότερον", "adj", "NOLEMMA");

			//ArrayList<String> ret = dbconn.searchTokenIdByAnalysisId("new_GA/doc/plot", "2610");

			//String ret = dbconn.searchLinkAnalysisByTokenId("new_GA/doc/plot", "42010.000002");
			String ret = dbconn.searchPericopeIdByTokenId("new_GA/doc/bada", "003.000001");
			//String ret = dbconn.searchLinkIdByPericopeIdAndLang("new_GA/link", "43190.0104", "grc");
			//ArrayList<String> ret = dbconn.searchAnalysisById("new_GA/doc/bada", "2769");
			//ArrayList<String> ret = dbconn.searchTokensInPericopeById("new_GA/doc/bada","00301.00312");


			//	dbconn.searchTokenIdByParameter("aristotele/doc/greek", Arrays.asList(""), Arrays.asList(""), Consts.GREEK);
			long end_time1 = System.currentTimeMillis();
			double difference1 = (end_time1 - start_time1);///1e6;
			log.debug( difference1 + " (" + ret +")");
		}/*/
		/*System.err.println(dbconn.xqRunWithMultipleResults("xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA/doc/bada/", 
			"doc('pericopes.xml')//xm:sequence[@id='00301.00312']/child::node()"));/**/
		//System.err.println(dbconn.xqRunAsDomWithMultipleResults("xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA/doc/bada/", 
		//"doc('pericopes.xml')//xm:sequence[@id='00301.00312']/*"));
		//dbconn.searchAnalysisAttibutesById("new_GA/doc/bada", "2769");
		log.debug("Done.");

	}





}
