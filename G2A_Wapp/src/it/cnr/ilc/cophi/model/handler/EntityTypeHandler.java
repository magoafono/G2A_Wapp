package it.cnr.ilc.cophi.model.handler;

import it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element;
import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence;
import it.cnr.ilc.cophi.utils.MessageProvider;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

public class EntityTypeHandler {

	public static boolean isPericopes(Sequence s) {

		boolean ret = false;
		if (null != s) {
			ret = MessageProvider.getValue("type","pericopes").equals(s.getClassname());
		}
		return ret;
	}


	public static boolean isPericope(Sequence s) {

		boolean ret = false;
		if (null != s) {
				Sequence parent = (Sequence) getParent(s);
				if (null != parent){
					ret = MessageProvider.getValue("type","pericopes").equals(parent.getClassname());
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
			ret = MessageProvider.getValue("type","text").equals(s.getClassname());
		}
		return ret;
	}


	public static boolean isLinks(Sequence s) {
		boolean ret = false;
		if (null != s) {
			ret = MessageProvider.getValue("type","links").equals(s.getClassname());
		}
		return ret;
	}
	
	public static boolean isLink(Sequence s) {
		boolean ret = false;
		if (null != s) {
			ret = MessageProvider.getValue("type","link").equals(s.getClassname());
		}
		return ret;
	}


	public static boolean isAnalysisDB(Sequence s) {
		// TODO Auto-generated method stub
		boolean ret = false;
		if(null != s){
			ret = MessageProvider.getValue("type","analysisDB").equals(s.getExtended()); 
		}
		return ret;
	}


	public static boolean isAnalysis(Element element) {
		boolean ret = false;
		if(null != element){
			ret = MessageProvider.getValue("type","analysis").equals(element.getClassname()); 
		}
		return ret;
	}


	public static boolean isTokenToAnalysis(Sequence s) {
		
		boolean ret = false;
		if(null != s){
			ret = MessageProvider.getValue("type","tokenToAnalysis").equals(s.getClassname());
		}
		return ret;
	}


	public static boolean isTokenAnalyses(Sequence s) {
		
		boolean ret = false;
		if(null != s){
			ret = MessageProvider.getValue("type","tokenAnalyses").equals(s.getClassname());
		}
		return ret;
	}

}
