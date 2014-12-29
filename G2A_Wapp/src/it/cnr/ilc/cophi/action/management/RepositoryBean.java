package it.cnr.ilc.cophi.action.management;

import it.cnr.ilc.cophi.action.userbean.AnalysisBean;
import it.cnr.ilc.cophi.action.userbean.SearchBean;
import it.cnr.ilc.cophi.exception.BugException;
import it.cnr.ilc.cophi.model.Link;
import it.cnr.ilc.cophi.model.Reference;
import it.cnr.ilc.cophi.model.comment.Comment;
import it.cnr.ilc.cophi.model.handler.EntityManager;
import it.cnr.ilc.cophi.model.view.LinkViewEntity;
import it.cnr.ilc.cophi.model.view.ResultViewEntity;
import it.cnr.ilc.cophi.model.view.TokenViewEntity;
import it.cnr.ilc.cophi.utils.Consts;
import it.cnr.ilc.cophi.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.profiler.Profiler;

import com.hp.hpl.jena.ontology.OntModel;


@ManagedBean(name="repository", eager=true)
@ApplicationScoped
public class RepositoryBean {
	EntityManager em = null;

	private static final Logger logger = LogManager.getLogger("RepositoryBean");

	public RepositoryBean() {

		init();
	}

	void init(){

		em = EntityManager.getInstance();
	}


	public List<String> getArabicTokens() {

		return em.getArabicTokensAsList();
	}

	public List<String> getGreekTokens() {

		return em.getGreekTokensAsList();
	}

	//	public List<Pericope<Reference>> getPericopes() {
	//		return em.retriveAllPericopeAsList();
	//	}

	//	public List<String> getPericopesText(String lang) {
	//		
	//		List<Pericope<Reference>> lop = getPericopes();
	//		for (Iterator it = lop.iterator(); it.hasNext();) {
	//			Pericope<Reference> pericope = (Pericope<Reference>) it.next();
	//			List<Reference> tokens = pericope.getValue();
	//			for (Iterator it2 = tokens.iterator(); it2.hasNext();) {
	//				RefTokenText refTokenText = (RefTokenText) it2.next();
	//				System.err.println(refTokenText.getTok().getFrom() +  "-" + refTokenText.getTok().getTo());
	//			}
	//		}
	//		return null;
	//	}

	public List<LinkViewEntity> getLinks() {

		return em.getLinksAsList(Consts.ARABIC);
	}

	public List<LinkViewEntity> getLinks(String lang) {

		return em.getLinksAsList("arabic".equals(lang)?Consts.ARABIC:Consts.GREEK);
	}

	public Link<Reference> getLink (String id) {

		return em.getLink(id);
	}

	public List<Comment> getCommentsByLinkId (String linkId) {
		return em.getLinkComments (linkId);
	}

	public List<Comment> reloadAllComments (String linkId) {
		em.reloadAllComments();
		return getCommentsByLinkId(linkId);
	}

	public List<Comment> getOrderedCommentsByLinkId (String linkId) {
		return em.getOrderedLinkComments (linkId);
	}

	public boolean saveComment(Comment comment) {

		return em.saveComment(comment);
	}

	public boolean deleteComment(Comment comment) {

		return em.deleteComment(comment);
	}

	//	private HashMap<String,Link<Reference>> loadLinks() {
	//		return em.retriveAllLink();
	//	}

	public String getGreekPericopeDomAsString (){
		return em.pericopeDomAsString(Consts.GREEK);
	}

	public String getArabicPericopeDomAsString (){
		return ""; /*em.pericopeDomAsString(Consts.ARABIC)*/
	}

	public List<TokenViewEntity> getArabicTokenViewList() {
		return em.getListOfArabicTokenView();
	}

	public List<TokenViewEntity> getGreekTokenViewList() {
		return em.getListOfGreekTokenView();
	}

	public void addGreekClick(List<String> tokensId, String focusedPericopeId, List<String> listOfPericopesId) {

		em.handlePericope (tokensId, focusedPericopeId, listOfPericopesId, Consts.ADDTOPERICOPE, Consts.GREEK);
		em.saveModifiedGreekPericopes(focusedPericopeId, listOfPericopesId);
	}

