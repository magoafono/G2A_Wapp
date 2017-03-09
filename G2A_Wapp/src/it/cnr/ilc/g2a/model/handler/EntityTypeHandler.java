package it.cnr.ilc.g2a.model.handler;

import it.cnr.ilc.g2a.model.xmlmapping.ElementDocument.Element;
import it.cnr.ilc.g2a.model.xmlmapping.SequenceDocument.Sequence;
import it.cnr.ilc.g2a.utils.MessageProvider;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

public class EntityTypeHandler {
	
	static MessageProvider mp = new MessageProvider();

	public static boolean isPericopes(Sequence s) {

		boolean ret = false;
		if (null != s) {
			ret = mp.getValue("type","pericopes").equals(s.getClassname());
		}
		return ret;
	}


	public static boolean isPericope(Sequence s) {

		boolean ret = false;
		if (null != s) {
				Sequence parent = (Sequence) getParent(s);
				if (null != parent){
					ret = mp.getValue("type","pericopes").equals(parent.getClassname());
				}
		}
		return ret;
	}


	private static XmlObject getParent (XmlObject xbean) {

		XmlObject parentXbean = null;

		if (null != xbean) {
			XmlCursor cursor = xbean.newCursor();
			cursor.toParent();
			parentXbean = (Sequence) cursor.getObject();
			cursor.dispose();
		}	
		return parentXbean;
	}

	public static boolean isText(Sequence s) {

		boolean ret = false;
		if (null != s) {
			ret = mp.getValue("type","text").equals(s.getClassname());
		}
		return ret;
	}


	public static boolean isLinks(Sequence s) {
		boolean ret = false;
		if (null != s) {
			ret = mp.getValue("type","links").equals(s.getClassname());
		}
		return ret;
	}
	
	public static boolean isLink(Sequence s) {
		boolean ret = false;
		if (null != s) {
			ret = mp.getValue("type","link").equals(s.getClassname());
		}
		return ret;
	}


	public static boolean isAnalysisDB(Sequence s) {
		// TODO Auto-generated method stub
		boolean ret = false;
		if(null != s){
			ret = mp.getValue("type","analysisDB").equals(s.getExtended()); 
		}
		return ret;
	}


	public static boolean isAnalysis(Element element) {
		boolean ret = false;
		if(null != element){
			ret = mp.getValue("type","analysis").equals(element.getClassname()); 
		}
		return ret;
	}


	public static boolean isTokenToAnalysis(Sequence s) {
		
		boolean ret = false;
		if(null != s){
			ret = mp.getValue("type","tokenToAnalysis").equals(s.getClassname());
		}
		return ret;
	}


	public static boolean isTokenAnalyses(Sequence s) {
		
		boolean ret = false;
		if(null != s){
			ret = mp.getValue("type","tokenAnalyses").equals(s.getClassname());
		}
		return ret;
	}

}
