package it.cnr.ilc.cophi.action.userbean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class SearchViewBeanDynamic  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5106285848810682454L;

	private SearchBean item = null;
	private int index;
	private ArrayList<SearchBean> entryFields = null;

	@PostConstruct
	public void init() {
/*
		entryFields = new ArrayList<SearchBean>();
		SearchBean sb = new SearchBean();
		sb.setType("");
		sb.setWord("");
		sb.setPos("");
		sb.setIndex("0");
		sb.setOperator("");
		entryFields.add(sb);
		item = new SearchBean(Integer.toString(entryFields.size()));*/
		/*sb = new SearchBean();
		sb.setType("forma");
		sb.setWord("");
		sb.setPos("");
		sb.setIndex("1");
		entryFields.add(sb);*/
		System.err.println("SearchViewBean init()");
	}

	/**
	 * @return the item
	 */
	public SearchBean getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(SearchBean item) {
		this.item = item;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/*
	public void addInput(ActionEvent e) {
		if (entryFields.size() == 3) {
			System.out.println("excedded limit");
			return;
		}
		SearchBean last = entryFields.get(entryFields.size() - 1);
		if ("".equals(last.getOperator())) {
			last.setOperator("And");
		}

		entryFields.add(item);
		item = new SearchBean();
		item.setIndex(Integer.toString(entryFields.size()));
		for (int i=0; i < entryFields.size(); i++) {
			SearchBean sbi = entryFields.get(i);
			System.err.println(i + " : " + sbi);

		}

	}*/

	/**
	 * @return the entryField
	 */
	public ArrayList<SearchBean> getEntryFields() {
	//	System.err.println("SearchViewBean getEntryFields() " + entryFields.size());

		return entryFields;
	}

	/**
	 * @param entryField the entryField to set
	 */
	public void setEntryFields(ArrayList<SearchBean> entryFields) {
		System.err.println("SearchViewBean setEntryFields() " + entryFields.size());
		this.entryFields = entryFields;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void onChangeOperator() {
		
	//	System.err.println("operator is (" + item.getOperator() + ")");
		System.err.println("index is (" + getIndex() + ")");
		
	}
	
	
}
