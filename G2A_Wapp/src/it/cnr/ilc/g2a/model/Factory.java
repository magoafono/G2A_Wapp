package it.cnr.ilc.g2a.model;

import it.cnr.ilc.g2a.action.controller.analysis.ContextAnalysis;
import it.cnr.ilc.g2a.action.controller.content.ContextContent;
import it.cnr.ilc.g2a.action.controller.resource.ContextResource;
import it.cnr.ilc.g2a.model.analysis.Analysis;
import it.cnr.ilc.g2a.model.analysis.AnalysisDB;
import it.cnr.ilc.g2a.model.analysis.AnalysisDelegate;
import it.cnr.ilc.g2a.model.analysis.AnalysisSet;
import it.cnr.ilc.g2a.model.analysis.RefAnalysis;
import it.cnr.ilc.g2a.model.comment.Comment;
import it.cnr.ilc.g2a.model.text.PericopeText;
import it.cnr.ilc.g2a.model.text.RefPericopeText;
import it.cnr.ilc.g2a.model.text.RefTokenText;
import it.cnr.ilc.g2a.model.text.TokenText;

import java.util.ArrayList;
import java.util.HashMap;

public class Factory {


	public static TokenText getTokenTextInstance() {

		return new TokenText();
	}

	public static RefTokenText getRefTokenTextInstance() {

		return new RefTokenText();
	}

	public static ArrayList<TokenText> getTokenTextList() {

		return new ArrayList<TokenText>();
	}

	public static HashMap<String,TokenText> getTokenTextMap() {

		return new HashMap<String,TokenText>();
	}

	public static ArrayList<RefTokenText> getRefTokenTextList() {

		return new ArrayList<RefTokenText>();
	}

	public static ArrayList<PericopeText> getPericopeTextList() {

		return new ArrayList<PericopeText>();
	}

	public static HashMap<String,PericopeText> getPericopeTextMap() {

		return new HashMap<String,PericopeText>();
	}

	public static PericopeText getPericopeText() {

		return new PericopeText();
	}

	public static ArrayList<Link<RefPericope>> getLinkList() {

		return new ArrayList<Link<RefPericope>>();
	}

	public static HashMap<String,Link<RefPericope>> getLinkMap() {

		return new HashMap<String,Link<RefPericope>>();
	}

	public static ArrayList<Link<RefPericopeText>> getTextLinkList() {

		return new ArrayList<Link<RefPericopeText>>();
	}

	public static Link<RefPericope> getLink() {

		return new Link<RefPericope>();
	}

	public static Link<Reference> getTextLink() {

		return new Link<Reference>();
	}

	public static ArrayList<RefPericope> getRefPericopeList() {

		return  new ArrayList<RefPericope>();
	}

	public static RefPericope getRefPericopeInstance() {
		return getRefPericopeInstance(null);
	}

	public static RefPericope getRefPericopeInstance(String extended) {

		RefPericope ret = new RefPericopeText();
		/*//TODO //FIXME
		if (null != extended) {
			if ("image".equals(extended)) {
				ret = null; //new RefPericopeImage(); 
			}
		} */
		return ret;
	}
	public static RefPericopeText getRefPericopeTextInstance(String extended) {

		RefPericopeText ret = new RefPericopeText();
		return ret;
	}

	public static AnalysisDelegate getInstanceAnalysisDelegate() {

		AnalysisDelegate ad = new AnalysisDelegate();

		return ad;
	}

	public static ContextAnalysis getInstanceContextAnalysis() {

		return new ContextAnalysis();
	}

	public static ContextResource getInstanceContextResource() {

		return new ContextResource();
	}

	public static AnalysisDB getAnalysisDBInstance() {

		return new AnalysisDB();
	}

	public static AnalysisSet getInstanceAnalysisSet() {

		return new AnalysisSet();
	}

	public static Analysis getAnalysisInstance() {

		return new Analysis();
	}

	public static HashMap<String, String> getStringHashMapInstance() {

		return new HashMap<String, String>();
	}

	public static RefAnalysis getRefAnalysisInstance() {

		return new RefAnalysis();
	}

	public static ContextContent getContextContentInstance(String type) {

		ContextContent cc = null;
		if ("TokenText".equals(type)){
			cc = new ContextContent<TokenText>();
		} else if ("PericopeText".equals(type)){
			cc = new ContextContent<PericopeText>();
		} else if ("Link".equals(type)){
			cc = new ContextContent<Link<Reference>>();
		} else if ("Comment".equals(type)) {
			cc = new ContextContent<Comment>();
		}
		return cc;
	}

	public static ArrayList<Reference> getReferenceList() {
		// TODO Auto-generated method stub
		return new ArrayList<Reference>();
	}


}
