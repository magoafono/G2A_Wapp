package it.cnr.ilc.cophi.action.controller.importxml;

import it.cnr.ilc.cophi.model.Factory;
import it.cnr.ilc.cophi.model.Link;
import it.cnr.ilc.cophi.model.Pericope;
import it.cnr.ilc.cophi.model.Reference;
import it.cnr.ilc.cophi.model.ReferenceSet;
import it.cnr.ilc.cophi.model.Token;
import it.cnr.ilc.cophi.model.importxml.xmlmapping.AddDocument;
import it.cnr.ilc.cophi.model.importxml.xmlmapping.DocDocument.Doc;
import it.cnr.ilc.cophi.model.importxml.xmlmapping.FieldDocument.Field;
import it.cnr.ilc.cophi.model.text.RefPericopeText;
import it.cnr.ilc.cophi.model.text.RefTokenText;
import it.cnr.ilc.cophi.model.text.TokenText;
import it.cnr.ilc.cophi.model.xmlmapping.ElementDocument.Element;
import it.cnr.ilc.cophi.model.xmlmapping.ParamDocument.Param;
import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument;
import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.xmlbeans.XmlCursor;

public class LegacyXMLMapper {

	public LegacyXMLMapper(AddDocument addRoot, CharSequence greekCharBuffer,
			CharSequence arabicCharBuffer) {
		this.addRoot = addRoot;
		this.greekCharSeq = greekCharBuffer;
		this.arabicCharSeq= arabicCharBuffer;
	}

	//input
	private AddDocument addRoot;
	private CharSequence greekCharSeq;
	private CharSequence arabicCharSeq;

	//output
	private List<TokenText> greekTokenList = null;
	private List<TokenText> arabicTokenList = null;

	private Pericope<Reference> greekPericope = null;
	private Pericope<Reference> arabicPericope = null;

	private Link<Reference> greekArabicLink = null;

	private SequenceDocument greekTokenSequence  = null;
	private SequenceDocument arabicTokenSequence = null;
	private SequenceDocument greekPericopeSequence  = null;
	private SequenceDocument arabicPericopeSequence  = null;

	private SequenceDocument greekArabicLinkSequence  = null;

	/**
	 * @return the ad
	 */
	public AddDocument getAddNode() {
		return this.addRoot;
	}
	/**
	 * @param ad the ad to set
	 */
	public void setAddNode(AddDocument addNode) {
		this.addRoot = addNode;
	}
	/**
	 * @return the greekCharBuffer
	 */
	public CharSequence getGreekCharBuffer() {
		return greekCharSeq;
	}
	/**
	 * @param greekCharBuffer the greekCharBuffer to set
	 */
	public void setGreekCharBuffer(CharSequence greekCharBuffer) {
		this.greekCharSeq = greekCharBuffer;
	}
	/**
	 * @return the arabicCharBuffer
	 */
	public CharSequence getArabicCharBuffer() {
		return arabicCharSeq;
	}
	/**
	 * @param arabicCharBuffer the arabicCharBuffer to set
	 */
	public void setArabicCharBuffer(CharSequence arabicCharBuffer) {
		this.arabicCharSeq = arabicCharBuffer;
	}
	/**
	 * @return the greekToken
	 */
	public List<TokenText> getGreekTokenList() {
		return greekTokenList;
	}
	/**
	 * @param greekToken the greekToken to set
	 */
	public void setGreekTokenList(List<TokenText> greekTokenList) {
		this.greekTokenList = greekTokenList;
	}
	/**
	 * @return the arabicToken
	 */
	public List<TokenText> getArabicTokenList() {
		return arabicTokenList;
	}
	/**
	 * @param arabicToken the arabicToken to set
	 */
	public void setArabicTokenList(List<TokenText> arabicTokenList) {
		this.arabicTokenList = arabicTokenList;
	}
	/**
	 * @return the greekPericope
	 */
	public Pericope<Reference> getGreekPericope() {
		return greekPericope;
	}
	/**
	 * @param greekPericope the greekPericope to set
	 */
	public void setGreekPericope(Pericope<Reference> greekPericope) {
		this.greekPericope = greekPericope;
	}
	/**
	 * @return the arabicPericope
	 */
	public Pericope<Reference> getArabicPericope() {
		return arabicPericope;
	}
	/**
	 * @param arabicPericope the arabicPericope to set
	 */
	public void setArabicPericope(Pericope<Reference> arabicPericope) {
		this.arabicPericope = arabicPericope;
	}

