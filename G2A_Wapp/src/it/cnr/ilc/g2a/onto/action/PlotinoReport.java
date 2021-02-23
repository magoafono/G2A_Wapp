/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.g2a.onto.action;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import it.cnr.ilc.g2a.action.management.RepositoryBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class PlotinoReport implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3552854684791859105L;

    private static final Logger LOG = LogManager.getLogger("PlotinoReport");

    @ManagedProperty(value = "#{repository}")
    private RepositoryBean repositoryBean;

    private String selectedLemma = "";
    private String selectedRelType = "all";

    private Integer canvasHeight;
    private Integer canvasWidth;

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
        LOG.debug("getSelectedLemma() " + selectedLemma);
        return selectedLemma;
    }

    public void setSelectedLemma(String selectedLemma) {
        this.selectedLemma = selectedLemma;
    }

    public String getSelectedRelType() {
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

    public void runOntoQuery() {
        LOG.info("selectdLemma: " + selectedLemma);
        repositoryBean.runOntoQuery(selectedLemma, selectedRelType, getGreek2EnglishMap().get(selectedLemma));
    }

    public Integer getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(Integer canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public Integer getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(Integer canvasWidth) {
        this.canvasWidth = canvasWidth;
    }
    public void draw() {

        LOG.info("call draw() height=(" + canvasHeight + ")");
       // System.err.println("canvasHeight " + canvasHeight);
        LOG.info("call draw() width=(" + canvasWidth + ")");
        RequestContext.getCurrentInstance().execute("drawGraph(" + canvasHeight + "," + canvasWidth + " );");
    }

}
