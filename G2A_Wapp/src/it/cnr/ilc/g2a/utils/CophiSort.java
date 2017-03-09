package it.cnr.ilc.g2a.utils;

import it.cnr.ilc.g2a.model.Link;
import it.cnr.ilc.g2a.model.Reference;
import it.cnr.ilc.g2a.model.comment.Comment;
import it.cnr.ilc.g2a.model.text.TokenText;
import it.cnr.ilc.g2a.model.view.LinkViewEntity;
import it.cnr.ilc.g2a.model.view.TokenViewEntity;

import java.util.Comparator;

public class CophiSort {

	public static final Comparator<Comment> 
	COMMENT_ID_ORDER = 
	new Comparator<Comment>() {

		public int compare(Comment c1, Comment c2) {
			String id1str = c1.getCommentId();
			String id2str = c2.getCommentId();
			int id1 = Integer.parseInt(id1str.substring(id1str.lastIndexOf('_') + 1));
			int id2 = Integer.parseInt(id2str.substring(id2str.lastIndexOf('_') + 1));
			return 	(id1 < id2) ? -1 : (id1 == id2) ? 0 : 1;
		}
	};

	public static final Comparator<TokenViewEntity> 
	TOKENVIEW_FROM_ORDER = 
	new Comparator<TokenViewEntity>() {

		public int compare(TokenViewEntity c1, TokenViewEntity c2) {
			int  id1 = c1.getFrom();
			int  id2 = c2.getFrom();

			return 	(id1 < id2) ? -1 : (id1 == id2) ? 0 : 1;
		}
	};

	public static final Comparator<LinkViewEntity> 
	GREEKLINKVIEW_FROM_ORDER2 = 
	new Comparator<LinkViewEntity>() {

		public int compare(LinkViewEntity c1, LinkViewEntity c2) {
			Link<Reference> l1 = c1.getLink();
			float id1 = Float.parseFloat(l1.getValue().get(Consts.GREEK).getRef());
			Link<Reference> l2 = c2.getLink();
			float id2 =  Float.parseFloat(l2.getValue().get(Consts.GREEK).getRef());

			//System.err.println("sort: " + id1 + " " + id2 + " => " + ((id1 < id2) ? -1 : (id1 == id2) ? 0 : 1));
			return 	(id1 < id2) ? -1 : (id1 == id2) ? 0 : 1;
		}
	};


	/**
	 * Ordinamento per le pericopi arabe. E' stato modificato dall'ordinamento usando i ref come float
	 * perche' non funziona nel caso in cui una pericope sia a cavallo di 2 pagine.
	 * es. 29.1403 verrebbe prima di 29.1414 perche' confrontandoli come float il secondo e' piu' grande
	 * e quindi ipotizzato successivo, al primo che invece e' a cavallo della pagina 29 e 30 e quindi successivo al secondo
	 * Per ovviare al problema si scompone il ref in pagina, riga di inizio e riga di fine.
	 */
	public static final Comparator<LinkViewEntity> 
	ARABICLINKVIEW_FROM_ORDER2 = 
	new Comparator<LinkViewEntity>() {

		public int compare(LinkViewEntity c1, LinkViewEntity c2) {

			int ret = 0;

			Link<Reference> l1 = c1.getLink();
			String ref1str = l1.getValue().get(Consts.ARABIC).getRef();
			float id1 = Float.parseFloat(ref1str);
			Link<Reference> l2 = c2.getLink();
			String ref2str = l2.getValue().get(Consts.ARABIC).getRef();
			float id2 =  Float.parseFloat(l2.getValue().get(Consts.ARABIC).getRef());

			try {
				String pagina1str = ref1str.substring(0, ref1str.indexOf("."));
				String righe1str = ref1str.substring(ref1str.indexOf(".") + 1);
				String start1str = righe1str.substring(0, 2);
				String end1str = righe1str.substring(2);

				String pagina2str = ref2str.substring(0, ref2str.indexOf("."));
				String righe2str = ref2str.substring(ref2str.indexOf(".") + 1);
				String start2str = righe2str.substring(0, 2);
				String end2str = righe2str.substring(2);
				//System.err.println(start2str + " " + end2str);

				int pagina1 = Integer.parseInt(pagina1str);
				//	int righe1 = Integer.parseInt(righe1str);
				int start1 = Integer.parseInt(start1str);
				int end1 = Integer.parseInt(end1str);

				int pagina2 = Integer.parseInt(pagina2str);
				//	int righe2 = Integer.parseInt(righe2str);
				int start2 = Integer.parseInt(start2str);
				int end2 = Integer.parseInt(end2str);


				//System.err.println("sort: " + id1 + " " + id2 + " => " + ((id1 < id2) ? -1 : (id1 == id2) ? 0 : 1));
				//return 	(id1 < id2) ? -1 : (id1 == id2) ? 0 : 1;
				if (pagina1 < pagina2) {
					ret = -1;
				} else if (pagina1 > pagina2) {
					ret = 1;
				} else {
					if (start1 > end1) {
						ret = 1;
					} else if (start2 > end2){
						ret =  -1;
					} else {
						ret = (end1 > start2)?1:-1;
					}
				}

			} catch (java.lang.StringIndexOutOfBoundsException e) {
				//System.err.println("sort: " + ref1str + " " + ref2str + " => " + ret);
				/*
				 * Se mi da eccezione e' perche' uno dei due non ha il punto di delimitazione della pagina,
				 * quindi vuole dire che una delle due (o entrambe) non hanno corrispondente greco(?) e si mettono in fondo
				 */

				ret = (id1 < id2) ? -1 : (id1 == id2) ? 0 : 1;
			}
			//System.err.println("sort: " + id1 + " " + id2 + " => " + ret);
			return ret;
		}
	};


