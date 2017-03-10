/**
 * 
 */
package it.cnr.ilc.g2a.model.text;

import it.cnr.ilc.g2a.model.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author simone
 *
 */
public class TokenText extends Token {
	
    	private static final Logger log = LogManager.getLogger("TokenText");

	private int from;
	private int to;
	private String value;
	/**
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(int from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public int getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(int to) {
		this.to = to;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getText () {
		return getValue();
	}
	
	public String getTextOnCharSequence (CharSequence s) {
		String ret = null;
		if (getTo() > s.length()) {
			log.error(" ERR in getText("+ s.subSequence(0, 20) +")" + s.length() +", "+getFrom() + ":" + getTo());
			
		} else {
			ret = (s.subSequence(getFrom(), getTo())).toString(); 
		}
		return ret;
	}
	
}

