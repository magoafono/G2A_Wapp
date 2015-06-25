package it.cnr.ilc.cophi.utils;

import it.cnr.ilc.cophi.datahandler.ExistDBConnector;
import it.cnr.ilc.cophi.model.Link;
import it.cnr.ilc.cophi.model.Reference;
import it.cnr.ilc.cophi.model.comment.Comment;
import it.cnr.ilc.cophi.model.text.PericopeText;
import it.cnr.ilc.cophi.model.text.RefPericopeText;
import it.cnr.ilc.cophi.model.text.RefTokenText;
import it.cnr.ilc.cophi.model.text.TokenText;
import it.cnr.ilc.cophi.model.view.ResultViewEntity;
import it.cnr.ilc.cophi.model.view.SelectedTextBoundaries;
import it.cnr.ilc.cophi.model.view.TokenViewEntity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Utils {

	private static final Logger logger = LogManager.getLogger("Utils");

	public static CharSequence fromFile(String filename) throws IOException {
		FileInputStream input = new FileInputStream(filename);
		FileChannel channel = input.getChannel();

		// Create a read-only CharBuffer on the file
		ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int)channel.size());
		CharBuffer cbuf = Charset.forName("UTF8").newDecoder().decode(bbuf);
		input.close();

		return cbuf;
	}


	public static HashMap<String, List<Comment>> commentsGroupByLink (HashMap<String, Comment> commentsHM) {
		HashMap<String,List<Comment>> hmloc = new HashMap<String, List<Comment>>();
		List<Comment>  list = null;
		for (Comment com : commentsHM.values()) {
			String linkId = com.getLinkId();
			if (hmloc.containsKey(linkId)){
				list = hmloc.get(linkId);
			} else {
				list = new ArrayList<Comment>();
			}
			list.add(com);
			hmloc.put(linkId, list);
		}
		return hmloc;
	}



	public static String getDate () {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		return dateFormat.format(date);

	}


	public static String createFilename(Comment comment) {

		return comment.getCommentId();
	}


	public static String substring(SelectedTextBoundaries bound, String text) {
		String ret = "";
		if (bound.getStart() < bound.getEnd()){
			ret = text.substring(bound.getStart(), bound.getEnd());
		}
		return ret;
	}

	public static void addFatalMessageToContext(String summary, String messageId) {
		addMessageToContext(summary, messageId, FacesMessage.SEVERITY_FATAL);

	}
	public static void addErrorMessageToContext(String summary, String messageId) {
		addMessageToContext(summary, messageId, FacesMessage.SEVERITY_ERROR);

	}
	public static void addWarnMessageToContext(String summary, String messageId) {
		addMessageToContext(summary, messageId, FacesMessage.SEVERITY_WARN);

	}
	public static void addInfoMessageToContext(String summary, String messageId) {
		addMessageToContext(summary, messageId, FacesMessage.SEVERITY_INFO);

	}
	private static void addMessageToContext(String summary, String messageId, Severity severity) {

		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage();
		message.setSeverity(severity);
		message.setSummary(summary);
		context.addMessage(messageId, message);

	}

	public static String extractTextFromSpan (String text) {

		return extractTextFromTag("span", text);
	}

	private static String extractTextFromTag (String tag, String text) {
		//<span class="pericope c" style="background-color: lightgreen">kjhdkjashdkajds</span>
		String value = null;
		Pattern p = Pattern.compile("<\\*"+ tag + ".+?>(.+?)<\\s*" + tag + "\\*/>");
		Matcher m = p.matcher(text);

		if (m.find()) {
			value = m.group(0);
		}

		return value;

	}

	public static String getLangfromLangId(int langId) {
		String lang = null;
		switch (langId) {
		case 0:
			lang = "greek";
			break;
		case 1:
			lang = "arabic";
			break;

		default:
			break;
		}
		return  lang;
	}

	public static HashMap<String, String> createToken2PericopeHM (HashMap<String, PericopeText> pericopes) {

		HashMap<String, String> token2pericope = new HashMap<String, String>();
		if (null != pericopes) {
			Iterator<Entry<String, PericopeText>> it = pericopes.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, PericopeText> pairs = (Map.Entry)it.next();
				//System.out.println(pairs.getKey() + " = " + pairs.getValue());
				List<Reference> lortt = pairs.getValue().getValue();
				for (Reference reference : lortt) {
					token2pericope.put(reference.getRef(), pairs.getKey());
				}
			}
		}

		return token2pericope;
	}

	public static HashMap<String, String> createPericope2LinkHM (HashMap<String, Link<RefPericopeText>> links) {

		HashMap<String, String> pericope2link = new HashMap<String, String>();
		if (null != links) {
			Iterator<Entry<String, Link<RefPericopeText>>> it = links.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Link<RefPericopeText>> pairs = (Map.Entry)it.next();
				//System.out.println(pairs.getKey() + " = " + pairs.getValue());
				List<RefPericopeText> lortt = pairs.getValue().getValue();
				for (Reference reference : lortt) {
					pericope2link.put(reference.getRef(), pairs.getKey());
				}
			}
		}

		return pericope2link;
	}


	public static List<TokenViewEntity> createTokenViewList (HashMap<String, TokenText> tokens, HashMap<String, PericopeText> pericopes, 
			HashMap<String,Link<RefPericopeText>> links, 
			HashMap<String, String> token2pericope, HashMap<String, String> pericope2link, int langId, CharSequence text) {

		List<TokenViewEntity> ltv = new ArrayList<TokenViewEntity>();
		String pericopeId = null;
		String linkId = null;
		String precPericopeId = null;
		List<TokenText> tokensList = new ArrayList<TokenText>(tokens.values());
		int i = 0;
		TokenViewEntity prec = null;
		Collections.sort(tokensList,CophiSort.TOKENTEXT_FROM_ORDER);
		int index = 0;
		for (TokenText token: tokensList) {
			TokenViewEntity tv = new TokenViewEntity();
			tv.setId(token.getId());
			//			tv.setText(token.getText(text));
			tv.setText(token.getText());
			tv.setFrom(token.getFrom());
			tv.setTo(token.getTo());
			tv.setColor(i);

			if (null != prec) {
				prec.setSucc(tv);
			}

			tv.setPrec(prec);

			if (null != (pericopeId = token2pericope.get(token.getId()))){
				tv.setPericopeInfo(pericopes.get(pericopeId).getInfo());
				if (!pericopeId.equals(precPericopeId)){
					i++;
					precPericopeId = pericopeId;
				}
				tv.setPericopeId(pericopeId);
				tv.setColor(i);

				/*if (pericopeId.contains(".")) {
					tv.setHisPage(Integer.parseInt(pericopeId.substring(0, pericopeId.indexOf(".")))); //se arabo
				} else {
					tv.setHisPage(Integer.parseInt(pericopeId)); //se greco
				}*/
				if (langId == Consts.ARABIC) {
					/*
					 * esempio di ID di Pericope arabo: 068.000048
					 * PPP.xxxxxx dove  PPP e' la pagina e xxxxxxx e il progressivo del token 
					 * quindi la pagina va dal carattere 2 al carattere 5
					 */
					tv.setHisLoc(Integer.parseInt(pericopeId.substring(0, 3))); //se arabo
				} else {
					/* esempio di ID di pericope Greca 53080.000401
					 * L'id della pericope del greco NON contiene il numero di pagina ma e' formato come segue:
					 * TCPPP.xxxxxx dove T e' il trattano (1-9) C e' il capitolo e PPP e' il paragrafo (080 significa paragrafo 8 e 081 paragrafo 8^1)
					 */
					tv.setHisLoc(Integer.parseInt(pericopeId.substring(0, 5))); //se greco hisPage tutto il riferimento TCPPP e non la pagina che in greco non ci sono
				}

				if (null !=(linkId = pericope2link.get(pericopeId))){
					tv.setLinkId(linkId);
				}
			} else {
				tv.setColor(-1);
				tv.setPericopeId("-1");
				tv.setPericopeInfo("NONE");
				tv.setLinkId("-1");
			}
			prec = tv;
			tv.setIndex(index);
			index++;
			ltv.add(tv);
		}
		//		Collections.sort(ltv,CophiSort.TOKENVIEW_FROM_ORDER);

		return ltv;
	}

	@Deprecated
	public static List<TokenViewEntity> createTokenViewListByXquery (HashMap<String, TokenText> tokens, HashMap<String, PericopeText> pericopes, 
			HashMap<String,Link<RefPericopeText>> links, int langId, CharSequence text) {

		ExistDBConnector dbconn = ExistDBConnector.getInstance();
		String URI = null;
		String linkURI = "new_GA/link";
		List<TokenViewEntity> ltv = new ArrayList<TokenViewEntity>();
		String pericopeId = null;
		String linkId = null;
		String precPericopeId = null;
		List<TokenText> tokensList = new ArrayList<TokenText>(tokens.values());
		int i = 0;
		TokenViewEntity prec = null;
		Collections.sort(tokensList,CophiSort.TOKENTEXT_FROM_ORDER);

		for (TokenText token: tokensList) {
			TokenViewEntity tv = new TokenViewEntity();
			tv.setId(token.getId());
			//			tv.setText(token.getText(text));
			tv.setText(token.getText());
			tv.setFrom(token.getFrom());
			tv.setTo(token.getTo());
			tv.setColor(i);

			if (null != prec) {
				prec.setSucc(tv);
			}

			tv.setPrec(prec);

			switch (langId) {
			case Consts.ARABIC:
				URI = "new_GA/doc/bada";
				break;
			case Consts.GREEK:
				URI = "new_GA/doc/plot";
				break;

			default:
				break;
			}
			if (null != (pericopeId = dbconn.searchPericopeIdByTokenRef(URI, token.getId()))){
				tv.setPericopeInfo(pericopes.get(pericopeId).getInfo());
				if (!pericopeId.equals(precPericopeId)){
					i++;
					precPericopeId = pericopeId;
				}
				tv.setPericopeId(pericopeId);
				tv.setColor(i);

				/*if (pericopeId.contains(".")) {
					tv.setHisPage(Integer.parseInt(pericopeId.substring(0, pericopeId.indexOf(".")))); //se arabo
				} else {
					tv.setHisPage(Integer.parseInt(pericopeId)); //se greco
				}*/
				if (langId == Consts.ARABIC) {
					/*
					 * esempio di ID di Pericope arabo: 068.000048
					 * PPP.xxxxxx dove  PPP e' la pagina e xxxxxxx e il progressivo del token 
					 * quindi la pagina va dal carattere 2 al carattere 5
					 */
					tv.setHisLoc(Integer.parseInt(pericopeId.substring(0, 3))); //se arabo
				} else {
					/* esempio di ID di pericope Greca 53080.000401
					 * L'id della pericope del greco NON contiene il numero di pagina ma e' formato come segue:
					 * TCPPP.xxxxxx dove T e' il trattano (1-9) C e' il capitolo e PPP e' il paragrafo (080 significa paragrafo 8 e 081 paragrafo 8^1)
					 */
					tv.setHisLoc(Integer.parseInt(pericopeId.substring(0, 5))); //se greco hisPage tutto il riferimento TCPPP e non la pagina che in greco non ci sono
				}

				if (null !=(linkId = dbconn.searchLinkIdByPericopeRef(linkURI, pericopeId))){
					tv.setLinkId(linkId);
				}
			} else {
				tv.setColor(-1);
				tv.setPericopeId("-1");
				tv.setPericopeInfo("NONE");
				tv.setLinkId("-1");
			}
			prec = tv;
			ltv.add(tv);
		}
		//		Collections.sort(ltv,CophiSort.TOKENVIEW_FROM_ORDER);

		return ltv;
	}


	/**
	 * Aggiorna i TokenViewEntity coinvolti nello spostamenti da una pericope ad un altra.
	 * E' necessario aggiornare di ogni tve l'id della pericope, la info della pericope e il colore da visualizzare nel pericopatore.
	 * @param listOfGreekTokenView Lista dei TokenView di un testo
	 * @param clickedTokenIndex lista degli indici dei tokenview coinvolti nella modifica
	 * @param pericopeId Id della pericope a cui sono stati aggiunti i token
	 * @param pericopes tutte le pericopi di un testo
	 */
	public static void updateTokenViewList(List<TokenViewEntity> listOfGreekTokenView, List<Integer> clickedTokenIndex, String pericopeId, HashMap<String, PericopeText> pericopes) {

		/*
		 * Calcolo il massimo e il minimo indice; serve per poter individuare il colore delle pericope a cui ho aggiunto i token specificati clickedTokenIndex
		 */
		int min = Integer.MAX_VALUE;
		int max = 0;
		for (Integer index : clickedTokenIndex) {
			min = (min>index)?index:min;
			max = (max<index)?index:max;
		}

		/*
		 * Calcolo i tve precedente e successivo ai token che sono oggetto dello spostamento
		 */
		TokenViewEntity tvePrec = listOfGreekTokenView.get(min>0 ? min-1 : 0);
		TokenViewEntity tveSucc = listOfGreekTokenView.get(max<listOfGreekTokenView.size()? max+1 : 0);

		for (Integer index : clickedTokenIndex) {

			TokenViewEntity tve = listOfGreekTokenView.get(index.intValue());

			tve.setPericopeId(pericopeId);
			PericopeText pt = pericopes.get(pericopeId);

			tve.setPericopeInfo(pt.getInfo());
			//per settare il colore assumo che e' sicuramente cambiato, quindi prendo il colore dal precedente o successivo TVE che è diverso dal mio
			if (tvePrec.getColor() != tve.getColor()) {
				tve.setColor(tvePrec.getColor()); 
			} else {
				tve.setColor(tveSucc.getColor()); 
			}
		}

	}

	public static Document createPericopeDom (HashMap<String, TokenText> tokens, HashMap<String,Link<RefPericopeText>> links, HashMap<String, String> token2pericope, HashMap<String, String> pericope2link, int langId, CharSequence text) {

		Document rootDom = new Document();
		Element rootElement = new Element("span").setAttribute("class", "document  " + Utils.getLangfromLangId(langId));
		rootDom.setRootElement(rootElement);
		String pericopeId = null;
		String linkId = null;
		TokenText tokenPrec = null;
		StringBuilder tokenClass = null;
		StringBuilder tokenRel = null;
		String precPericopeId = null;
		Element pericopeElement = new Element("span");
		rootElement.addContent(pericopeElement);

		for (TokenText token: tokens.values()) {
			tokenClass = new StringBuilder();
			tokenRel = new StringBuilder();
			//			String tokenText = token.getText(text);
			String tokenText = token.getText();
			tokenClass.append("token");
			Element tokenElement = new Element("span");
			tokenElement.setAttribute("id", token.getId());
			tokenElement.setText(tokenText);
			if (null != (pericopeId = token2pericope.get(token.getId()))) {
				tokenClass.append(" pericope");
				tokenRel.append(pericopeId);

				if (null != tokenPrec) {
					precPericopeId = token2pericope.get(tokenPrec.getId());
				}
				if (!pericopeId.equals(precPericopeId)) {//cambio pericope
					pericopeElement.setAttribute("id", (precPericopeId==null)?"-1":precPericopeId);
					pericopeElement.setAttribute("rel", links.get(pericope2link.get(precPericopeId)).getValue().get((langId + 1)%2).getRef()); //pericope relativa nell'altra lingua
					rootElement.addContent(pericopeElement); 
					pericopeElement = new Element("span");
				}

				if (null != (linkId = pericope2link.get(tokenText))) {
					String idPericopeRef = links.get(linkId).getValue().get(langId).getRef();
					tokenRel.append(" " + idPericopeRef);

				}

			} else {
				tokenClass.append("nopericope");
			}
			if (null != tokenPrec){
				int noOfspace = token.getFrom() - tokenPrec.getTo();
				if (noOfspace > 0) {
					Element preElement = new Element("pre");
					preElement.setAttribute("style", "display:inline");
					preElement.setText(String.format("%"+noOfspace+"s", " "));
				}
			}
			tokenElement.setAttribute("rel", tokenRel.toString());
			tokenElement.setAttribute("class", tokenClass.toString());
			pericopeElement.addContent(tokenElement);
		}

		return rootDom;
	}



	public static Document createPericopeDomOld (HashMap<String,Link<Reference>> links, int langId, CharSequence text) {

		Document rootDom = new Document();
		Element rootElement = new Element("span").setAttribute("class", "document  " + Utils.getLangfromLangId(langId));
		rootDom.setRootElement(rootElement);

		TokenText tokenPrec = null;

		for (Link<Reference> link : links.values()) {
			PericopeText pericope = (PericopeText) ((RefPericopeText)link.getValue().get(langId)).getPericope();
			List<Reference> lor = pericope.getValue();
			Element pericopeElement = new Element("span").setAttribute("class", "pericope");
			pericopeElement.setAttribute("id", pericope.getId());
			pericopeElement.setAttribute("rel",  ((RefPericopeText)link.getValue().get((langId + 1)%2)).getPericope().getId()); //pericope relativa nell'altra lingua
			rootElement.addContent(pericopeElement);
			for (Reference reference : lor) {
				TokenText token = ((RefTokenText)reference).getTok();
				//				String tokenText = token.getText(text);
				String tokenText = token.getText();
				Element tokenElement = new Element("span").setAttribute("class", "token");
				tokenElement.setAttribute("id", token.getId());
				tokenElement.setText(tokenText);
				tokenElement.setAttribute("rel", pericope.getId());
				if (null != tokenPrec){
					int noOfspace = token.getFrom() - tokenPrec.getTo();
					if (noOfspace > 0) {
						Element preElement = new Element("pre");
						preElement.setAttribute("style", "display:inline");
						preElement.setText(String.format("%"+noOfspace+"s", " "));
						pericopeElement.addContent(preElement);
					}
				}
				pericopeElement.addContent(tokenElement);
				tokenPrec = token;
			}
		}
		return rootDom;

	}

	public static String JDOMtoString (Document jdom) {

		XMLOutputter xmlout = new XMLOutputter(Format.getRawFormat());
		return xmlout.outputString(jdom.getRootElement());

	}


	public static float emToFloat (String em) {
		float ret = 0;
		if (null != em) {
			ret = Float.parseFloat(em.substring(0, em.indexOf("em")));
		}
		return ret;
	}

	public static ArrayList<PericopeText> getPericopeTextFromIds (List<String> ids, HashMap<String, PericopeText> hmPT) {

		ArrayList<PericopeText> listPT = null;
		if (null != ids) {
			listPT = new ArrayList<PericopeText>();
			for (String id : ids) {
				listPT.add(hmPT.get(id));
			}
		} 

		return listPT;
	}


	public static HashMap<String, String> stringList2HashMap (List<String> list) {

		HashMap<String, String> hm = null;
		if (null != list) {
			hm = new HashMap<String, String>();
			for (String s : list) {
				hm.put(s, "1");
			}
		}

		return hm;
	}

	public static List<TokenViewEntity> createTokenViewListByPericopeId (HashMap<String, TokenText> tokens, 
			HashMap<String, PericopeText> pericopes, String pericopeId, List<String> highlightTokenIds) {

		List<TokenViewEntity> ltv = new ArrayList<TokenViewEntity>();
		TokenViewEntity prec = null;
		//		Collections.sort(tokensList,CophiSort.TOKENTEXT_FROM_ORDER);
		int index = 0;
		HashMap<String, String> highlightTokenIdsHM = stringList2HashMap(highlightTokenIds);
		List<Reference> tokenList = pericopes.get(pericopeId).getValue();

		for (Reference tokenRef: tokenList) {
			TokenText tt = tokens.get(tokenRef.getRef());

			TokenViewEntity tve = new TokenViewEntity();
			tve.setId(tt.getId());
			//			tv.setText(token.getText(text));
			tve.setText(tt.getText());
			tve.setFrom(tt.getFrom());
			tve.setTo(tt.getTo());
			if (highlightTokenIdsHM.containsKey(tt.getId())){
				tve.setColor(1);
			} else {
				tve.setColor(0);
			}

			if (null != prec) {
				prec.setSucc(tve);
			}

			tve.setPrec(prec);

			tve.setPericopeInfo(pericopes.get(pericopeId).getInfo());
			tve.setPericopeId(pericopeId);

			//		tv.setHisLoc(pericopes.get(precPericopeId).getExtended()); //se arabo


			prec = tve;
			tve.setIndex(index);
			index++;
			ltv.add(tve);
		}
		//		Collections.sort(ltv,CophiSort.TOKENVIEW_FROM_ORDER);

		return ltv;


	}

	public static HashMap <String, ResultViewEntity> resultIntersect (HashMap<String, ResultViewEntity> a, HashMap <String, ResultViewEntity> b) {

		HashMap <String, ResultViewEntity>  result = null;
		if (null != a && null != b ) {
			result = new HashMap <String, ResultViewEntity> ();

			Iterator it = a.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
				//System.out.println(pairs.getKey() + " = " + pairs.getValue());
				if(b.containsKey(pairs.getKey())) {
					result.put((String)pairs.getKey(), mergeHighlighted(a.get(pairs.getKey()), b.get(pairs.getKey())));
				}
			}
		} 

		return result;
	}

	public static HashMap <String, ResultViewEntity> resultUnion (HashMap<String, ResultViewEntity> a, HashMap <String, ResultViewEntity> b) {

		HashMap <String, ResultViewEntity>  result = null;
		if (null != a && null != b ) {
			result = new HashMap <String, ResultViewEntity> ();

			Iterator it = a.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
				//System.out.println(pairs.getKey() + " = " + pairs.getValue());
				if(b.containsKey(pairs.getKey())) {
					result.put((String)pairs.getKey(), mergeHighlighted(a.get(pairs.getKey()), b.get(pairs.getKey())));
				} else {
					result.put((String)pairs.getKey(), a.get(pairs.getKey()));
				}
			}
			it = b.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
				//System.out.println(pairs.getKey() + " = " + pairs.getValue());
				if(!result.containsKey(pairs.getKey())) {
					result.put((String)pairs.getKey(), b.get(pairs.getKey()));
				}
			}
		} else if (null != a){
			result = a;
		} else {
			result = b;
		}

		return result;
	}

	public static ResultViewEntity mergeHighlighted (ResultViewEntity a, ResultViewEntity b) {

		if (null != a && null != b) {
			List<TokenViewEntity> arabictveA = a.getArabicTVE();
			List<TokenViewEntity> arabictveB = b.getArabicTVE();
			if (null != arabictveA && null != arabictveB) {
				for (int i = 0; i < arabictveA.size(); i++) {
					if (arabictveA.get(i).getColor() == 1 || arabictveB.get(i).getColor() == 1) {
						arabictveA.get(i).setColor(1);
					}
				}
			}

			List<TokenViewEntity> greektveA = a.getGreekTVE();
			List<TokenViewEntity> greektveB = b.getGreekTVE();
			if (null != greektveA && null != greektveB) {
				for (int i = 0; i < greektveA.size(); i++) {
					if (greektveA.get(i).getColor() == 1 || greektveB.get(i).getColor() == 1) {
						greektveA.get(i).setColor(1);
					}
				}
			}
		} else if (a == null) {
			a = b;
		}

		return a;

	}



	public static  <T> Map<String, ? extends T> hashMapIntersect (Map<String, ? extends T> a, Map <String, ? extends T> b) {

		Map<String, T> result = null;
		if (null != a && null != b ) {
			result = new HashMap <String, T> ();

			a.keySet().retainAll(b.keySet());
			Iterator it = a.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
				System.out.println(pairs.getKey() + " = " + pairs.getValue());
				result.put((String)pairs.getKey(), (T) pairs.getValue());
			}
		}

		return result;
	}


	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Collection<T> intersect (Collection <? extends T> a, Collection <? extends T> b)
	{
		Collection <T> result = null;
		if (null != a && null != b ) {
			result = new ArrayList <T> ();

			for (T t: a)
			{
				if (b.remove (t)) 
					result.add (t);
			}
		}
		return result;
	}


	public static String lang2String(int lang) {
		String ret = null;
		switch (lang) {
		case Consts.GREEK:
			ret = Consts.GREEKCODE;
			break;

		case Consts.ARABIC:
			ret = Consts.ARABICCODE;
		default:
			break;
		}

		return ret;
	}


	/**
	 * Crea una hashmap a partire da un file di testo dove ogni riga è una coppia di stringhe (contenenti {space} ma non {tab}) le quali
	 * solo separate da {tab}
	 * @param filename
	 * @return hashmap di coppie di stringhe
	 */
	public static HashMap<String, String> loadFile2HashMap (String filename) {

		BufferedReader br;
		HashMap<String, String> result = null;
		try {

			br = new BufferedReader( new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename), "UTF-8"));
			result = new HashMap<String, String>(128);
			String line;
			Pattern p = Pattern.compile("(.+)\t(.+)");
			while ((line = br.readLine()) != null) {
				Matcher m = p.matcher(line);
				if (m.matches()) {
					result.put(m.group(1), m.group(2));
				} else {
					logger.warn("Invalid row: ("+line+")");
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Converte alcuni caratteri appartenenti al Greek Extended nella loro versione "combined"
	 * @param s
	 * @return
	 */
	public static String extendToComposeGreekCharacter (String s) {
		
		String news = s.replaceAll("\u1F71","\u03AC");
		news = news.replaceAll("\u1FBB","\u0386");
		news = news.replaceAll("\u1F73","\u03AD");
		news = news.replaceAll("\u1FC9","\u0388");
		news = news.replaceAll("\u1F75","\u03AE");
		news = news.replaceAll("\u1FCB","\u0389");
		news = news.replaceAll("\u1F77","\u03AF");
		news = news.replaceAll("\u1FDB","\u038A");
		news = news.replaceAll("\u1F79","\u03CC");
		news = news.replaceAll("\u1FF9","\u038C");
		news = news.replaceAll("\u1F7B","\u03CD");
		news = news.replaceAll("\u1FEB","\u038E");
		news = news.replaceAll("\u1F7D","\u03CE");
		news = news.replaceAll("\u1FFB","\u038F");
		news = news.replaceAll("\u1FD3","\u0390");
		news = news.replaceAll("\u1FE3","\u03B0");
		news = news.replaceAll("\u037E","\u003B");
		news = news.replaceAll("\u0387","\u00B7");
		news = news.replaceAll("\u0344","\u0308");
		
		return news;
	}

}