	public static final Comparator<LinkViewEntity> 
	ARABICLINKVIEW_FROM_ORDER3 = 
	new Comparator<LinkViewEntity>() {

		public int compare(LinkViewEntity c1, LinkViewEntity c2) {
			Link<Reference> l1 = c1.getLink();
			String v1 = l1.getValue().get(Consts.ARABIC).getRef();
			Link<Reference> l2 = c2.getLink();
			String v2 = l2.getValue().get(Consts.ARABIC).getRef();
			String[] components1 = v1.split("\\.");
			String[] components2 = v2.split("\\.");
			int length = Math.min(components1.length, components2.length);
			for(int i = 0; i < length; i++) {
				int result = new Integer(components1[i]).compareTo(Integer.parseInt(components2[i]));
				if(result != 0) {
					return result;
				}
			}
			return components1.length - components2.length;
		}
	};
	
	private static int compareId (String v1, String v2) {
		
		if (v1 == null || v2 == null) {
			
			System.err.println("ERR: CophiSort.compareId (" + v1 + ", " + v2 + ")");
			return 0;
		}
		String[] components1 = v1.split("\\.");
		String[] components2 = v2.split("\\.");
		int length = Math.min(components1.length, components2.length);
		for(int i = 0; i < length; i++) {
			int result = new Integer(components1[i]).compareTo(Integer.parseInt(components2[i]));
			if (components1[i].contains("-") || components2[i].contains("-")) {
				/*
				 * necessario per ordinare correttamente le pericopi con id negativo (ultima parte dell'id)
				 * altrimenti l'ordinamento è invertito per la presenza della parte negativa
				 */
				result = -result;
			}
			if(result != 0) {
				return result;
			}
		}
		System.err.println ("Come mai qui??? " + v1 + " " + v2 + " " + components1.length + " " + components2.length);
		return components1.length - components2.length;
	}
	
	public static final Comparator<LinkViewEntity> 
	GREEKLINKVIEW_FROM_ORDER = 
	new Comparator<LinkViewEntity>() {

		public int compare(LinkViewEntity lve1, LinkViewEntity lve2) {
			
			String v1 = lve1.getGreekPericopeId();
			String v2 = lve2.getGreekPericopeId();
				
			return compareId (v1, v2);

		}
	};
	
	public static final Comparator<LinkViewEntity> 
	ARABICLINKVIEW_FROM_ORDER = 
	new Comparator<LinkViewEntity>() {

		public int compare(LinkViewEntity lve1, LinkViewEntity lve2) {
			String v1 = lve1.getArabicPericopeId();
			String v2 = lve2.getArabicPericopeId();

			return compareId (v1, v2);

		}
	};
	public static final Comparator<TokenText> 
	TOKENTEXT_FROM_ORDER = 
	new Comparator<TokenText>() {

		public int compare(TokenText c1, TokenText c2) {
			int  id1 = c1.getFrom();
			int  id2 = c2.getFrom();

			return 	(id1 < id2) ? -1 : (id1 == id2) ? 0 : 1;
		}
	};

	public static final Comparator<Reference> 
	TOKENREF_FROM_ORDER = 
	new Comparator<Reference>() {

		public int compare(Reference c1, Reference c2) {
			String id1str = c1.getRef(); //es. 43190.000002
			String id2str = c2.getRef();

			double id1 = Double.parseDouble(id1str);
			double id2 = Double.parseDouble(id2str);
			
			return (id1 < id2) ? -1 : (id1 == id2) ? 0 : 1;

		}
	};

	public static final Comparator<Reference> 
	TOKENREF_FROM_ORDER_OLD = 
	new Comparator<Reference>() {

		public int compare(Reference c1, Reference c2) {
			String id1str = c1.getRef();
			String id2str = c2.getRef();

			int id1 = Integer.parseInt(id1str.substring(id1str.lastIndexOf('_') + 1));
			int id2 = Integer.parseInt(id2str.substring(id2str.lastIndexOf('_') + 1));

			return 	(id1 < id2) ? -1 : (id1 == id2) ? 0 : 1;
		}
	};

	

	
	public static void main(String[] args) {
		System.err.println(Float.parseFloat("29.1403") > Float.parseFloat("29.1414"));
	}

}
