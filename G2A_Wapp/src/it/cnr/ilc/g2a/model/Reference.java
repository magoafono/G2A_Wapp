package it.cnr.ilc.g2a.model;

public abstract class Reference {

	private String id;
	private String ref;
	private String extended;
	private String classname;
	
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
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
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
	public void setExtended(String ext) {
		this.extended = ext;
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


}
