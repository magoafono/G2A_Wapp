package it.cnr.ilc.cophi.model.text;


import it.cnr.ilc.cophi.model.Pericope;
import it.cnr.ilc.cophi.model.Reference;

import java.util.List;

public class PericopeText extends Pericope<Reference>{

	/**
	 * Costruisce il testo di ogni pericope a partire dai valori dei token che (la pericope) contiene
	 * Per ottimizzazione sono stati messi nelle pericopi anche i token (veri e propri) che contengono
	 * @return
	 */
	public String getText() {

		List<Reference> list = getValue();
		
		StringBuffer sb = new StringBuffer();
		
		for (Reference reference : list) {
			
			RefTokenText token = (RefTokenText) reference;
			sb.append(token.getExtended());
			sb.append(" ");
		}
		
		return sb.toString().trim();
	}
	
	/**
	 * 
	 * @param cb
	 * @return
	 */
	@Deprecated
	public String getTextByCharSequence(CharSequence cb) {

		List<Reference> list = getValue();
		
		int start = 0;
		int end = 0;

		if (null != list && list.size() > 0){
			RefTokenText firstToken = (RefTokenText) list.get(0);
			RefTokenText lastToken = (RefTokenText) list.get(list.size() - 1);
			if (firstToken.getTok() != null) {
				start = firstToken.getTok().getFrom();
			} else {
				System.err.println("Error in get pericope text firstToken.getId() " + firstToken.getId());
			}
			if (lastToken.getTok() != null) {
				end = lastToken.getTok().getTo();
			} else {
				System.err.println("Error in get pericope text lastToken.getId(): " + lastToken.getId());
			}
		}
		return cb.subSequence(start, end).toString();
	}


}
