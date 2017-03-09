package it.cnr.ilc.g2a.utils;

import it.cnr.ilc.g2a.action.userbean.AnalysisBean;
import it.cnr.ilc.g2a.datahandler.ExistDBConnector;
import it.cnr.ilc.g2a.exception.LanguageUnknownException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.profiler.Profiler;


public class XPathUtils {

	private static final Logger logger = LogManager.getLogger("XPathUtils");
	private static MessageProvider mp = new MessageProvider();

	public static String getDbName() {
		return mp.getValue( Consts.CONFIGNAME , "db_name");
	}

	public static String getArabicCollectionPath() {

		return mp.getValue(Consts.CONFIGNAME, "db_arabic_doc_path");
	}
	public static String getGreekCollectionPath() {

		return mp.getValue(Consts.CONFIGNAME, "db_greek_doc_path");
	}
	public static String getLinkCollectionPath() {

		return mp.getValue(Consts.CONFIGNAME, "db_link_path");
	}
	public static String getCommentCollectionPath() {

		return mp.getValue(Consts.CONFIGNAME, "db_comment_path");
	}

	public static String getArabicWorkName() {

		return mp.getValue(Consts.CONFIGNAME, "arabic_work_name");
	}

	public static String getGreekWorkName() {

		return mp.getValue(Consts.CONFIGNAME, "greek_work_name");
	}

	// ANALYSIS

	public static ArrayList<AnalysisBean> getGreekAnalysisByPericopeId(String collection, String pericopeId) {

		ExistDBConnector dbconn = ExistDBConnector.getInstance();
		ArrayList<AnalysisBean> analyses = null;
		ArrayList<String> tokensId = dbconn.searchTokensInPericopeById(collection , pericopeId);
		//int i = 0;
		for (String id : tokensId) {
			String analysisId = dbconn.searchLinkAnalysisByTokenId(collection , id);
			//String analysisId = "380";
			ArrayList<String> analysis = dbconn.searchAnalysisById(collection , analysisId);
			//System.err.println(i++ + " analysis: " + analysis);

			if (null != analysis) {
				if (analyses == null) {
					analyses = new ArrayList<AnalysisBean>();
				}
				AnalysisBean ab = new AnalysisBean();

				//ab.setForma(analysis.get(0));
				ab.setForma(dbconn.searchTokenValueByTokenId(collection , id));
				ab.setPos(analysis.get(1));
				ab.setLemma(analysis.get(2));
				analyses.add(ab);
			}

		}

		return analyses;
	}


	public static ArrayList<AnalysisBean> getArabicAnalysisByPericopeId(String collection, String pericopeId) {

		ExistDBConnector dbconn = ExistDBConnector.getInstance();
		ArrayList<AnalysisBean> analyses = null;
		ArrayList<String> tokensId = dbconn.searchTokensInPericopeById(collection, pericopeId);
		System.err.println("pericopeId " + pericopeId);

		Pattern sequencePattern = Pattern.compile("<xm:sequence xmlns:xm=\"(.+?)\" id=\"(?<id>.+?)\" classname=\"(.+?)\">");
		Pattern elementPattern = Pattern.compile("<xm:element id=\"(?<id>.+?)\" classname=\"(.+?)\">(?<params>.+?)</xm:element>");
		Pattern paramPattern = Pattern.compile("<xm:param name=\"(?<name>.+?)\" value=\"(?<value>.*?)\"/>");

		HashMap<String, String> parameters = new HashMap<String, String>();
		//int i = 0;
		for (String id : tokensId) {
			String analysisId = dbconn.searchLinkAnalysisByTokenId(collection, id);
			//String analysisId = "380";

			String analysis = dbconn.searchAnalysisAttibutesById(collection, analysisId);

			if (null != analysis) {
				if (analyses == null) {
					analyses = new ArrayList<AnalysisBean>();
				}
				AnalysisBean sequenceBean = new AnalysisBean();

				//	System.err.println("s("+ana+")");
				Matcher sequenceMatcher = sequencePattern.matcher(analysis);
				if (sequenceMatcher.find()){
					//System.err.println("sequence id " + sequenceMatcher.group("id"));
					sequenceBean.setId(sequenceMatcher.group("id"));
					Matcher elementMatcher = elementPattern.matcher(analysis);

					while (elementMatcher.find()){
						//System.err.println("element id" + elementMatcher.group("id"));


						Matcher paramMatcher = paramPattern.matcher(elementMatcher.group("params")); //
						parameters.clear();
						while (paramMatcher.find()){
							// System.err.println(paramMatcher.group());
							//System.err.print(paramMatcher.group("name"));
							//System.err.print(" => ");
							//System.err.println(paramMatcher.group("value"));
							parameters.put(paramMatcher.group("name"), paramMatcher.group("value"));
						}

						if (parameters.containsKey("radice")) {
							//SubAnalysisBean paramBean = new SubAnalysisBean();
							//paramBean.setId(elementMatcher.group("id"));

							sequenceBean.setRadicet(StringEscapeUtils.unescapeXml(parameters.get("radice")));

							sequenceBean.setLemmat(StringEscapeUtils.unescapeXml(parameters.get("lemma")));

							sequenceBean.setFormat(StringEscapeUtils.unescapeXml(parameters.get("forma")));

							sequenceBean.setAnalysis(StringEscapeUtils.unescapeXml(parameters.get("analisi")));

							//elementBean.add(paramBean);

							BuckwalterToViewConverter.convert(sequenceBean);
							analyses.add(sequenceBean);

						}
					}
				}
			}
			//		System.err.println("getArabicAnalysisByPericopeId() tokenid " + id + " analysisId " + analysisId);
		}

		return analyses;
	}


