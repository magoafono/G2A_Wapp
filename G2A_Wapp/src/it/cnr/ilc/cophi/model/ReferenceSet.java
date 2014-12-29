package it.cnr.ilc.cophi.model;

import java.util.List;

public abstract class ReferenceSet<T extends Reference> {
	
	private String id;
	private List<T> value = null;
	private String type;
	private String classname;
	private String extended;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the value
	 */
	public List<T> getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(List<T> value) {
		this.value = value;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the classname
	 */
	public String getClassname() {
		return classname;
	}
	/**
	 * @param classname the classname to set
	 */
	public void setClassname(String classname) {
		this.classname = classname;
	}
	/**
	 * @return the extended
	 */
	public String getExtended() {
		return extended;
	}
	/**
	 * @param extended the extended to set
	 */
	public void setExtended(String extended) {
		this.extended = extended;
	}
}
