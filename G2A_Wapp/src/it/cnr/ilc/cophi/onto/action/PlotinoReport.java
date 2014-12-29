/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.cophi.onto.action;

import it.cnr.ilc.cophi.action.management.RepositoryBean;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PlotinoReport implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3552854684791859105L;


	@ManagedProperty(value="#{repository}")
	private  RepositoryBean repositoryBean;


	private  String selectedLemma = "";
	private  String selectedRelType = "all";

	/**
	 * @return the repositoryBean
	 */
	public RepositoryBean getRepositoryBean() {
		return repositoryBean;
	}

	/**
	 * @param repositoryBean the repositoryBean to set
	 */
	public void setRepositoryBean(RepositoryBean repositoryBean) {
		this.repositoryBean = repositoryBean;
	}


	public String getSelectedLemma() {
		return selectedLemma;
	}

	public void setSelectedLemma(String selectedLemma) {
		this.selectedLemma = selectedLemma;
	}


	public  String getSelectedRelType() {
		return selectedRelType;
	}

	public void setSelectedRelType(String selectedRelType) {
		this.selectedRelType = selectedRelType;
	}


	/**
	 * @return the ontoInstanceMap
	 */
	public Map<String, String> getOntoInstanceMap() {
		return repositoryBean.getOntoInstanceMap();
	}

	/**
	 * @return the greek2EnglishMap
	 */
	public Map<String, String> getGreek2EnglishMap() {
		return repositoryBean.getGreek2EnglishMap();
	}

	/**
	 * @return the ontoResult2JS
	 */
	public String getOntoResult2JS() {
		return repositoryBean.getOntoResult2JS();
	}

	public void setOntoResult2JS(String s) {
		repositoryBean.setOntoResult2JS(s);
	}

	public void runOntoQuery () {

		repositoryBean.runOntoQuery(selectedLemma, selectedRelType, getGreek2EnglishMap().get(selectedLemma));
	}
}
