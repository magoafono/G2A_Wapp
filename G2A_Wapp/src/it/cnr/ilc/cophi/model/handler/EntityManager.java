package it.cnr.ilc.cophi.model.handler;
import it.cnr.ilc.cophi.action.controller.content.CommentContentBehaviour;
import it.cnr.ilc.cophi.action.controller.content.ContextContent;
import it.cnr.ilc.cophi.action.controller.content.DocumentTextContentBehaviour;
import it.cnr.ilc.cophi.action.controller.content.LinkContentBehaviour;
import it.cnr.ilc.cophi.action.controller.content.PericopeTextContentBehaviour;
import it.cnr.ilc.cophi.action.controller.resource.ContextResource;
import it.cnr.ilc.cophi.action.controller.resource.ResourceBehaviour;
import it.cnr.ilc.cophi.action.controller.resource.XMLResourceBehaviour;
import it.cnr.ilc.cophi.action.userbean.AnalysisBean;
import it.cnr.ilc.cophi.exception.BugException;
import it.cnr.ilc.cophi.exception.LanguageUnknownException;
import it.cnr.ilc.cophi.model.Factory;
import it.cnr.ilc.cophi.model.Link;
import it.cnr.ilc.cophi.model.Reference;
import it.cnr.ilc.cophi.model.comment.Comment;
import it.cnr.ilc.cophi.model.text.PericopeText;
import it.cnr.ilc.cophi.model.text.RefPericopeText;
import it.cnr.ilc.cophi.model.text.RefTokenText;
import it.cnr.ilc.cophi.model.text.TokenText;
import it.cnr.ilc.cophi.model.view.LinkViewEntity;
import it.cnr.ilc.cophi.model.view.ResultViewEntity;
import it.cnr.ilc.cophi.model.view.TokenViewEntity;
import it.cnr.ilc.cophi.utils.Consts;
import it.cnr.ilc.cophi.utils.CophiSort;
import it.cnr.ilc.cophi.utils.GreekCollator;
import it.cnr.ilc.cophi.utils.GreekCollator.CollatorEnum;
import it.cnr.ilc.cophi.utils.MessageProvider;
import it.cnr.ilc.cophi.utils.OntoUtils;
import it.cnr.ilc.cophi.utils.Utils;
import it.cnr.ilc.cophi.utils.XPathUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.slf4j.profiler.Profiler;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * 
 * @author simone
 *
 */
public class EntityManager {

	private static EntityManager instance;

	CharSequence greekCharBuffer = null;
	CharSequence arabicCharBuffer = null;

	private ContextResource arabicCr = null;
	private ContextResource greekCr = null;

	private ContextResource linkCr = null;
	private ContextResource commentCr = null;

	private HashMap<String,Link<RefPericopeText>> links = null;

	private HashMap<String,Comment> comments = null;
	private HashMap<String, List<Comment>> commentsByLink = null;

	private HashMap<String,PericopeText> arabicPericopes = null;
	private HashMap<String,PericopeText> greekPericopes = null;

	private HashMap<String,TokenText> greekTokens = null;
	private HashMap<String,TokenText> arabicTokens = null;

	private Document greekPericopesDom = null;
	private Document arabicPericopesDom = null;

	private List<TokenViewEntity> listOfArabicTokenView = null;
	private List<TokenViewEntity> listOfGreekTokenView = null;

	private HashMap<String, String> arabicTokenToPericope = null;
	private HashMap<String, String> greekTokenToPericope = null;

	private HashMap<String, String> pericopeToLink = null;

	//liste dei linkviewentity ordinate per lingua
	private List<LinkViewEntity> linksOrderByGreek = null;
	private List<LinkViewEntity> linksOrderByArabic = null;

	/*
	 * Lexicon models
	 */
	private  OntModel ontoModel = null;
	private  OntModel ontoExplicitModel = null;

	private  Map<String, String> ontoInstanceMap;
	private  Map<String, String> greek2EnglishMap;
	private  String ontoResult2JS = "";

	private  Map<String, String> ontoQueryInstanceMap;
	private  Map<String, String> objRelationMap;

	private String query_1_param_1 = "ObjectProperty";
	private String query_1_param_2 = "";
	private String query_2_param_1 = "Being";
	private String query_2_param_2 = "Forms";
	private String query_2_param_3 = "Intellect";
	private String query_3_param_1 = "Being";
	private String query_3_param_2 = "";