	/**
	 * @return the greekArabicLink
	 */
	public Link<Reference> getGreekArabicLink() {
		return greekArabicLink;
	}
	/**
	 * @param greekArabicLink the arabicGreekLink to set
	 */
	public void setGreekArabicLink(Link<Reference> greekArabicLink) {
		this.greekArabicLink = greekArabicLink;
	}



	/**
	 * @return the greekTokenSequnce
	 */
	public SequenceDocument getGreekTokenSequence() {
		return greekTokenSequence;
	}
	/**
	 * @param greekTokenSequnce the greekTokenSequnce to set
	 */
	public void setGreekTokenSequence(SequenceDocument greekTokenSequence) {
		this.greekTokenSequence = greekTokenSequence;
	}
	/**
	 * @return the arabicTokenSequence
	 */
	public SequenceDocument getArabicTokenSequence() {
		return arabicTokenSequence;
	}
	/**
	 * @param arabicTokenSequence the arabicTokenSequence to set
	 */
	public void setArabicTokenSequence(SequenceDocument arabicTokenSequence) {
		this.arabicTokenSequence = arabicTokenSequence;
	}
	/**
	 * @return the greekPericopeSequence
	 */
	public SequenceDocument getGreekPericopeSequence() {
		return greekPericopeSequence;
	}
	/**
	 * @param greekPericopeSequence the greekPericopeSequence to set
	 */
	public void setGreekPericopeSequence(SequenceDocument greekPericopeSequence) {
		this.greekPericopeSequence = greekPericopeSequence;
	}
	/**
	 * @return the arabicPericopeSequence
	 */
	public SequenceDocument getArabicPericopeSequence() {
		return arabicPericopeSequence;
	}
	/**
	 * @param arabicPericopeSequence the arabicPericopeSequence to set
	 */
	public void setArabicPericopeSequence(SequenceDocument arabicPericopeSequence) {
		this.arabicPericopeSequence = arabicPericopeSequence;
	}
	private Doc getDoc () {
		return addRoot.getAdd().getDoc();
	}

	private Field[] getFields () {

		return getDoc().getFieldArray();
	}

	private Field getFieldByName (String name) {

		Field ret = null;
		Field[] fields = getFields();
		for (Field field : fields) {
			if (name.equals(field.getName()) ){
				ret = field;
				break;
			}
		}
		return ret;
	}

	private String getFieldContentByName(String name) {
		String ret = null;
		Field field;

		if (null != (field = getFieldByName(name))){
			XmlCursor cursor = field.newCursor();
			ret = cursor.getTextValue();
		}
		return ret;
	}

	private  String getGreekPericopeText() {

		return getFieldContentByName("pericope_gr");
	}

	private  String getArabicPericopeText() {

		return getFieldContentByName("pericope_ar");
	}

	private String getGreekPericopeId() {

		return getFieldContentByName("id_gr");
	}

	private String getArabicPericopeId() {

		return getFieldContentByName("id_ar");
	}

	private String getGreekPericopeInfo() {

		return getFieldContentByName("info_gr");
	}

	private String getArabicPericopeInfo() {

		return getFieldContentByName("info_ar");
	}

	/**
	 * @return the greekArabicLinkSequence
	 */
	public SequenceDocument getGreekArabicLinkSequence() {
		return greekArabicLinkSequence;
	}
	/**
	 * @param greekArabicLinkSequence the greekArabicLinkSequence to set
	 */
	public void setGreekArabicLinkSequence(SequenceDocument greekArabicLinkSequence) {
		this.greekArabicLinkSequence = greekArabicLinkSequence;
	}

	private Pericope<Reference> createPericope (String idPericope,  List<TokenText> tokenList, String type, String info) {

		Pericope<Reference> pericope = Factory.getPericopeText();
		pericope.setId(idPericope);
		pericope.setType(type);
		pericope.setInfo(info);
		
		List<Reference> ltt = new ArrayList<Reference>(1024);
		for (Token token : tokenList) {
			RefTokenText rtt = new RefTokenText();
			rtt.setId("ref_"+token.getId());
			rtt.setTok((TokenText)token);
			rtt.setRef(token.getId());
			ltt.add(rtt);
		}

		pericope.setValue(ltt);

		return pericope;
	}	

