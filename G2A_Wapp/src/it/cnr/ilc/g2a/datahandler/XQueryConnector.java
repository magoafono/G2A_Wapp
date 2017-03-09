package it.cnr.ilc.g2a.datahandler;

import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
import org.exist.xmldb.EXistResource;
public class XQueryConnector {

	private static String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/ga/new_GA2/doc/bada";
	/**
	 * args[0] Should be the name of the collection to access
	 * args[1] Should be the XQuery to execute
	 */
	public static void main(String args[]) throws Exception {

		final String driver = "org.exist.xmldb.DatabaseImpl";

		// initialize database driver
		Class cl = Class.forName(driver);
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);

		Collection col = null;
		try { 
			col = DatabaseManager.getCollection(URI);
			XQueryService xqs = (XQueryService) col.getService("XQueryService", "1.0");
			xqs.setProperty("indent", "yes");
			/*
			 * 1 - Per rimuovere l'elemento con id specificato nella pericope con id specificato
			 * update delete  doc('/db/ga/new_GA/doc/bada/pericopes.xml')/sequence/sequence[@id='08909.08916']/element[@id='ref_089.000260']
			 * 
			 * 2 - Per fare la ricerca della pericope con id specificato
			 * xquery version "3.0";
			for $book in doc('/db/ga/new_GA/doc/bada/pericopes.xml')
			return $book/sequence/sequence[@id='08909.08916']
			 * 3 - Per inserire un nodo nella pericope con id specificato	
			 * xquery version "3.0";
				update insert <element id="ref_089.000260" ref="089.000260" classname="refToken" extended="XXX"/> into doc('/db/ga/new_GA/doc/bada/pericopes.xml')/sequence/sequence[@id='08909.08916']         * 
			 */


//			CompiledExpression compiled = xqs.compile("for $book in doc('pericopes.xml') return $book/sequence/sequence[@id='08909.08916']");
			CompiledExpression compiled = xqs.compile("doc('pericopes.xml')/sequence/sequence/element[@id='ref_089.000132']");
			ResourceSet result = xqs.execute(compiled);
			ResourceIterator i = result.getIterator();
			Resource res = null;
			int t = 0;
			while(i.hasMoreResources()) {
				try {
					res = i.nextResource();

					System.err.println(t++ + " " + res.getClass() + " " +res.getContent());
				} finally {
					//dont forget to cleanup resources
					try { ((EXistResource)res).freeResources(); }
					catch(XMLDBException xe) {xe.getLocalizedMessage();}
				}
			}
		} catch (XMLDBException xe) {System.err.println(xe.getMessage());}
			
		finally {
			//dont forget to cleanup
			if(col != null) {
				try { col.close(); } 
				catch(XMLDBException xe) {xe.getLocalizedMessage();}
			}
		}
	}
	
	
	public void search (String query ) {
		
	}
	
	
}