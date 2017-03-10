package it.cnr.ilc.g2a.action.controller.importxml;

import it.cnr.ilc.g2a.datahandler.XMLHandler;
import it.cnr.ilc.g2a.model.importxml.xmlmapping.AddDocument;
import it.cnr.ilc.gtoa.model.xmlmapping.SequenceDocument;
import it.cnr.ilc.gtoa.model.xmlmapping.ElementDocument.Element;
import it.cnr.ilc.gtoa.model.xmlmapping.ParamDocument.Param;
import it.cnr.ilc.gtoa.model.xmlmapping.SequenceDocument.Sequence;
import it.cnr.ilc.g2a.utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

public class XMLImporter {

	// HashMap con chiave il nome dell'XML e valore la Resource (XML)
	private HashMap<String, Resource> resources = null; 

	private HashMap<String, SequenceDocument> tokensSequenceDocument = new HashMap<String, SequenceDocument>(1024*1024);
	private HashMap<String, SequenceDocument> pericopesSequenceDocument = new HashMap<String, SequenceDocument>(1024*1024);
	private SequenceDocument linksSequenceDocument = null;

	private List<Element> greekTS = new ArrayList<Element>(1024 * 1024);
	private List<Element> arabicTS = new ArrayList<Element>(1024 * 1024);

	/**
	 * @return the linksSequenceDocument
	 */
	public SequenceDocument getLinksSequenceDocument() {
		return linksSequenceDocument;
	}


	private XMLHandler handler = new XMLHandler();


	/**
	 * @return the resources
	 */
	public HashMap<String, Resource> getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(HashMap<String, Resource> resources) {
		this.resources = resources;
	}

	/**
	 * @return the tokensSequenceDocument
	 */
	public SequenceDocument getTokensSequenceDocument(String work) {
		return tokensSequenceDocument.get(work);
	}

	/**
	 * @param tokensSequenceDocument the tokensSequenceDocument to set
	 */
	public void setTokensSequenceDocument(SequenceDocument tokensSequenceDocument, String work) {
		this.tokensSequenceDocument.put(work, tokensSequenceDocument);
	}

	/**
	 * @return the pericopesSequenceDocument
	 */
	public SequenceDocument getPericopesSequenceDocument(String work) {
		return pericopesSequenceDocument.get(work);
	}

	/**
	 * @param pericopesSequenceDocument the pericopesSequenceDocument to set
	 */
	public void setPericopesSequenceDocument(
			SequenceDocument pericopesSequenceDocument, String work) {
		this.pericopesSequenceDocument.put(work, pericopesSequenceDocument);
	}

	public void setLinksSequenceDocument(
			SequenceDocument linksSequenceDocument) {
		this.linksSequenceDocument = linksSequenceDocument;
	}

	/**
	 * @return the greekTS
	 */
	public List<Element> getGreekTS() {
		return greekTS;
	}

	/**
	 * @param greekTS the greekTS to set
	 */
	public void setGreekTS(List<Element> greekTS) {
		this.greekTS = greekTS;
	}

	/**
	 * @return the arabicTS
	 */
	public List<Element> getArabicTS() {
		return arabicTS;
	}

	/**
	 * @param arabicTS the arabicTS to set
	 */
	public void setArabicTS(List<Element> arabicTS) {
		this.arabicTS = arabicTS;
	}

	public XMLResource loadResource(String collectionName, String resourceName) {

		return handler.getResource(collectionName, resourceName);
	}

	public void  loadTokens(String collectionName, String work, boolean append) {

		XMLResource xr = null;
		if (append) {
			if (null != (xr = loadResource(collectionName + System.getProperty("file.separator") + work, "tokens.xml"))){

				setTokensSequenceDocument(retrieveContentAsSequenceDocument(xr),work);	
			} else {
				setTokensSequenceDocument(SequenceDocument.Factory.newInstance(),work);	
			}
		} else {
			setTokensSequenceDocument(SequenceDocument.Factory.newInstance(),work);	
		}
	}

	public void loadPericopes (String collectionName, String work, boolean append) {
		XMLResource xr = null;
		if (append){
			if ( null != (xr = loadResource(collectionName + System.getProperty("file.separator") + work, "pericopes.xml"))) {
				setPericopesSequenceDocument(retrieveContentAsSequenceDocument(xr), work);
			} else {
				setPericopesSequenceDocument(SequenceDocument.Factory.newInstance(), work);
			}
		} else{
			setPericopesSequenceDocument(SequenceDocument.Factory.newInstance(), work);
		}
	}

	public void loadLinks (String collectionName, boolean append) {
		XMLResource xr = null;
		if (append) {
			if ( null != (xr = loadResource(collectionName + System.getProperty("file.separator"), "links.xml"))) {
				setLinksSequenceDocument(retrieveContentAsSequenceDocument(xr));
			} else {
				setLinksSequenceDocument(SequenceDocument.Factory.newInstance());
			}
		} else {
			setLinksSequenceDocument(SequenceDocument.Factory.newInstance());
		}
	}