	/**
	 * Crea la token list per la stringa s (esempio una pericope) 
	 * @param prefix prefisso per gli id dei tokentext
	 * @param buffer testo nel quale sarà cercata la stringa s
	 * @param s stringa di testo per la qale creare la lista di tokentext
	 * @return lista di tokentext
	 */
	private List<TokenText> createTokenList (String prefix, CharSequence buffer,  String s) {

		List<TokenText> tokenList = new ArrayList<TokenText>(1024*1024);

		/* 
		 * Costruisco un pattern con la stringa in input (es. la pericope) sostituendo ogni spazio con una catena di spazi \s*
		 * in questo modo la ricerca è meno restrittiva   
		 */
	/*	s = s.replaceAll("\\[", "&#91;");
		s = s.replaceAll("\\]", "&#93;");
		*/
		s = s.replaceAll("\\[", "\\\\[");
		s = s.replaceAll("\\]", "\\\\]");
		//s = s.replaceAll("\\<", "\\\\<");
		//s = s.replaceAll("\\>", "\\\\>");
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		
		
		Pattern p = Pattern.compile(s.replaceAll(" +", "\\\\s*"), Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		//Pattern p = Pattern.compile("καὶ\\s+κοσμεῖν\\s+ὀρεγόμενον\\s+καθὰ\\s+ἐν\\s+νῷ\\s+εἶδεν,", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

		Matcher m = p.matcher(buffer);

		if (m.find()) {
			// la pericope e' trovata nel testo => ne prendo i margini
			int startOfPericope = m.start();
			// tokenizzo la stringa che matcha
			//Pattern tokenPattern = Pattern.compile("\\b\\S+\\b", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
			//TODO nel pattern ci mancano i segni di interpunzione arabi!!!!
			/*
			 * ARABIC QUESTION MARK (\u061F), ARABIC SEMICOLON (\u061B), ARABIC COMMA (\u060C)
			 */
			Pattern tokenPattern = Pattern.compile("(([^\\p{Punct}\\[\\]\u21B2\u21B3\u00B6\u204B\u061F\u061B\u060C\\s]+)|[\\p{Punct}\\[\\]\u21B2\u21B3\u00B6\u204B\u061F\u061B\u060C])", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
			Matcher tokenMatcher = tokenPattern.matcher(m.group());
			if (tokenMatcher.groupCount() < 2 ) {
				System.err.println("No match found for : " + s + ", " + tokenMatcher.groupCount());
				System.exit(-1);
			}
			while (tokenMatcher.find()) {
				// per ogni token che individuo
				//System.err.println("token :" +tokenMatcher.group().trim());
				if (tokenMatcher.group().trim().length() > 0) {
					//System.err.println(m.group());
					TokenText curr = new TokenText();
					int from = startOfPericope + tokenMatcher.start();
					curr.setFrom(from);
					curr.setTo(startOfPericope + tokenMatcher.end());
					curr.setId(prefix + from);
					tokenList.add(curr);
				}
			}
		} else {
			System.err.println("Pericope text not found for  " + s);
			System.exit(-1);
		}

		return tokenList;
	}

	private Link<Reference> createLink ( Pericope<Reference> greekPericope,  Pericope<Reference> arabicPericope) {

		Link<Reference> greekArabicLink = null;
		if ((null != greekPericope) && (null != arabicPericope)) {
			greekArabicLink = Factory.getTextLink();

			RefPericopeText rptGr = (RefPericopeText) Factory.getRefPericopeInstance();
			RefPericopeText rptAr = (RefPericopeText) Factory.getRefPericopeInstance();

			rptGr.setId("ref_" + greekPericope.getId());
			rptGr.setPericope(greekPericope);
			rptGr.setRef(greekPericope.getId());
			rptGr.setExtended(greekPericope.getType());
			
			rptAr.setId("ref_" + arabicPericope.getId());
			rptAr.setPericope(arabicPericope);
			rptAr.setRef(arabicPericope.getId());
			rptAr.setExtended(arabicPericope.getType());
			
			List<Reference> lorpt = new ArrayList<Reference>(1024);
			lorpt.add(rptGr);
			lorpt.add(rptAr);

			greekArabicLink.setId("link_" + Float.parseFloat(greekPericope.getId()) + "_" + Float.parseFloat(arabicPericope.getId()));
			greekArabicLink.setType("greekArabicLink");
			greekArabicLink.setValue(lorpt);
		} else {
			System.err.println("createLink() le pericopi non sono inizializzate!!");
		}
		return greekArabicLink;
	}

	private void createGreekArabicLink () {

		setGreekArabicLink(createLink (getGreekPericope(), getArabicPericope()));
	}

	private void createGreekTokenList () {

		setGreekTokenList(createTokenList ("id_"/*"gr_"*/, getGreekCharBuffer(), getGreekPericopeText()));

	}

	private void createArabicTokenList () {

		setArabicTokenList(createTokenList ("id_"/*"ar_"*/, getArabicCharBuffer(), getArabicPericopeText()));

	}

	private void createGreekPericope (){

		setGreekPericope(createPericope (getGreekPericopeId(),  getGreekTokenList(), "grc", getGreekPericopeInfo())); 
	}

	private void createArabicPericope (){

		setArabicPericope(createPericope (getArabicPericopeId(), getArabicTokenList(), "ar", getArabicPericopeInfo())); 
	}

	private void importLegacyXml () {
		createGreekTokenList();
		createArabicTokenList();
		createGreekPericope();
		createArabicPericope();

		createGreekArabicLink();

	}

	private Param createParam (String name, String value) {

		Param p = Param.Factory.newInstance();
		p.setName(name);
		p.setValue(value);
		return p;
	}

	private List<Param> tokenToParamMapping (TokenText t) {

		List<Param> params = null;

		if (null != t) {
			params = new ArrayList<Param>(1024);

			Param from = createParam ("from", Integer.toString(t.getFrom()));
			Param to = createParam ("to", Integer.toString(t.getTo()));
			Param analysis = createParam ("analysis", "");

			params.add(from);
			params.add(to);
			params.add(analysis);
		}

		return params;
	}

	private List<Element> tokenToElementMapping (List<TokenText> list) {

		List<Element> elements = null;

		if (null != list) {
			elements = new ArrayList<Element>(1024);
			for (TokenText token : list) {
				Element e = Element.Factory.newInstance();

				Object[] listOfParams = tokenToParamMapping(token).toArray();
				e.setParamArray(Arrays.copyOf(listOfParams, listOfParams.length, Param[].class));
				e.setId("id_" + e.getParamArray(0).getValue()); //Si prende il valore del primo che deve essere il from
				e.setClassname("token");
				elements.add(e);
			}
		}		

		return elements;
	}


	private SequenceDocument createTokenSequenceDocument (String lang, List<TokenText> tokenTextList) {

		SequenceDocument sd = null;
		if ( null != tokenTextList) {
			sd = SequenceDocument.Factory.newInstance();
			Sequence s = sd.addNewSequence();
			s.setClassname("pericopeTokens"); //invece di "document"
			s.setExtended(lang);
			Object[] listOfElement = tokenToElementMapping(tokenTextList).toArray();
			s.setElementArray(Arrays.copyOf(listOfElement,listOfElement.length, Element[].class));
		}
		return sd;
	}

	private SequenceDocument createGreekTokenSequenceDocument () {

		return createTokenSequenceDocument ("greek", getGreekTokenList());
	}

	private SequenceDocument createArabicTokenSequenceDocument () {

		return createTokenSequenceDocument ("arabic", getArabicTokenList());
	}


	private List<Element> referencesToElementMapping(ReferenceSet<Reference> referenceSet, String className) {

		List<Element> elements = null;

		if (null != referenceSet) {
			List<Reference> list = referenceSet.getValue();
			elements = new ArrayList<Element>(1024);
			for (Reference reference : list) {
				Element e = Element.Factory.newInstance();
				e.setId(reference.getId());
				e.setRef(reference.getRef());
				e.setClassname(className);
				e.setExtended((reference.getExtended()!=null)?reference.getExtended():"");
				
				elements.add(e); 
			}
		}	

		return elements;
	}


	private List<Param> referenceToParamMapping(ReferenceSet<Reference> referenceSet) {
		
		List<Param> params = null;

		if (null != referenceSet) {
			params = new ArrayList<Param>(1024);
			Param p = Param.Factory.newInstance();
			p.setName("prec");
			p.setValue("");
			params.add(p);
		}
		return params;
	}

		
	private SequenceDocument createDocumentSequence(String type, String classname, ReferenceSet<Reference> references, String extended, String sequenceClassName) {

		SequenceDocument sd = null;

		if (null != references) {
			sd = SequenceDocument.Factory.newInstance();
			Sequence s = sd.addNewSequence();
			s.setClassname(sequenceClassName);
			s.setType(type); //lang
			s.setExtended(extended); //info
			//Il link alla precedente realizzato col param e' vuoto
			Object[] listOfParams= referenceToParamMapping(references).toArray();
			s.setParamArray(Arrays.copyOf(listOfParams,listOfParams.length, Param[].class));

			Object[] listOfElement = referencesToElementMapping(references, classname).toArray();
			s.setElementArray(Arrays.copyOf(listOfElement,listOfElement.length, Element[].class));

			s.setId(references.getId());
		}


		return sd;
	}
	
	private SequenceDocument createArabicPericopeSequence() {
		// TODO Auto-generated method stub
		return createDocumentSequence("ar", "refToken", getArabicPericope(), getArabicPericopeInfo(), "pericope");
	}

	private SequenceDocument createGreekPericopeSequence() {
		// TODO Auto-generated method stub
		return createDocumentSequence("grc", "refToken", getGreekPericope(), getGreekPericopeInfo(), "pericope");
	}

	private SequenceDocument createGreekArabicLinkSequence () {
		
		return createDocumentSequence("", "refPericope", getGreekArabicLink(), getFieldContentByName("id"), "link");
	}
	/*
	 * Trasforma 
	 */
	private void modelToXmlMapping () {

		setGreekTokenSequence(createGreekTokenSequenceDocument());
		setArabicTokenSequence(createArabicTokenSequenceDocument());
		setGreekPericopeSequence(createGreekPericopeSequence());
		setArabicPericopeSequence(createArabicPericopeSequence());

		setGreekArabicLinkSequence(createGreekArabicLinkSequence());
		//		try {
		//			XmlOptions opts = new XmlOptions();
		//			opts.setSavePrettyPrint();
		//			opts.setSavePrettyPrintIndent(4);
		//			getGreekTokenSequence().save(System.err, opts);
		//			getArabicTokenSequence().save(System.err, opts);
		//			getGreekPericopeSequence().save(System.err, opts);
		//			getArabicPericopeSequence().save(System.err, opts);
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
	}


	public void run () {
		importLegacyXml();
		modelToXmlMapping();
		//non la farei qua 
		//	exportNewXmlToExist ();
	}

	public static void main(String[] args) {
		System.err.println(Character.UnicodeBlock.GREEK);
		System.err.println(Character.UnicodeBlock.forName("ARABIC"));
		//Pattern p =  Pattern.compile("\\b.+?\\b", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		//Pattern p =  Pattern.compile("\\S+", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		//Pattern p =  Pattern.compile("(\\b.+?\\b|[\\p{Punct}\u00B6\u204B])", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		Pattern p =  Pattern.compile("(([^\\p{Punct}\u00B6\u060c\u204B\\s]+)|[\\p{Punct}\u060c\u00B6\u204B])", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		//Pattern p =  Pattern.compile("\\p{L}+", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		//Matcher m = p.matcher("tua'    madre quel,,,... budello - ὥσπερ¶¶¶κυοῦν ἀπ’ αὐτῶν καὶ ὠδῖνον γεννῆσαι, ποιεῖν. σπεύδε.");
		Matcher m = p.matcher("ولا يبقى في موضعه الأوّل،⁋لأنّه يشتاق إلى الفعل كثيرًا وإلى زَيْن الأشياء التي رآها في العقل.");
		while (m.find()) {
			if (m.group().trim().length() > 0) {
				System.err.println(m.group() + " " + m.start() + " " + m.end());
			}
		}
	}

}
