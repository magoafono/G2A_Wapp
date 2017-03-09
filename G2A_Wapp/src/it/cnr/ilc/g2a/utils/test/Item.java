package it.cnr.ilc.g2a.utils.test;

public class Item {


	private long id;
	private String value;
	
	public Item(long l, String string) {
		// TODO Auto-generated constructor stub
		id = l;
		value = string;
	}

	public Item() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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

}