	static MessageProvider mp = new MessageProvider();


	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return mp.getValue( Consts.CONFIGNAME , "db_name");
	}

	public String getArabicCollectionPath() {

		return mp.getValue(Consts.CONFIGNAME, "db_arabic_doc_path");
	}
	public String getGreekCollectionPath() {

		return mp.getValue(Consts.CONFIGNAME, "db_greek_doc_path");
	}
	public String getLinkCollectionPath() {

		return mp.getValue(Consts.CONFIGNAME, "db_link_path");
	}
	public String getCommentCollectionPath() {

		return mp.getValue(Consts.CONFIGNAME, "db_comment_path");
	}

	public String getArabicWorkName() {

		return mp.getValue(Consts.CONFIGNAME, "arabic_work_name");
	}

	public String getGreekWorkName() {

		return mp.getValue(Consts.CONFIGNAME, "greek_work_name");
	}


	/**
	 * @return the arabicTokens
	 */
	public HashMap<String, TokenText> getArabicTokens() {
		return arabicTokens;
	}

	/**
	 * @param arabicTokens the arabicTokens to set
	 */
	public void setArabicTokens(HashMap<String, TokenText> arabicTokens) {
		this.arabicTokens = arabicTokens;
	}

	/**
	 * @return the arabicPericopes
	 */
	public HashMap<String, PericopeText> getArabicPericopes() {
		return arabicPericopes;
	}

	/**
	 * @param arabicPericopes the arabicPericopes to set
	 */
	public void setArabicPericopes(
			HashMap<String, PericopeText> arabicPericopes) {
		this.arabicPericopes = arabicPericopes;
	}

	/**
	 * @return the greekTokens
	 */
	public HashMap<String, TokenText> getGreekTokens() {
		return greekTokens;
	}

	/**
	 * @param greekTokens the greekTokens to set
	 */
	public void setGreekTokens(HashMap<String, TokenText> greekTokens) {
		this.greekTokens = greekTokens;
	}

	/**
	 * @return the greekPericopes
	 */
	public HashMap<String, PericopeText> getGreekPericopes() {
		return greekPericopes;
	}

	/**
	 * @param greekPericopes the greekPericopes to set
	 */
	public void setGreekPericopes(
			HashMap<String, PericopeText> greekPericopes) {
		this.greekPericopes = greekPericopes;
	}

	/**
	 * @return the links
	 */
	public HashMap<String, Link<RefPericopeText>> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(HashMap<String, Link<RefPericopeText>> links) {
		this.links = links;
	}

	/**
	 * @return the greekCharBuffer
	 */
	public CharSequence getGreekCharBuffer() {
		return greekCharBuffer;
	}

	/**
	 * @param greekCharBuffer the greekCharBuffer to set
	 */
	public void setGreekCharBuffer(CharSequence greekCharBuffer) {
		this.greekCharBuffer = greekCharBuffer;
	}

	/**
	 * @return the arabicCharBuffer
	 */
	public CharSequence getArabicCharBuffer() {
		return arabicCharBuffer;
	}

	/**
	 * @param arabicCharBuffer the arabicCharBuffer to set
	 */
	public void setArabicCharBuffer(CharSequence arabicCharBuffer) {
		this.arabicCharBuffer = arabicCharBuffer;
	}

	/**
	 * @return the commentCr
	 */
	public ContextResource getCommentCr() {
		return commentCr;
	}

	/**
	 * @param commentCr the commentCr to set
	 */
	public void setCommentCr(ContextResource commentCr) {
		this.commentCr = commentCr;
	}

	/**
	 * @return the comments
	 */
	public HashMap<String, Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(HashMap<String, Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the commentsByLink
	 */
	public HashMap<String, List<Comment>> getCommentsByLink() {
		return commentsByLink;
	}

	/**
	 * @param commentsByLink the commentsByLink to set
	 */
	public void setCommentsByLink(HashMap<String, List<Comment>> commentsByLink) {
		this.commentsByLink = commentsByLink;
	}

	/**
	 * @return the greekPericopesDom
	 */
	public Document getGreekPericopesDom() {
		return greekPericopesDom;
	}

	/**
	 * @param greekPericopesDom the greekPericopesDom to set
	 */
	public void setGreekPericopesDom(Document greekPericopesDom) {
		this.greekPericopesDom = greekPericopesDom;
	}

	/**
	 * @return the arabicPericopesDom
	 */
	public Document getArabicPericopesDom() {
		return arabicPericopesDom;
	}

	/**
	 * @param arabicPericopesDom the arabicPericopesDom to set
	 */
	public void setArabicPericopesDom(Document arabicPericopesDom) {
		this.arabicPericopesDom = arabicPericopesDom;
	}

	/**
	 * @return the listOfArabicTokenView
	 */
	public List<TokenViewEntity> getListOfArabicTokenView() {
		return listOfArabicTokenView;
	}

	/**
	 * @param listOfArabicTokenView the listOfArabicTokenView to set
	 */
	public void setListOfArabicTokenView(List<TokenViewEntity> listOfArabicTokenView) {
		this.listOfArabicTokenView = listOfArabicTokenView;
	}

	/**
	 * @return the listOfGreekTokenView
	 */
	public List<TokenViewEntity> getListOfGreekTokenView() {
		return listOfGreekTokenView;
	}

	/**
	 * @param listOfGreekTokenView the listOfGreekTokenView to set
	 */
	public void setListOfGreekTokenView(List<TokenViewEntity> listOfGreekTokenView) {
		this.listOfGreekTokenView = listOfGreekTokenView;
	}

	/**
	 * @return the arabicTokenToPericope
	 */
	public HashMap<String, String> getArabicTokenToPericope() {
		return arabicTokenToPericope;
	}

	/**
	 * @param arabicTokenToPericope the arabicTokenToPericope to set
	 */
	public void setArabicTokenToPericope(
			HashMap<String, String> arabicTokenToPericope) {
		this.arabicTokenToPericope = arabicTokenToPericope;
	}

	/**
	 * @return the greekTokenToPericope
	 */
	public HashMap<String, String> getGreekTokenToPericope() {
		return greekTokenToPericope;
	}

	/**
	 * @param greekTokenToPericope the greekTokenToPericope to set
	 */
	public void setGreekTokenToPericope(HashMap<String, String> greekTokenToPericope) {
		this.greekTokenToPericope = greekTokenToPericope;
	}


	/**
	 * @return the pericopeToLink
	 */
	public HashMap<String, String> getPericopeToLink() {
		return pericopeToLink;
	}

	/**
	 * @param pericopeToLink the pericopeToLink to set
	 */
	public void setPericopeToLink(HashMap<String, String> pericopeToLink) {
		this.pericopeToLink = pericopeToLink;
	}

	/**
	 * @return the tokens
	 */


	private EntityManager () {
		init();
	}

	public static EntityManager getInstance() {
		if (instance == null) {
			synchronized(EntityManager.class) {
				if (instance == null) {
					instance = new EntityManager();
				}
			}
		}

		return instance;
	}

	private void init() {

		//String root_path = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/");

		/*try {
			greekCharBuffer = Utils.fromFile (root_path + "resources/greek.txt");
			arabicCharBuffer = Utils.fromFile (root_path + "resources/arabic.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		setArabicCr(createContextResouce(new XMLResourceBehaviour(), getDbName()  + getArabicCollectionPath()/*"/doc/bada/"*/));
		setGreekCr(createContextResouce(new XMLResourceBehaviour(), getDbName() +  getGreekCollectionPath()/*"/doc/plot/"*/));
		setLinkCr(createContextResouce(new XMLResourceBehaviour(), getDbName() +  getLinkCollectionPath()/*"/link"*/));

		setCommentCr(createContextResouce(new XMLResourceBehaviour(), getDbName() +  getCommentCollectionPath()/*"/comment"*/));

		setArabicTokens(retriveAllArabicToken()); 
		setGreekTokens(retriveAllGreekToken());

		setArabicPericopes(retrieveArabicPericope());
		setGreekPericopes(retrieveGreekPericope());

		setLinks(retrieveAllLink(getArabicPericopes(), getGreekPericopes()));

		setComments(retrieveAllComments());

		setCommentsByLink(Utils.commentsGroupByLink(getComments()));


		//	loadPericopesAsDselectom();

		setArabicTokenToPericope(Utils.createToken2PericopeHM(getArabicPericopes()));
		setGreekTokenToPericope(Utils.createToken2PericopeHM(getGreekPericopes()));
		setPericopeToLink(Utils.createPericope2LinkHM(getLinks()));

		setListOfArabicTokenView(Utils.createTokenViewList (getArabicTokens(), getArabicPericopes(), getLinks(), getArabicTokenToPericope(), getPericopeToLink(), Consts.ARABIC, getArabicCharBuffer()));
		setListOfGreekTokenView(Utils.createTokenViewList (getGreekTokens(), getGreekPericopes(), getLinks(), getGreekTokenToPericope(), getPericopeToLink(), Consts.GREEK, getGreekCharBuffer()));
		//setListOfArabicTokenView(Utils.createTokenViewListByXquery (getArabicTokens(), getArabicPericopes(), getLinks(), Consts.ARABIC, getArabicCharBuffer()));
		//setListOfGreekTokenView(Utils.createTokenViewListByXquery (getGreekTokens(), getGreekPericopes(), getLinks(), Consts.GREEK, getGreekCharBuffer()));


		//Lexicon
		InputStream in = getClass().getClassLoader().getResourceAsStream("/it/cnr/ilc/cophi/resources/Plotino.owl");
		InputStream in2 = getClass().getClassLoader().getResourceAsStream("/it/cnr/ilc/cophi/resources/Plotino.owl");
		setOntoModel( ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF));
		getOntoModel().read(in, "RDF/XML");
		setOntoExplicitModel(ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM));
		getOntoExplicitModel().read(in2, "RDF/XML");
		initializeOntoMenu();
		initializeQuestion_1();
		initializeQuestion_3();

	}




	private void reloadGreekFromDB() {

		setGreekCr(createContextResouce(new XMLResourceBehaviour(), getDbName() + getGreekCollectionPath() /*"/doc/plot/"*/));
		setGreekPericopes(retrieveGreekPericope()); //Forse non serve? Ho tenuto allineato tutto in memoria
		setGreekTokenToPericope(Utils.createToken2PericopeHM(getGreekPericopes()));

	}

	private void reloadArabicFromDB() {

		setArabicCr(createContextResouce(new XMLResourceBehaviour(), getDbName() + getArabicCollectionPath() /*"/doc/bada/"*/));
		setArabicPericopes(retrieveArabicPericope());
		setArabicTokenToPericope(Utils.createToken2PericopeHM(getArabicPericopes()));
	}

	private void reloadLinksFromDB() {

		setLinks(retrieveAllLink(getArabicPericopes(), getGreekPericopes()));
		setPericopeToLink(Utils.createPericope2LinkHM(getLinks()));
	}

	//sostituisce la reloadAfterGreekPericopeModification
	public void updateAfterGreekPericopeModification(List<Integer> clickedTokenIndex, String pericopeId) {

		Utils.updateTokenViewList(getListOfGreekTokenView(), clickedTokenIndex, pericopeId, getGreekPericopes());

	}

	//sostituisce la  reloadAfterArabicPericopeModification
	public void updateAfterArabicPericopeModification(List<Integer> clickedTokenIndex, String pericopeId) {

		Utils.updateTokenViewList(getListOfArabicTokenView(), clickedTokenIndex, pericopeId, getArabicPericopes());

	}

	@Deprecated 
	public void reloadAfterGreekPericopeModification(List<Integer> clickedTokenIndex, String pericopeId) {
		//LENTA
		reloadGreekFromDB();

		reloadLinksFromDB(); //inutile se non si toccano i links

		//troppo lavoro inutile
		setListOfGreekTokenView(Utils.createTokenViewList (getGreekTokens(), getGreekPericopes(), getLinks(), getGreekTokenToPericope(), getPericopeToLink(), Consts.GREEK, getGreekCharBuffer()));

		//troppo lenta
		//	setListOfGreekTokenView(Utils.createTokenViewListByXquery (getGreekTokens(), getGreekPericopes(), getLinks(),  Consts.GREEK, getGreekCharBuffer()));
	}


	@Deprecated
	public void reloadAfterArabicPericopeModification() {
		reloadArabicFromDB();
		reloadLinksFromDB();
		setListOfArabicTokenView(Utils.createTokenViewList (getArabicTokens(), getArabicPericopes(), getLinks(), getArabicTokenToPericope(), getPericopeToLink(), Consts.ARABIC, getArabicCharBuffer()));
		//setListOfArabicTokenView(Utils.createTokenViewListByXquery(getArabicTokens(), getArabicPericopes(), getLinks(), Consts.ARABIC, getArabicCharBuffer()));
	}

	/*
	 * Vecchia versione nella quale non si faceva distinzione di ricaricare Arabo e Greco quando si modifica la pericopatura nell'altra lingua
	 * @Deprecated
	 */

	@Deprecated
	public void reloadAfterPericopeModificationOld() {

		setArabicCr(createContextResouce(new XMLResourceBehaviour(), getDbName() + getArabicCollectionPath() /*"/doc/bada/"*/));
		setGreekCr(createContextResouce(new XMLResourceBehaviour(), getDbName() + getGreekCollectionPath() /*"/doc/plot/"*/));

		setArabicPericopes(retrieveArabicPericope());
		setGreekPericopes(retrieveGreekPericope());
		setLinks(retrieveAllLink(getArabicPericopes(), getGreekPericopes()));


		setArabicTokenToPericope(Utils.createToken2PericopeHM(getArabicPericopes()));
		setGreekTokenToPericope(Utils.createToken2PericopeHM(getGreekPericopes()));
		setPericopeToLink(Utils.createPericope2LinkHM(getLinks()));

		setListOfArabicTokenView(Utils.createTokenViewList (getArabicTokens(), getArabicPericopes(), getLinks(), getArabicTokenToPericope(), getPericopeToLink(), Consts.ARABIC, getArabicCharBuffer()));
		setListOfGreekTokenView(Utils.createTokenViewList (getGreekTokens(), getGreekPericopes(), getLinks(), getGreekTokenToPericope(), getPericopeToLink(), Consts.GREEK, getGreekCharBuffer()));
	}

	public void saveAllPericopes() {

		saveGreekPericopes();
		saveArabicPericopes();
	}
	/**
	 * Nuova save che usa direttamente exist-db con xquery invece di ricostruire tutto l'XML e salvarlo (metodo lentissimo!)
	 * @param focusedPericopeId id della pericope oggetto della modifica (focused)
	 * @param listOfPericopesId ids delle pericopi coivolte nella pericope (solitamente dalle quali ho tolto tokens)
	 */
	public void saveModifiedGreekPericopes(String focusedPericopeId, List<String> listOfPericopesId) {
		//TODO 
		//List<PericopeText> lor, String work, ContextResource cr, String collection, String filename
		//aggiungo la focused pericope alle altre che vanno salvate tramite xquery/xupdate
		listOfPericopesId.add(focusedPericopeId);
		updateModifiedPericopes(listOfPericopesId, Utils.getPericopeTextFromIds(listOfPericopesId, getGreekPericopes()), getGreekWorkName(), getGreekCr(), getDbName() + getGreekCollectionPath(),  "pericopes.xml");

	}

	public void saveModifiedArabicPericopes(String focusedPericopeId, List<String> listOfPericopesId) {
		//TODO 
		listOfPericopesId.add(focusedPericopeId);
		updateModifiedPericopes(listOfPericopesId, Utils.getPericopeTextFromIds(listOfPericopesId, getArabicPericopes()), getArabicWorkName(), getArabicCr(), getDbName() + getArabicCollectionPath(),  "pericopes.xml");
	}

	public void saveGreekPericopes() {

		savePericopes(new ArrayList<PericopeText>(getGreekPericopes().values()), getGreekWorkName(), getGreekCr(), getDbName() + getGreekCollectionPath() /*"/doc/plot/"*/, "pericopes.xml");
	}

	public void saveArabicPericopes() {

		savePericopes(new ArrayList<PericopeText>(getArabicPericopes().values()), getArabicWorkName(), getArabicCr(), getDbName() + getArabicCollectionPath() /*"/doc/bada/"*/, "pericopes.xml");
	}


	private ContextResource createContextResouce(ResourceBehaviour rb, String dbPath) {
		ContextResource cr = Factory.getInstanceContextResource();
		cr.setResourceBehaviourType(rb);
		cr.loadResources(dbPath);
		return cr;
	}


	//tokens
	public HashMap<String,TokenText> retriveAllArabicToken () {

		return retrieveAllToken(getArabicCr()); 
	}

	public HashMap<String,TokenText> retriveAllGreekToken () {

		return retrieveAllToken(getGreekCr()); 
	}

	private HashMap<String,TokenText> retrieveAllToken (ContextResource cr) {

		ContextContent<TokenText> cc = Factory.getContextContentInstance("TokenText");
		cc.setContentType(new DocumentTextContentBehaviour(cr.retrieveContent("tokens.xml")));
		return cc.getValue();
	}

	//pericopes
	public HashMap<String,PericopeText> retrieveArabicPericope () {
		return retrieveAllPericope (getArabicCr(), getArabicTokens());
	}

	public HashMap<String,PericopeText> retrieveGreekPericope () {
		return retrieveAllPericope (getGreekCr(), getGreekTokens());
	}


	private HashMap<String,PericopeText> retrieveAllPericope (ContextResource cr, HashMap<String, TokenText> tokens) {

		ContextContent<PericopeText> cc = Factory.getContextContentInstance("PericopeText");
		PericopeTextContentBehaviour ptcb = new PericopeTextContentBehaviour(cr.retrieveContent("pericopes.xml"));
		ptcb.setTokens(tokens);
		cc.setContentType(ptcb);

		return cc.getValue();
	}

	//links
	public HashMap<String,Link<RefPericopeText>> retrieveAllLink (HashMap<String, PericopeText> pericopesAr, HashMap<String, PericopeText> pericopesGr) {

		ContextContent<Link<RefPericopeText>> cc = Factory.getContextContentInstance("Link");
		LinkContentBehaviour lcb = new LinkContentBehaviour(getLinkCr().retrieveContent("links.xml"));
		HashMap<String, PericopeText> pericopes = new HashMap<String, PericopeText>();
		pericopes.putAll(pericopesGr);
		pericopes.putAll(pericopesAr);
		lcb.setPericopes(pericopes);
		cc.setContentType(lcb);

		return cc.getValue();
	}

	private HashMap<String, Comment> retrieveAllComments() {
		ContextContent<Comment> cc = Factory.getContextContentInstance("Comment");
		CommentContentBehaviour ccb = new CommentContentBehaviour();
		ccb.setResources(getCommentCr().getResources());
		cc.setContentType(ccb);

		return cc.getValue();
	}


	public void retriveAllComment () {

	}

	/**
	 * @return the arabicCr
	 */
	public ContextResource getArabicCr() {
		return arabicCr;
	}

	/**
	 * @param arabicCr the arabicCr to set
	 */
	public void setArabicCr(ContextResource arabicCr) {
		this.arabicCr = arabicCr;
	}

	/**
	 * @return the greekCr
	 */
	public ContextResource getGreekCr() {
		return greekCr;
	}

	/**
	 * @param greekCr the greekCr to set
	 */
	public void setGreekCr(ContextResource greekCr) {
		this.greekCr = greekCr;
	}

	public Link<Reference> getLink(String id) {

		return null;
	}

	/**
	 * @return the linkCr
	 */
	public ContextResource getLinkCr() {
		return linkCr;
	}

	/**
	 * @param linkCr the linkCr to set
	 */
	public void setLinkCr(ContextResource linkCr) {
		this.linkCr = linkCr;
	}

	/**
	 * @return the ontoModel
	 */
	public OntModel getOntoModel() {
		return ontoModel;
	}

	/**
	 * @param ontoModel the ontoModel to set
	 */
	public void setOntoModel(OntModel ontoModel) {
		this.ontoModel = ontoModel;
	}

	/**
	 * @return the ontoExplicitModel
	 */
	public OntModel getOntoExplicitModel() {
		return ontoExplicitModel;
	}

	/**
	 * @param ontoExplicitModel the ontoExplicitModel to set
	 */
	public void setOntoExplicitModel(OntModel ontoExplicitModel) {
		this.ontoExplicitModel = ontoExplicitModel;
	}

	/**
	 * @return the ontoInstanceMap
	 */
	public Map<String, String> getOntoInstanceMap() {
		return ontoInstanceMap;
	}

	/**
	 * @param ontoInstanceMap the ontoInstanceMap to set
	 */
	public void setOntoInstanceMap(Map<String, String> ontoInstanceMap) {
		this.ontoInstanceMap = ontoInstanceMap;
	}


	/**
	 * @return the greek2EnglishMap
	 */
	public Map<String, String> getGreek2EnglishMap() {
		return greek2EnglishMap;
	}

	/**
	 * @param greek2EnglishMap the greek2EnglishMap to set
	 */
	public void setGreek2EnglishMap(Map<String, String> greek2EnglishMap) {
		this.greek2EnglishMap = greek2EnglishMap;
	}

	/**
	 * @return the ontoResult2JS
	 */
	public String getOntoResult2JS() {
		return ontoResult2JS;
	}

	/**
	 * @param ontoResult2JS the ontoResult2JS to set
	 */
	public void setOntoResult2JS(String ontoResult2JS) {
		this.ontoResult2JS = ontoResult2JS;
	}

	/**
	 * @return the linksOrderByGreek
	 */
	public List<LinkViewEntity> getLinksOrderByGreek() {
		return linksOrderByGreek;
	}

	/**
	 * @param linksOrderByGreek the linksOrderByGreek to set
	 */
	public void setLinksOrderByGreek(List<LinkViewEntity> linksOrderByGreek) {
		this.linksOrderByGreek = linksOrderByGreek;
	}

	/**
	 * @return the linksOrderByArabic
	 */
	public List<LinkViewEntity> getLinksOrderByArabic() {
		return linksOrderByArabic;
	}

	/**
	 * @param linksOrderByArabic the linksOrderByArabic to set
	 */
	public void setLinksOrderByArabic(List<LinkViewEntity> linksOrderByArabic) {
		this.linksOrderByArabic = linksOrderByArabic;
	}

	public List<String> getArabicTokensAsList() {

		ArrayList<String> al = new ArrayList<String>();

		for (TokenText tt : getArabicTokens().values()) {
			//al.add(tt.getText(getArabicCharBuffer()));
			al.add(tt.getText());
		}

		return al;
	}

	public List<String> getGreekTokensAsList() {

		ArrayList<String> al = new ArrayList<String>();

		for (TokenText tt : getGreekTokens().values()) {
			//	al.add(tt.getText(getGreekCharBuffer()));
			al.add(tt.getText());
		}

		return al;
	}


	public LinkViewEntity getLinkViewEntityByLinkId(String linkId) {

		Link<RefPericopeText> link = getLinks().get(linkId);

		return getLinkAsLinkViewEntity(link);
		//		LinkViewEntity lve = new LinkViewEntity();
		//		lve.setLink(link);
		//
		//		RefPericopeText grRpt = link.getValue().get(Consts.GREEK);
		//		PericopeText grPericope = (PericopeText) grRpt.getPericope();
		//		if (null != grPericope) {
		//			lve.setGreekPericopeInfo(grPericope.getInfo());
		//			lve.setGreekPericopeText(grPericope.getText());
		//			lve.setGreekPericopeRefId(grRpt.getRef());
		//			lve.setGreekPericopeId(grRpt.getId().substring(4)); //tolgo ref_
		//			lve.setGreekPericopeHidden(Consts.HIDDEN_REF_PERICOPE.equals(grRpt.getClassname()));
		//		} else {
		//			System.err.println("Pericope greca non trovata in links.xml! " + grRpt.getId() + " ref " + grRpt.getRef());
		//		}
		//		RefPericopeText arRpt = link.getValue().get(Consts.ARABIC);
		//		PericopeText arPericope = (PericopeText) arRpt.getPericope();
		//		if (null != arPericope) {
		//			lve.setArabicPericopeInfo(arPericope.getInfo());
		//
		//			lve.setArabicPericopeText(arPericope.getText());
		//			lve.setArabicPericopeRefId(arRpt.getRef());
		//			lve.setArabicPericopeId(arRpt.getId().substring(4));
		//			lve.setArabicPericopeHidden(Consts.HIDDEN_REF_PERICOPE.equals(arRpt.getClassname()));
		//		} else {
		//			System.err.println("Pericope araba non trovata in links.xml! " + arRpt.getId() + " ref " + arRpt.getRef());
		//		}
		//
		//		return lve;
	}

	public LinkViewEntity getLinkAsLinkViewEntity(Link<RefPericopeText> link) {

		LinkViewEntity lve = new LinkViewEntity();
		lve.setLink(link);
		if (null != getCommentsByLink().get(link.getId())){
			lve.setNoOfComments(getCommentsByLink().get(link.getId()).size()); //numero di commenti
		}
		RefPericopeText grRpt = link.getValue().get(Consts.GREEK);
		PericopeText grPericope = (PericopeText) grRpt.getPericope();
		if (null != grPericope) {
			lve.setGreekPericopeInfo(grPericope.getInfo());
			lve.setGreekPericopeText(grPericope.getText().replaceAll("\\s*↲\\s*", "\n"));
			lve.setGreekPericopeRefId(grRpt.getRef());
			lve.setGreekPericopeId(grRpt.getId().substring(4)); //tolgo ref_
			lve.setGreekPericopeHidden(Consts.HIDDEN_REF_PERICOPE.equals(grRpt.getClassname()));
		} else {
			System.err.println("Pericope greca non trovata in links.xml! Gr Pericope ID: " + grRpt.getId() + " Gr Pericope ref " + grRpt.getRef());
		}
		RefPericopeText arRpt = link.getValue().get(Consts.ARABIC);
		PericopeText arPericope = (PericopeText) arRpt.getPericope();

		if (null != arPericope) {
			lve.setArabicPericopeInfo(arPericope.getInfo());
			lve.setArabicPericopeText(arPericope.getText().replaceAll("\\s*↳\\s*", "\n"));
			lve.setArabicPericopeRefId(arRpt.getRef());
			lve.setArabicPericopeId(arRpt.getId().substring(4));
			lve.setArabicPericopeHidden(Consts.HIDDEN_REF_PERICOPE.equals(arRpt.getClassname()));
		} else {
			System.err.println("Pericope araba non trovata in links.xml! " + arRpt.getId() + " ref " + arRpt.getRef());
		}

		return lve;
	}

	public List<LinkViewEntity> getLinksAsList(int langId) {

		List<LinkViewEntity> lvel = new ArrayList<LinkViewEntity>();
		for (Link<RefPericopeText> link : getLinks().values()) {

			lvel.add(getLinkAsLinkViewEntity(link));
		}

		switch (langId) {
		case Consts.GREEK:
			Collections.sort(lvel, CophiSort.GREEKLINKVIEW_FROM_ORDER);
			setLinksOrderByGreek(lvel);
			break;
		case Consts.ARABIC:
			Collections.sort(lvel, CophiSort.ARABICLINKVIEW_FROM_ORDER);
			setLinksOrderByArabic(lvel);
			break;

		default:
			break;
		}

		return lvel;
	}

	public List<Comment> getLinkComments(String linkId) {

		return getCommentsByLink().get(linkId);
	}

	public List<Comment> getOrderedLinkComments(String linkId) {

		List<Comment> unsorted = getLinkComments(linkId);
		if (null != unsorted) {
			Collections.sort(unsorted, CophiSort.COMMENT_ID_ORDER);
		}
		return unsorted;
	}

	/**
	 * @return the ontoQueryInstanceMap
	 */
	public Map<String, String> getOntoQueryInstanceMap() {
		return ontoQueryInstanceMap;
	}

	/**
	 * @param ontoQueryInstanceMap the ontoQueryInstanceMap to set
	 */
	public void setOntoQueryInstanceMap(Map<String, String> ontoQueryInstanceMap) {
		this.ontoQueryInstanceMap = ontoQueryInstanceMap;
	}

	/**
	 * @return the objRelationMap
	 */
	public Map<String, String> getObjRelationMap() {
		return objRelationMap;
	}

	/**
	 * @param objRelationMap the objRelationMap to set
	 */
	public void setObjRelationMap(Map<String, String> objRelationMap) {
		this.objRelationMap = objRelationMap;
	}

	/**
	 * @return the query_1_param_1
	 */
	public String getQuery_1_param_1() {
		return query_1_param_1;
	}

	/**
	 * @param query_1_param_1 the query_1_param_1 to set
	 */
	public void setQuery_1_param_1(String query_1_param_1) {
		this.query_1_param_1 = query_1_param_1;
	}

	/**
	 * @return the query_1_param_2
	 */
	public String getQuery_1_param_2() {
		return query_1_param_2;
	}

	/**
	 * @param query_1_param_2 the query_1_param_2 to set
	 */
	public void setQuery_1_param_2(String query_1_param_2) {
		this.query_1_param_2 = query_1_param_2;
	}

	/**
	 * @return the query_2_param_1
	 */
	public String getQuery_2_param_1() {
		return query_2_param_1;
	}

	/**
	 * @param query_2_param_1 the query_2_param_1 to set
	 */
	public void setQuery_2_param_1(String query_2_param_1) {
		this.query_2_param_1 = query_2_param_1;
	}

	/**
	 * @return the query_2_param_2
	 */
	public String getQuery_2_param_2() {
		return query_2_param_2;
	}

	/**
	 * @param query_2_param_2 the query_2_param_2 to set
	 */
	public void setQuery_2_param_2(String query_2_param_2) {
		this.query_2_param_2 = query_2_param_2;
	}

	/**
	 * @return the query_2_param_3
	 */
	public String getQuery_2_param_3() {
		return query_2_param_3;
	}

	/**
	 * @param query_2_param_3 the query_2_param_3 to set
	 */
	public void setQuery_2_param_3(String query_2_param_3) {
		this.query_2_param_3 = query_2_param_3;
	}

	/**
	 * @return the query_3_param_1
	 */
	public String getQuery_3_param_1() {
		return query_3_param_1;
	}

	/**
	 * @param query_3_param_1 the query_3_param_1 to set
	 */
	public void setQuery_3_param_1(String query_3_param_1) {
		this.query_3_param_1 = query_3_param_1;
	}

	/**
	 * @return the query_3_param_2
	 */
	public String getQuery_3_param_2() {
		return query_3_param_2;
	}

	/**
	 * @param query_3_param_2 the query_3_param_2 to set
	 */
	public void setQuery_3_param_2(String query_3_param_2) {
		this.query_3_param_2 = query_3_param_2;
	}

	public boolean saveComment(Comment comment) {

		ContextContent<Comment> cc = Factory.getContextContentInstance("Comment");
		CommentContentBehaviour ccb = new CommentContentBehaviour();
		cc.setContentType(ccb);
		cc.contentToJDOM(comment);
		ContextResource crc = getCommentCr();
		return crc.store(cc.getDocument(), getDbName() + getCommentCollectionPath(), Utils.createFilename(comment));
	}

	public boolean deleteComment(Comment comment) {

		ContextResource crc = getCommentCr();
		return crc.remove(getDbName() + getCommentCollectionPath(), comment.getXmlFileName());
	}


	public void reloadAllComments() {
		setCommentCr(createContextResouce(new XMLResourceBehaviour(), getDbName() + getCommentCollectionPath()));
		setComments(retrieveAllComments());
		setCommentsByLink(Utils.commentsGroupByLink(getComments()));
	}

	/*
	public void loadPericopesAsDom () {

		setGreekPericopesDom(Utils.createPericopeDom (getLinks(), Consts.GREEK, getGreekCharBuffer()));
		setArabicPericopesDom(Utils.createPericopeDom (getLinks(), Consts.ARABIC, getArabicCharBuffer())); 
	}
	 */
	public String pericopeDomAsString (int langId) {

		String ret = null;
		switch (langId) {
		case Consts.GREEK:
			ret = Utils.JDOMtoString(getGreekPericopesDom());
			break;

		case Consts.ARABIC:
			ret = Utils.JDOMtoString(getArabicPericopesDom());
			break;

		default:
			break;
		}
		return ret;
	}

	/**
	 * It aligns the internal structures and the XML DB to the change made by a user to pericopes 
	 * 
	 * @param tokensId List of tokens' id to be treated 
	 * @param focusedPericopeId Pericope's id selected by the user
	 * @param listOfPericopesId Pericopes' list Id that owns tokens
	 * @param action Add, Remove or Shift action (as integer constant)
	 * @param langId Greek or Arabic (as integer constant)
	 */
	public void handlePericope (List<String> tokensId, String focusedPericopeId, List<String> listOfPericopesId, int action, int langId){

		switch (action) {
		case Consts.ADDTOPERICOPE:
			removeTokensFromPericopes (tokensId, listOfPericopesId, langId);
			addTokensToPericope(tokensId, focusedPericopeId, langId);
			break;

		case Consts.REMFROMPERICOPE: 
			/*
			 * TODO Siamo sicuri che serva? Se si mantiene va gestito il fatto che i token "removed" devono andare in una "nopericope"
			 * che può non esistere.
			 * 
			 */
			//listOfPericopesId.add(focusedPericopeId);
			removeTokensFromPericopes (tokensId, listOfPericopesId, langId);
			break;

		case Consts.SHIFTTOPERICOPE:
			removeTokensFromPericope (tokensId, focusedPericopeId, langId);
			addTokensToPericope(tokensId, listOfPericopesId.get(0), langId);
			break;
		default:
			break;
		}

	}

	private List<PericopeText> getPericopesByIDAndLang (List<String> ids, int langId) {

		List<PericopeText> lopt = new ArrayList<PericopeText>();
		for (String id : ids) {
			lopt.add(getPericopeByIDAndLang (id, langId));
		}

		return lopt;
	}



	private PericopeText getPericopeByIDAndLang (String id, int langId) {

		PericopeText pericope = null;

		switch (langId) {
		case Consts.GREEK:
			pericope = getGreekPericopes().get(id);
			break;
		case Consts.ARABIC:
			pericope = getArabicPericopes().get(id);
			break;

		default:
			break;
		}

		return pericope;
	}

	private HashMap<String, Reference> convertReferenceListToHashMap (List<Reference> list) {


		HashMap<String,Reference> map = new HashMap<String, Reference>();
		for (Reference i : list) {
			map.put(i.getRef(), i);
		}

		return map;

	}

	/**
	 * It removes tokens identified by a list of tokenId from its pericopes
	 * @param tokensId
	 * @param listOfPericopesId
	 * @param langId
	 */
	private void removeTokensFromPericopes(List<String> tokensId,
			List<String> listOfPericopesId, int langId) {

		if (null != listOfPericopesId)  {
			List<PericopeText> lopt = getPericopesByIDAndLang(listOfPericopesId, langId);

			for (PericopeText pericope : lopt) {
				if (null != pericope)
					pericope.setValue(removeTokensFromPericope(tokensId, pericope.getId(), langId));
			}
		}
	}

	/**
	 * Remove from the pericope idenfied by pericopesId the tokens listed in tokensId list
	 * @param tokensId the list of token to be removed
	 * @param pericopesId the ID of the pericope invoved by the operation
	 * @param langId language ID 
	 * @return
	 */
	private List<Reference> removeTokensFromPericope(List<String> tokensId, String pericopesId, int langId) {

		PericopeText pericope = getPericopeByIDAndLang(pericopesId, langId);
		List<Reference> newList = new ArrayList<Reference>();
		for (Reference ref : pericope.getValue()) {

			if (!tokensId.contains(ref.getRef())) {
				newList.add(ref);

			} else {
				//System.err.println("removeFromPericope(): da eliminare!!!");
			}
		}
		return newList;

	}

	private TokenText getTokenByIDAndLang (String tokenId, int langId) {

		TokenText ret = null;
		switch (langId) {
		case Consts.GREEK:
			ret = getGreekTokens().get(tokenId);
			break;
		case Consts.ARABIC:
			ret = getArabicTokens().get(tokenId);
			break;

		default:
			break;
		}
		return ret;
	}

	/**
	 * It adds tokens listed in tokensId list into the pericope with Id focusedPericopeId
	 * @param tokensId list of token ids
	 * @param focusedPericopeId ID of the focused pericope (in the pericope manager)
	 * @param langId
	 */
	private void addTokensToPericope(List<String> tokensId, String focusedPericopeId, int langId) {

		PericopeText pericope = getPericopeByIDAndLang(focusedPericopeId, langId);

		List<Reference> tokens = pericope.getValue();

		HashMap<String,Reference> map = convertReferenceListToHashMap(tokens);

		for (String id : tokensId) {
			if(!map.containsKey(id)){
				RefTokenText ref = new RefTokenText();
				ref.setId("ref_" + id);
				ref.setTok(getTokenByIDAndLang(id, langId));
				ref.setRef(id);
				ref.setExtended(getTokenByIDAndLang(id, langId).getText());
				tokens.add(ref);
			}
		}

		Collections.sort(tokens, CophiSort.TOKENREF_FROM_ORDER);
		pericope.setValue(tokens); 
	}


	public boolean savePericopes(List<PericopeText> lor, String work, ContextResource cr, String collection, String filename) {

		ContextContent<PericopeText> cc = Factory.getContextContentInstance("PericopeText");
		PericopeTextContentBehaviour pcb = new PericopeTextContentBehaviour();
		cc.setContentType(pcb);
		cc.contentToSequenceDocument(lor, work); //FIXME LENTA

		return cr.store(cc.getDocument(), collection, filename);

	}


	/* 
	 * Xquery based solution for save modified pericopes
	 */

	public void updateModifiedPericopes (List<String> pericopesId, ArrayList<PericopeText> ptlist, String work, ContextResource cr, String collection, String filename) {

		//cr.store(cc.getDocument(), collection, filename);
		ContextContent<PericopeText> cc = Factory.getContextContentInstance("PericopeText");
		PericopeTextContentBehaviour pcb = new PericopeTextContentBehaviour();
		cc.setContentType(pcb);

		PericopeText pt = null;
		for (int i=0; i < ptlist.size(); i++) {
			pt = ptlist.get(i);
			cc.referenceToString(pt); 
			//id della pericope da sostituire , XML con cui sostituire , nome della collezione, nome del file XML
			cr.xqupdate(pericopesId.get(i), cc.getXmlString(),  collection, filename);
		}
	}


	/* 
	 * Xquery based solution for retrieve linguistic analysis
	 */

	public List<AnalysisBean> getGreekAnalysisByPericopeId (String pericopeId) {

		return XPathUtils.getGreekAnalysisByPericopeId(getDbName() + getGreekCollectionPath(), pericopeId);
	}


	public List<AnalysisBean> getArabicAnalysisByPericopeId (String pericopeId) {

		return XPathUtils.getArabicAnalysisByPericopeId(getDbName() + getArabicCollectionPath(), pericopeId);

	}

	/**
	 * Ricerca semplice. Restituisce l'insieme delle coppie di pericopi che soddisfano i criteri di ricerca.
	 * @param word Termine da cercare. La tipologia (forma/lemma/radice) è stabilita con <i>type</i>
	 * @param type Tipologia (forma/lemma/radice) di interpretazione della <i>word</>
	 * @param pos Part-of-Speech associata alla <i>word</i> (
	 * @return
	 * @throws BugException 
	 */

	//	public HashMap<String,ResultViewEntity> simpleSearchLinksByTokens (String word, String type, String pos, int lang) throws BugException {
	public HashMap<String,ResultViewEntity> simpleSearchLinksByTokens (List<String> itemName, List<String> itemValue, int lang) throws BugException {

		List<TokenViewEntity> grlotve = null;
		List<TokenViewEntity> arlotve = null;
		List<String> tokenIds = null;
		HashMap<String,ResultViewEntity> results = new HashMap<String,ResultViewEntity>(); //empty result

		try {
			Profiler profiler = new Profiler("simpleSearchLinksByTokens");
			
			if (XPathUtils.existsMorphoFile(getDbName(), lang)) {
				profiler.start("XPathUtils.simpleSearchTokenIdsByMopho");
				tokenIds = XPathUtils.simpleSearchTokenIdsByMopho(getDbName(), itemName, itemValue, lang);
			} else {
				profiler.start("XPathUtils.simpleSearchTokenIdsByMopho");
				tokenIds = XPathUtils.simpleSearchTokenIdsByForma(getDbName(), itemValue, lang);
			}
			
			profiler.start("XPathUtils.simpleSearchPericopeIdsByTokenIds");
			List<String> pericopeIds = XPathUtils.simpleSearchPericopeIdsByTokenIds(getDbName(), tokenIds, lang);

			profiler.start("XPathUtils.simpleSearchLinkIdsByPericopeIds");
			List<String> linkIds = XPathUtils.simpleSearchLinkIdsByPericopeIds(getDbName(), pericopeIds, lang);

			//Per ogni linkIds devo recuperare il testo delle due pericopi e poi il testo
			//1- recupero le pericopi di linkIds
			for (String linkId : linkIds) {
				Link<RefPericopeText> link = getLinks().get(linkId);
				//Sia per il link alla pericope greca che a quella araba prendo le pericopi

				if (null!= link) {
					profiler.start("simpleSearchLinksByTokens(): " + linkId);

					//GREEK
					String grPericopeId = link.getValue().get(Consts.GREEK).getRef();
					String grClassname = link.getValue().get(Consts.GREEK).getClassname(); //hiddenRefPericope or refPericope

					//PericopeText greekPericope = greekPericopes.get(grPericopeId);
					//				if ("pericope".equals(greekPericope.getClassname())) {
					if ("refPericope".equals(grClassname)){

						grlotve = Utils.createTokenViewListByPericopeId(getGreekTokens(), getGreekPericopes(), grPericopeId, tokenIds);
					} else {
						grlotve = null;//non va visualizzata
					}
					//ARABIC
					String arPericopeId = link.getValue().get(Consts.ARABIC).getRef();
					String arClassname = link.getValue().get(Consts.ARABIC).getClassname(); //hiddenRefPericope or refPericope
					//PericopeText arabicPericope = arabicPericopes.get(arPericopeId);
					//		if ("pericope".equals(arabicPericope.getClassname())) {
					if ("refPericope".equals(arClassname)){
						arlotve = Utils.createTokenViewListByPericopeId(getArabicTokens(), getArabicPericopes(), arPericopeId, tokenIds);
					} else {
						arlotve = null;//non va visualizzata
					}
					/*if (null == results) {
						results = new HashMap<String,ResultViewEntity>();
					}*/
					ResultViewEntity rve = new ResultViewEntity();
					rve.setLinkId(linkId);
					if (null != arlotve) {
						rve.setArabicTVE(arlotve);
						rve.setArPericopeId(arPericopeId);
						rve.setArPericopeInfo(getArabicPericopes().get(arPericopeId).getInfo());
					}
					if (null != grlotve) {
						rve.setGreekTVE(grlotve);
						rve.setGrPericopeId(grPericopeId);
						rve.setGrPericopeInfo(getGreekPericopes().get(grPericopeId).getInfo());
					}
					//results.add(rve);
					results.put(linkId,rve);

				} else {
					throw new BugException("link ID is null!");
				}

			}
			profiler.stop().print();

		} catch (LanguageUnknownException e) {
			e.printStackTrace();
		}

		return 	results;		


	}


	//LEXICON

	public  void initializeOntoMenu() {
		String queryString = null;
		ontoInstanceMap = new  LinkedHashMap<String, String>();
		greek2EnglishMap = new  LinkedHashMap<String, String>();
		List<String> wordToSort =  new ArrayList<String>();
		String greekTerm = null;
		String englishTerm = null; 

		queryString = Consts.NAMESPACES
				+ "SELECT ?gTerm ?termine "
				+ "WHERE { ?termine plotino:greekTerm ?gTerm . } "
				+ "ORDER BY ?gTerm";
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, getOntoModel());
		for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
			QuerySolution binding = rs.nextSolution();
			greekTerm = binding.get("gTerm").toString();
			englishTerm = binding.get("termine").toString().split("#")[1];
			System.err.println(greekTerm + " " + englishTerm);
			wordToSort.add(greekTerm + "|" + englishTerm);
		}
		GreekCollator.sort(wordToSort, CollatorEnum.Primary);
		for (String s : wordToSort) {
			greekTerm = s.split("\\|")[0];
			englishTerm = s.split("\\|")[1]; 

			//System.err.println(greekTerm + " <> " + englishTerm);
			ontoInstanceMap.put(greekTerm, greekTerm);
			greek2EnglishMap.put(greekTerm, englishTerm);

		}
		qe.close();
	}

	public void runOntoQuery(String selectedLemma, String selectedRelType, String selectedLemmaEN) {

		setOntoResult2JS(OntoUtils.runQuery(selectedLemma, selectedRelType, selectedLemmaEN, getOntoModel(), getOntoExplicitModel())); 

	}


	public void initializeQuestion_1()  {
		String queryString = null;
		String inst = null;
		ontoQueryInstanceMap = new HashMap<String, String>();
		queryString = Consts.NAMESPACES
				+ "SELECT ?termine "
				+ "WHERE { ?termine a owl:Thing . ?termine ?property ?value . ?property rdf:type owl:"
				+ query_1_param_1 + " . } " + "ORDER BY ?termine";
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, getOntoModel());
		for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
			QuerySolution binding = rs.nextSolution();
			inst = binding.get("termine").toString().split("#")[1];
			ontoQueryInstanceMap.put(inst, inst);
		}
		qe.close();
	}

	public void initializeQuestion_3() {
		String queryString = null;
		String elem = null;
		objRelationMap   = new HashMap<String, String>();
		/*ontoQueryInstanceMap = new HashMap<String, String>();
		queryString = Consts.NAMESPACES
				+ "SELECT ?termine "
				+ "WHERE { ?termine a owl:Thing . } "
				+ "ORDER BY ?termine";
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, getOntoModel());
		for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
			QuerySolution binding = rs.nextSolution();
			elem = binding.get("termine").toString().split("#")[1];
			ontoQueryInstanceMap.put(elem, elem);
		}*/
		queryString = Consts.NAMESPACES
				+ "SELECT DISTINCT ?property "
				+ "WHERE { "
				+ "{ plotino:"
				+ query_3_param_1
				+ " ?property ?v1 . ?property rdf:type owl:ObjectProperty . } "
				+ "UNION "
				+ "{ ?v2 ?property plotino:"
				+ query_3_param_1
				+ " . ?property rdf:type owl:ObjectProperty . } "
				+ "} "
				+ "ORDER BY ?property";
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, getOntoModel());
		for (ResultSet rs = qe.execSelect(); rs.hasNext();) {
			QuerySolution binding = rs.nextSolution();
			elem = binding.get("property").toString().split("#")[1];
			objRelationMap.put(elem, elem);
		}
		qe.close();
	}

}
