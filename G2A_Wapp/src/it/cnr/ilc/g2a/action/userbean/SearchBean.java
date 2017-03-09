package it.cnr.ilc.g2a.action.userbean;

import java.util.Arrays;
import java.util.List;


public class SearchBean {

	private List<String> word = Arrays.asList(new String[] { null , null, null });
	private List<String> type = Arrays.asList(new String[] { null , null, null });
	private List<String> pos = Arrays.asList(new String[] { null , null, null });
	private String op = null;

	/**
	 * @return the word
	 */
	public List<String> getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(List<String> word) {
		this.word = word;
	}

	/**
	 * @return the type
	 */
	public List<String> getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(List<String> type) {
		this.type = type;
	}

	/**
	 * @return the pos
	 */
	public List<String> getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(List<String> pos) {
		this.pos = pos;
	}

	/**
	 * @return the op
	 */
	public String getOp() {
		return op;
	}

	/**
	 * @param op the op to set
	 */
	public void setOp(String op) {
		this.op = op;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			sb.append(i + ": " + getWord().get(i) + " "+ getType().get(i) + " " + getPos().get(i) + "\n");
		}
		sb.append(" => " + getOp() + "\n");
		return sb.toString();
	}


}
