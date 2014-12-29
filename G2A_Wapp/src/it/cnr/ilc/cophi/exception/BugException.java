package it.cnr.ilc.cophi.exception;

public class BugException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1284095843577096603L;
	
	public BugException() { super(); }
	public BugException(String message) { super(message); }
	public BugException(String message, Throwable cause) { super(message, cause); }
	public BugException(Throwable cause) { super(cause); }
}