	public void removeGreekClick(List<String> tokensId, String focusedPericopeId, List<String> listOfPericopesId) {

		em.handlePericope (tokensId, focusedPericopeId,  listOfPericopesId, Consts.REMFROMPERICOPE, Consts.GREEK);
		em.saveGreekPericopes();
	}

	public void shiftGreekClick(List<String> tokensId, String focusedPericopeId, List<String> listOfPericopesId) {

		//	em.handlePericope (tokensId, focusedPericopeId, listOfPericopesId, Consts.SHIFTTOPERICOPE, Consts.GREEK);
		//em.saveGreekPericopes();
	}

	public void addArabicClick(List<String> tokensId, String focusedPericopeId, List<String> listOfPericopesId) {

		em.handlePericope (tokensId, focusedPericopeId, listOfPericopesId, Consts.ADDTOPERICOPE, Consts.ARABIC);
		em.saveModifiedArabicPericopes(focusedPericopeId, listOfPericopesId);
		//em.saveArabicPericopes();
	}

	public void removeArabicClick(List<String> tokensId, String focusedPericopeId, List<String> listOfPericopesId) {

		em.handlePericope (tokensId, focusedPericopeId,  listOfPericopesId, Consts.REMFROMPERICOPE, Consts.ARABIC);
		em.saveArabicPericopes();
	}

	public void shiftArabicClick(List<String> tokensId, String focusedPericopeId, List<String> listOfPericopesId) {

		em.handlePericope (tokensId, focusedPericopeId, listOfPericopesId, Consts.SHIFTTOPERICOPE, Consts.ARABIC);
		em.saveArabicPericopes();
	}

	public void updateAfterGreekPericopeModification(List<Integer> clickedTokenIndex, String pericopeId) {
		em.updateAfterGreekPericopeModification(clickedTokenIndex, pericopeId);
	}

	public void updateAfterArabicPericopeModification(List<Integer> clickedTokenIndex, String pericopeId) {
		em.updateAfterArabicPericopeModification(clickedTokenIndex, pericopeId);
	}

	public String getPericopeTextById(String pericopeId, int langId) {

		String text = null;
		switch (langId) {
		case Consts.GREEK:
			//text = em.getGreekPericopes().get(pericopeId).getText(em.getGreekCharBuffer());
			text = em.getGreekPericopes().get(pericopeId).getText();
			break;

		case Consts.ARABIC:
			//text = em.getArabicPericopes().get(pericopeId).getText(em.getArabicCharBuffer());
			text = em.getArabicPericopes().get(pericopeId).getText();
			break;

		default:
			break;
		}
		return text;
	}

	/////////RECUPERO CON XQUERY/XPATH DELLE ANALISI LINGUISTICHE

	public List<AnalysisBean> getPericopeAnalysis (String pericopeId, int langId ) {

		List<AnalysisBean> analyses = null;
		switch (langId) {
		case Consts.GREEK:

			analyses = em.getGreekAnalysisByPericopeId(pericopeId);
			break;

		case Consts.ARABIC:

			analyses = em.getArabicAnalysisByPericopeId(pericopeId);

			break;

		default:
			break;
		}
		return analyses;


	}

	/////// RICERCA CON XPATH (SEARCH.XHTML)

