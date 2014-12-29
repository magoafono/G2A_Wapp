package it.cnr.ilc.cophi.model.view;

public class SelectedTextBoundaries {

	private Integer start;
	private Integer end;

	public SelectedTextBoundaries (String start, String end) {
		this.start = Integer.parseInt(start);
		this.end= Integer.parseInt(end);
	}
	
	public SelectedTextBoundaries() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public Integer getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(Integer end) {
		this.end = end;
	}
	
}