	public void loadResources(String collectionName) {

		this.setResources(handler.getResources(collectionName));
		//return handler.getResources(collectionName);
	}

	public SequenceDocument retrieveContentAsSequenceDocument (Resource resource) {
		SequenceDocument sd = null;
		try {
			sd = (SequenceDocument) handler.loadDocument((XMLResource) resource, SequenceDocument.Factory.newInstance());

		} catch (XMLDBException ee) {

			ee.printStackTrace(); //TODO mettere qualche stampa di warning e basta
		} finally {
			if ( null == sd ) {
				sd = SequenceDocument.Factory.newInstance();
			}
		}

		return sd;
	}	

	public AddDocument retrieveContent(Resource resource) {
		// TODO Auto-generated method stub
		AddDocument ad = null;
		try {
			ad = (AddDocument) handler.loadDocument((XMLResource) resource, AddDocument.Factory.newInstance());

		} catch (XMLDBException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();

		} finally {
			if (null == ad){
				ad = AddDocument.Factory.newInstance();
			}
		}
		return ad;
	}



	private void updateLinkSequence (SequenceDocument oldXmlMap, SequenceDocument newXmlMap, String extended ) {

		/*		updateParamInSequence (oldXmlMap, newXmlMap);
		updateElementInSequence(oldXmlMap, newXmlMap);*/
		updateSequenceInSequence(oldXmlMap, newXmlMap, "links", extended );
	}

	private void updatePericopeSequence (SequenceDocument oldXmlMap, SequenceDocument newXmlMap, String extended ) {

		/*		updateParamInSequence (oldXmlMap, newXmlMap);
		updateElementInSequence(oldXmlMap, newXmlMap);*/
		updateSequenceInSequence(oldXmlMap, newXmlMap, "pericopes", extended );
	}


	private void updateTokenSequence (SequenceDocument oldXmlMap, SequenceDocument newXmlMap, String extended  ) {

		updateElementInSequence(oldXmlMap, newXmlMap, "pericopeTokens", extended);

	}

	private void createTokenSequenceFromList (List<Element> loe, SequenceDocument to, String extended){
		createSequenceFromList (loe, to,  "pericopeTokens", extended);
	}

	private void createSequenceFromList (List<Element> loe, SequenceDocument to, String classname, String extended){
		
		to.addNewSequence();
		to.getSequence().setClassname(classname);
		to.getSequence().setExtended(extended);
		    
		to.getSequence().setElementArray(loe.toArray(new Element[loe.size()]));
	}
	
	private void updateSequenceInSequence (SequenceDocument from, SequenceDocument to, String classname, String extended) {

		Sequence fromSequence = from.getSequence();
		List<Sequence> toListOfSequence = new ArrayList<Sequence>(1024 * 1024); 

		if (null!= to) {
			if ((null!= to.getSequence())) {
				Sequence[] toSequence = to.getSequence().getSequenceArray();
				toListOfSequence.addAll(Arrays.asList(toSequence));
			} else {
				to.addNewSequence();
				to.getSequence().setClassname(classname);
				to.getSequence().setExtended(extended);
			}
			toListOfSequence.addAll(Arrays.asList(fromSequence));
			to.getSequence().setSequenceArray(Arrays.copyOf(toListOfSequence.toArray(),toListOfSequence.toArray().length, Sequence[].class));
		} else {
			System.err.println("Casino grosso in updateSequenceInSequence");
		}
	}	


	private void updateElementInSequence (SequenceDocument from, SequenceDocument to, String classname, String extended) {

		Element[] fromElements = from.getSequence().getElementArray();
		List<Element> toListOfElements = new ArrayList<Element>(1024 * 1024); 
		if (null != to) {
			if ((null!= to.getSequence())) {
				Element[] toElements = to.getSequence().getElementArray();
				toListOfElements.addAll(Arrays.asList(toElements));
			} else {
				to.addNewSequence();
				to.getSequence().setClassname(classname);
				to.getSequence().setExtended(extended);
			}
			toListOfElements.addAll(Arrays.asList(fromElements));
			//	to.getSequence().setElementArray(Arrays.copyOf(toListOfElements.toArray(), toListOfElements.toArray().length, Element[].class));
			Element[] array = toListOfElements.toArray(new Element[toListOfElements.size()]);
			to.getSequence().setElementArray(array);
		} else {
			System.err.println("Casino grosso in updateElementInSequence");
		}
	}

