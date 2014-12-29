package it.cnr.ilc.cophi.model;

public abstract class Pericope<T extends Reference> extends ReferenceSet<T> {

	private String info = null;

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	



}
