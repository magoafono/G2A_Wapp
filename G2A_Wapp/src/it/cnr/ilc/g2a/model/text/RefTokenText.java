package it.cnr.ilc.g2a.model.text;

import it.cnr.ilc.g2a.model.RefToken;

public class RefTokenText extends RefToken {

		
	private TokenText tok;

	/**
	 * @return the tok
	 */
	public TokenText getTok() {
		return tok;
	}

	/**
	 * @param tok the tok to set
	 */
	public void setTok(TokenText tok) {
		this.tok = tok;
	}
}