	private void updateParamInSequence (SequenceDocument from, SequenceDocument to) {

		Param[] fromParams = from.getSequence().getParamArray();
		Param[] toParams = to.getSequence().getParamArray();
		List<Param> toListOfParams = new ArrayList<Param>(1024 * 1024); 
		toListOfParams.addAll(Arrays.asList(toParams));
		toListOfParams.addAll(Arrays.asList(fromParams));
		to.getSequence().setParamArray(Arrays.copyOf(toListOfParams.toArray(), toListOfParams.toArray().length, Param[].class ));
	}

	public void saveTokens (String collectionName, String work) {
		handler.save(collectionName + System.getProperty("file.separator") + work, getTokensSequenceDocument(work).toString(), "tokens.xml");
	}

	public void savePericopes (String collectionName,String work) {

		handler.save(collectionName + System.getProperty("file.separator") + work, getPericopesSequenceDocument(work).toString(), "pericopes.xml");
	}

	public void saveLinks (String collectionName) {

		handler.save(collectionName, getLinksSequenceDocument().toString(), "links.xml");
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		XMLImporter	importer = new XMLImporter();
		CharSequence greekCharBuffer;
		CharSequence arabicCharBuffer;
		//carico gli XML da importare
		importer.loadResources("testimporter"); //specificare il nome del db
		//carico gli XML nel quale esportare i vecchi XML

		String dbName = "new_GA2";
		
		importer.loadTokens(dbName + "/doc", "plot", false);
		importer.loadTokens(dbName + "/doc", "bada", false);
		importer.loadPericopes(dbName + "/doc", "plot", false);
		importer.loadPericopes(dbName + "/doc", "bada", false);
		importer.loadLinks(dbName + "/link", false);

		// select a class on the classpath that will used to load a resource
		try {
			//greekReader = new BufferedReader(new InputStreamReader(importer.getClass().getResourceAsStream("resources/greek.txt"),"UTF-8"));

			greekCharBuffer = Utils.fromFile ("WebContent/resources/greek.txt");
			//arabicCharBuffer = Utils.fromFile ("WebContent/resources/arabic.txt");
			arabicCharBuffer = Utils.fromFile ("/home/simone/Dropbox/GA/badaoui_revisionato/badaoui-merged.txt");
			//Map.Entry<String, String> entry : map.entrySet()

			//for (Resource resource: importer.getResources().values()) {
			for(Map.Entry<String, Resource> entry : importer.getResources().entrySet()){
				Resource resource = entry.getValue();
				System.err.print("resource name: " + entry.getKey());
				AddDocument ad = importer.retrieveContent(resource);
				LegacyXMLMapper converter = new LegacyXMLMapper(ad, greekCharBuffer, arabicCharBuffer);

				System.err.print(" new converter done => ");
				converter.run();
				System.err.print(" run done => ");


				Element[] arabicElementArray = converter.getArabicTokenSequence().getSequence().getElementArray();

				importer.getArabicTS().addAll(new ArrayList<Element>(Arrays.asList(arabicElementArray)));

				
				Element[] greekElementArray = converter.getGreekTokenSequence().getSequence().getElementArray();

				importer.getGreekTS().addAll(new ArrayList<Element>(Arrays.asList(greekElementArray)));
				
				/* da chiamare una volta sola dopo che ho fatto tutto perche' costa un casino in tempo
				importer.updateTokenSequence(converter.getArabicTokenSequence(), importer.getTokensSequenceDocument("bada"), "badaoui");
				importer.updateTokenSequence(converter.getGreekTokenSequence(), importer.getTokensSequenceDocument("plot"), "plotino");
				System.err.print(" udpated tokens => ");
				 */
				importer.updatePericopeSequence(converter.getArabicPericopeSequence(), importer.getPericopesSequenceDocument("bada"), "badaoui");
				importer.updatePericopeSequence(converter.getGreekPericopeSequence(), importer.getPericopesSequenceDocument("plot"), "plotino");
				System.err.print(" updated pericopes => ");

				importer.updateLinkSequence(converter.getGreekArabicLinkSequence(), importer.getLinksSequenceDocument(), "");
				System.err.println(" updated links ");

			}

			importer.createTokenSequenceFromList(importer.getArabicTS(),  importer.getTokensSequenceDocument("bada"),  "badaoui");
			importer.createTokenSequenceFromList(importer.getGreekTS(),  importer.getTokensSequenceDocument("plot"),  "plotino");
			
			importer.saveTokens (dbName + "/doc", "plot");
			importer.saveTokens (dbName + "/doc", "bada");

			importer.savePericopes(dbName + "/doc", "plot");
			importer.savePericopes(dbName + "/doc", "bada");

			importer.saveLinks(dbName + "/link" );

			//String greekTokenList = importer.getTokensSequenceDocument("plot").toString();
			//String greekPericopeList = importer.getPericopesSequenceDocument("plot").toString();

			//System.err.println(greekTokenList);
			//System.err.println(greekPericopeList);

			System.err.println("Done!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