	//SEARCH


	/*	public static List<String> simpleGreekSearchTokenIdsByMopho(String dbName, String word, String type, String pos) {

		return simpleSearchTokenIdsByMopho(dbName, word, type, pos, "grc");
	}
	public static List<String> simpleArabicSearchTokenIdsByMopho(String dbName, String word, String type, String pos) {

		return simpleSearchTokenIdsByMopho(dbName, word, type, pos, "ar");
	}
	 */

	public static List<String> simpleSearchTokenIdsByMopho (String dbName, List<String> itemName, List<String> itemValue, int lang) throws LanguageUnknownException {

		ExistDBConnector dbconn = ExistDBConnector.getInstance();
		List<String> tokenIds = new ArrayList<String>();
		List<String> analysisIds = null;
		String collection = null;
		switch (lang) {
		case Consts.GREEK:
			collection = getGreekCollectionPath();
			
			analysisIds = dbconn.searchAnalysisIdByParameter(dbName + collection, itemName, itemValue, Consts.GREEK);
			break;
		case Consts.ARABIC:
			collection = getArabicCollectionPath();
			analysisIds = dbconn.searchAnalysisIdByParameter(dbName + collection, itemName, itemValue, Consts.ARABIC);
			break;

		default:
			throw new LanguageUnknownException("Unknonw language " + lang);
		}

		if (null != analysisIds) {

			for (String analysisId : analysisIds) {
				if (null != analysisId)
					tokenIds.addAll(dbconn.searchTokenIdByAnalysisId(dbName + collection, analysisId));
			}
		}

		return tokenIds;

	}


	public static List<String> simpleSearchTokenIdsByForma (String dbName, List<String> forme, int lang) throws LanguageUnknownException {

		ExistDBConnector dbconn = ExistDBConnector.getInstance();
		List<String> tokenIds = new ArrayList<String>();
		String collection = null;
		switch (lang) {
		
		case Consts.GREEK:
			collection = getGreekCollectionPath();
			for (String forma : forme) {
				List<String> ids = dbconn.searchTokenIdByForma(dbName + collection, forma, Consts.GREEK);
				if (null != ids)
					tokenIds.addAll(ids);
			}
			break;
			
		case Consts.ARABIC:
			collection = getArabicCollectionPath();
			for (String forma : forme) {
				List<String> ids = dbconn.searchTokenIdByForma(dbName + collection, forma, Consts.ARABIC);
				if (null != ids)
					tokenIds.addAll(ids);
			}
			break;

		default:
			throw new LanguageUnknownException("Unknonw language " + lang);
		}

		Set<String> s = new HashSet<String>(tokenIds);
		System.err.println("====> simpleSearchTokenIdsByForma(): reduction: " + tokenIds.size() + " " + s.size());
		tokenIds.clear();
		tokenIds.addAll(s);

		return tokenIds;

	}