	private HashMap<String, ResultViewEntity> simpleSearchLinksByTokens (SearchBean parameters, int lang) {
		//ricerca i link riferenti pericopi che soddisfano i parametri di ricerca  
		//TODO
		/*
		 * 
		 * 	1 - cerco gli id delle analisi in analysis.xml che hanno forma e/o lemma e/o pos richieste [searchAnalysisIdByParameter]
		 * 	2 - cerco gli id dei token che hanno analisi id quelli trovati precedentemente in linkAnalysis.xml
		 * 	3 - degli id dei token che rispettano i parametri di ricerca vado a cercare gli id delle pericopi [searchPericopeIdByTokenId] 
		 * 	4 - dagli id delle pericopi prendo gli id dei link (pericope2link) [searchLinkIdByPericopeIdAndLang]
		 * 
		 *  
		 * Vecchio OLD ***************************
		 * 	1.1 - accedo a tokens.xml e recupero gli ids dei token (XPATH) [searchTokenIdsByTokenAttribute()]
		 * 	1.2 - se ho restrizioni sulla pos   
		 * 		1.2.1 - con i token id accedo a linkanalisisrecupero gli id delle analisi (XPATH) [searchLinkAnalysisByTokenId()]
		 * 		1.2.2 - recupero il valore delle analisi e lo confronto con la pos in input (XPATH)
		 * 	1.3 - degli id dei token che rispettano i parametri di ricerca vado a cercare gli id delle pericopi (token2pericope) 
		 * 	1.4 - dagli id delle pericopi prendo gli id dei link (pericope2link)
		 * 
		 * 2 - se ricerca per lemma 
		 * 	2.1 - accedo a anlysis.xml e recupero gli id delle analisi di quel lemma ed eventualmente con quella pos (XPATH)
		 * 	2.2 - accedo a linkanalysis.xml e recupero gli id dei tonken con quegli id di analisi trovati prima
		 * 	2.3 (uguale a 1.3)
		 * 	2.4 (uguale a 1.4)
		 */
		//		List<List<ResultViewEntity>> lolorve = new ArrayList<List<ResultViewEntity>>();
		List<HashMap<String,ResultViewEntity>> lolorve = new ArrayList<HashMap<String,ResultViewEntity>>();
		HashMap<String, ResultViewEntity> resultHM = null;

		Profiler profiler = new Profiler("RepositoryBean.simpleSearchLinksByTokens()");

		for (int i = 0; i < 3; i++){

			try {
				//lolorve.add(em.simpleSearchLinksByTokens(parameters.getWord().get(i), parameters.getType().get(i), parameters.getPos().get(i), lang));
				ArrayList<String> itemName = new ArrayList<String>();
				ArrayList<String> itemValue = new ArrayList<String>();
				if (!"".equals(parameters.getWord().get(i))) {
					itemName.add(parameters.getType().get(i));
					itemValue.add(parameters.getWord().get(i).toLowerCase());
					if (null != parameters.getPos().get(i)){ //lo confronto col null perche' nella interfaccia e' un oneselect
						switch (lang) {
						case Consts.GREEK:
							itemName.add("pos");
							break;
						case Consts.ARABIC:
							itemName.add("post");
							break;

						default:
							break;
						}

						itemValue.add(parameters.getPos().get(i));
					}
					profiler.start("simpleSearchLinksByTokens");
					lolorve.add(em.simpleSearchLinksByTokens(itemName, itemValue, lang));
				}
			} catch (BugException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		profiler.stop().print();
		lolorve.removeAll(Collections.singleton(null));
		if (Consts.ANDOP.equals(parameters.getOp())) {
			/*if (! "".equals(parameters.getWord().get(0))){
				if (! "".equals(parameters.getWord().get(1))){
					resultHM = (HashMap<String,ResultViewEntity>) Utils.resultIntersect (lolorve.get(0), lolorve.get(1));
					if (! "".equals(parameters.getWord().get(2))){
						//		result = (List<ResultViewEntity>) Utils.intersect (result, lolorve.get(2));
						resultHM = (HashMap<String, ResultViewEntity>) Utils.resultIntersect (resultHM, lolorve.get(2));
					}

				} else {
					resultHM = (HashMap<String, ResultViewEntity>) lolorve.get(0);
				}*/
			for (HashMap<String,ResultViewEntity> partialResult : lolorve) {
				if (null != partialResult){
					if (null == resultHM) {
						resultHM = new HashMap<String, ResultViewEntity>();
						resultHM.putAll(partialResult);
					} else {
						resultHM = (HashMap<String,ResultViewEntity>) Utils.resultIntersect (resultHM, partialResult);
					}
				}
			}

			//and di tutti i risultati delle ricerche

		} else if (Consts.OROP.equals(parameters.getOp())){
			//presento tutti i risultati delle ricerche
			for (HashMap<String,ResultViewEntity> partialResult : lolorve) {
				resultHM = (HashMap<String,ResultViewEntity>) Utils.resultUnion (resultHM, partialResult);
			}

			//			resultHM = (HashMap<String,ResultViewEntity>) Utils.resultUnion(lolorve.get(2), Utils.resultUnion(lolorve.get(0), lolorve.get(1)));

		} else {
			logger.error("Unknown operation: " + parameters.getOp());
			resultHM = (HashMap<String, ResultViewEntity>) lolorve.get(0);
		}


		return resultHM;

	}

	public List<ResultViewEntity> simpleArabicSearchLinksByTokens (SearchBean parameters) {

		List<ResultViewEntity> ret = null;
		HashMap<String, ResultViewEntity> resultHM = simpleSearchLinksByTokens(parameters, Consts.ARABIC);
		if (null != resultHM) {
			Map<String, ResultViewEntity> map = new TreeMap<String, ResultViewEntity>(resultHM); 
			ret = new ArrayList(map.values());
		}

		return ret;

	}

	public List<ResultViewEntity> simpleGreekSearchLinksByTokens (SearchBean parameters) {

		List<ResultViewEntity> ret = null;
		HashMap<String, ResultViewEntity> resultHM = simpleSearchLinksByTokens(parameters, Consts.GREEK);
		if (null != resultHM) {
			Map<String, ResultViewEntity> map = new TreeMap<String, ResultViewEntity>(resultHM); 
			ret = new ArrayList(map.values());
		}

		return ret;

	}


	public List<ResultViewEntity> combinedSearchLinksByTokens (SearchBean greekParameter, SearchBean arabicParameter, String combinedOperation) {

		List<ResultViewEntity> ret = null;
		HashMap<String, ResultViewEntity> combinedResultHM = null;

		HashMap<String, ResultViewEntity> greekResultHM = simpleSearchLinksByTokens(greekParameter, Consts.GREEK);
		HashMap<String, ResultViewEntity> arabicResultHM = simpleSearchLinksByTokens(arabicParameter, Consts.ARABIC);

		if (Consts.ANDOP.equals(combinedOperation)) {
			combinedResultHM = (HashMap<String,ResultViewEntity>) Utils.resultIntersect (arabicResultHM, greekResultHM);
		} else if (Consts.OROP.equals(combinedOperation)) {
			combinedResultHM = (HashMap<String,ResultViewEntity>) Utils.resultUnion(arabicResultHM, greekResultHM);
		} else {
			logger.error("Operazione sconosciuta!! " + combinedOperation);
		}
		if (null != combinedResultHM) {
			Map<String, ResultViewEntity> map = new TreeMap<String, ResultViewEntity>(combinedResultHM); 
			ret = new ArrayList(map.values());
		}

		return ret;
	}

	public LinkViewEntity getLinkViewEntity(String linkId) {

		return em.getLinkViewEntityByLinkId(linkId);

	}

	/*
	 * Lexicon
	 */

	/**
	 * @return the ontoModel
	 */
	public OntModel getOntoModel() {
		return em.getOntoModel();
	}

	/**
	 * @param ontoModel the ontoModel to set
	 */
	public void setOntoModel(OntModel ontoModel) {
		em.setOntoModel(ontoModel);
	}

	/**
	 * @return the ontoExplicitModel
	 */
	public OntModel getOntoExplicitModel() {
		return em.getOntoExplicitModel();
	}

	/**
	 * @param ontoExplicitModel the ontoExplicitModel to set
	 */
	public void setOntoExplicitModel(OntModel ontoExplicitModel) {
		em.setOntoExplicitModel(ontoExplicitModel);
	}

	/**
	 * @return the ontoInstanceMap
	 */
	public Map<String, String> getOntoInstanceMap() {
		return em.getOntoInstanceMap();
	}

	/**
	 * @param ontoInstanceMap the ontoInstanceMap to set
	 */
	public void setOntoInstanceMap(Map<String, String> ontoInstanceMap) {
		em.setOntoInstanceMap(ontoInstanceMap);
	}

	/**
	 * @return the ontoInstanceMap
	 */
	public Map<String, String> getGreek2EnglishMap() {
		return em.getGreek2EnglishMap();
	}

	/**
	 * @param ontoInstanceMap the ontoInstanceMap to set
	 */
	public void setGreek2EnglishMap(Map<String, String> greek2EnglishMap) {
		em.setGreek2EnglishMap(greek2EnglishMap);
	}



	/**
	 * @return the ontoResult2JS
	 */
	public String getOntoResult2JS() {
		return em.getOntoResult2JS();
	}

	/**
	 * @param ontoResult2JS the ontoResult2JS to set
	 */
	public void setOntoResult2JS(String ontoResult2JS) {
		em.setOntoResult2JS(ontoResult2JS);
	}


	public void runOntoQuery(String selectedLemma, String selectedRelType, String selectedLemmaEN) {
		em.runOntoQuery( selectedLemma,  selectedRelType, selectedLemmaEN);
	}
	
	
	/**
	 * @return the ontoQueryInstanceMap
	 */
	public Map<String, String> getOntoQueryInstanceMap() {
		return em.getOntoQueryInstanceMap();
	}

	/**
	 * @param ontoQueryInstanceMap the ontoQueryInstanceMap to set
	 */
	public void setOntoQueryInstanceMap(Map<String, String> ontoQueryInstanceMap) {
		em.setOntoQueryInstanceMap(ontoQueryInstanceMap);
	}
	
	/**
	 * @return the objRelationMap
	 */
	public Map<String, String> getObjRelationMap() {
		return em.getObjRelationMap();
	}

	/**
	 * @param objRelationMap the objRelationMap to set
	 */
	public void setObjRelationMap(Map<String, String> objRelationMap) {
		em.setObjRelationMap(objRelationMap);
	}

	/**
	 * @return the query_1_param_1
	 */
	public String getQuery_1_param_1() {
		return em.getQuery_1_param_1();
	}

	/**
	 * @param query_1_param_1 the query_1_param_1 to set
	 */
	public void setQuery_1_param_1(String query_1_param_1) {
		em.setQuery_1_param_1( query_1_param_1);
	}

	/**
	 * @return the query_1_param_2
	 */
	public String getQuery_1_param_2() {
		return em.getQuery_1_param_2();
	}

	/**
	 * @param query_1_param_2 the query_1_param_2 to set
	 */
	public void setQuery_1_param_2(String query_1_param_2) {
		em.setQuery_1_param_2(query_1_param_2);
	}

	/**
	 * @return the query_2_param_1
	 */
	public String getQuery_2_param_1() {
		return em.getQuery_2_param_1();
	}

	/**
	 * @param query_2_param_1 the query_2_param_1 to set
	 */
	public void setQuery_2_param_1(String query_2_param_1) {
		em.setQuery_2_param_1(query_2_param_1);
	}

	/**
	 * @return the query_2_param_2
	 */
	public String getQuery_2_param_2() {
		return em.getQuery_2_param_2();
	}

	/**
	 * @param query_2_param_2 the query_2_param_2 to set
	 */
	public void setQuery_2_param_2(String query_2_param_2) {
		em.setQuery_2_param_2(query_2_param_2);
	}

	/**
	 * @return the query_2_param_3
	 */
	public String getQuery_2_param_3() {
		return em.getQuery_2_param_3();
	}

	/**
	 * @param query_2_param_3 the query_2_param_3 to set
	 */
	public void setQuery_2_param_3(String query_2_param_3) {
		em.setQuery_2_param_3( query_2_param_3);
	}

	/**
	 * @return the query_3_param_1
	 */
	public String getQuery_3_param_1() {
		return em.getQuery_3_param_1();
	}

	/**
	 * @param query_3_param_1 the query_3_param_1 to set
	 */
	public void setQuery_3_param_1(String query_3_param_1) {
		em.setQuery_3_param_1(query_3_param_1);
	}

	/**
	 * @return the query_3_param_2
	 */
	public String getQuery_3_param_2() {
		return em.getQuery_3_param_2();
	}

	/**
	 * @param query_3_param_2 the query_3_param_2 to set
	 */
	public void setQuery_3_param_2(String query_3_param_2) {
		em.setQuery_3_param_2(query_3_param_2);
	}
	
	

}
