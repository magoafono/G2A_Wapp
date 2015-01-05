package it.cnr.ilc.cophi.exception;

public class LanguageUnknownException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2501078905209286989L;

	
	public LanguageUnknownException(String message) {
		super(message);
	}
	
	public LanguageUnknownException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	
}