	/*
	public static List<String> simpleSearchTokenIdsByMopho (String dbName, String word, String type, String pos, int lang) {

		ExistDBConnector dbconn = ExistDBConnector.getInstance();
		List<String> tokenIds = new ArrayList<String>();
		List<String> analysisIds = null;
		String collection = null;
		switch (lang) {
		case Consts.GREEK:
			collection = "plot/";
			break;
		case Consts.ARABIC:
			collection = "bada/";
			break;

		default:
			break;
		}

		switch (type) {
		case "forma":
			analysisIds = dbconn.searchAnalysisIdByParameter(dbName + "/doc/" + collection, word, pos, null, null);
			break;
		case "lemma":
			analysisIds = dbconn.searchAnalysisIdByParameter(dbName + "/doc/" + collection, null, pos, word, null);
			break;
		case "radice":
			analysisIds = dbconn.searchAnalysisIdByParameter(dbName + "/doc/"  + collection, null, pos, null, word);
			break;
		default:
			break;
		}
		if (null != analysisIds) {

			for (String analysisId : analysisIds) {
				tokenIds.addAll(dbconn.searchTokenIdByAnalysisId(dbName + "/doc/"  + collection, analysisId));
			}
		}

		return tokenIds;
	}
	 */
	/*
	public static List<String> simpleGreekSearchPericopeIdsByTokenIds (String dbName, List<String> tokenIds) {

		return simpleSearchPericopeIdsByTokenIds(dbName, tokenIds, Consts.GREEKCODE);
	}

	public static List<String> simpleArabicSearchPericopeIdsByTokenIds (String dbName, List<String> tokenIds) {

		return simpleSearchPericopeIdsByTokenIds(dbName, tokenIds, Consts.ARABICCODE);
	}
	 */
	public static List<String> simpleSearchPericopeIdsByTokenIds (String dbName, List<String> tokenIds, int lang) {

		Profiler profiler = new Profiler("simpleSearchPericopeIdsByTokenIds");

		ExistDBConnector dbconn = ExistDBConnector.getInstance();
		List<String> pericopeIds = null;
		String collection = null;
		switch (lang) {
		case Consts.GREEK:
			collection = getGreekCollectionPath();
			break;
		case Consts.ARABIC:
			collection = getArabicCollectionPath();
			break;

		default:
			break;
		}

		if (null != tokenIds) {
			pericopeIds = new ArrayList<String>();
			for (String tokenId : tokenIds) {
				pericopeIds.add(dbconn.searchPericopeIdByTokenId(dbName + collection, tokenId));
				//profiler.stop().print(); //non importa perche' e' veloce
			}
		}
		Set<String> s = new HashSet<String>(pericopeIds);
		System.err.println("====> simpleSearchPericopeIdsByTokenIds(): reduction: " + pericopeIds.size() + " " + s.size());
		pericopeIds.clear();
		pericopeIds.addAll(s);

		profiler.stop().print();

		return pericopeIds;

	}


	public static List<String> simpleSearchLinkIdsByPericopeIds (String dbName, List<String> pericopeIds, int lang) {


		Profiler profiler = new Profiler("simpleSearchLinkIdsByPericopeIds");

		ExistDBConnector dbconn = ExistDBConnector.getInstance();
		List<String> linkIds = null;
		String res = null;
		if (null != pericopeIds) {
			linkIds = new ArrayList<String>();
			for (String pericopeId : pericopeIds) {
				profiler.start(pericopeId);
				if (null != (res = dbconn.searchLinkIdByPericopeIdAndLang(dbName + getLinkCollectionPath(), pericopeId, Utils.lang2String(lang)))){
					linkIds.add(res);
				}
			}
		}
		profiler.stop().print();
		return linkIds;

	}

	public static boolean existsMorphoFile (String dbName, int lang) throws LanguageUnknownException {

		ExistDBConnector dbconn = ExistDBConnector.getInstance();
		String collection;
		boolean ret = false;

		switch (lang) {
		case Consts.GREEK:
			collection = getGreekCollectionPath();
			ret = dbconn.exists(dbName + collection, "morphoDB.xml");
			break;
		case Consts.ARABIC:
			collection = getArabicCollectionPath();
			ret = dbconn.exists(dbName + collection, "morphoDB.xml");
			break;

		default:
			throw new LanguageUnknownException("Unknonw language " + lang);
		}
		return ret;

	}

}
