/**
 *
 */
package it.cnr.ilc.g2a.action.userbean;

import it.cnr.ilc.g2a.action.management.RepositoryBean;
import it.cnr.ilc.g2a.model.Pericope;
import it.cnr.ilc.g2a.model.text.RefTokenText;
import it.cnr.ilc.g2a.model.view.LinkViewEntity;
import it.cnr.ilc.g2a.model.view.ResultViewEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.primefaces.event.data.PageEvent;

/**
 * @author Angelo Del Grosso
 *
 */
@ManagedBean
@SessionScoped
public class ParallelViewBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8761677373617116582L;

    private static final Logger log = LogManager.getLogger("ParallelViewBean");

    @ManagedProperty(value = "#{repository}")
    private RepositoryBean repositoryBean;

    @ManagedProperty(value = "#{sharedBean}")
    private SharedBean sharedBean;

    private List<LinkViewEntity> filteredPericopes;

    private ResultViewEntity selectedResultViewEntity = null;
    private int rowsPerPage = 20;
    private int rowOffset = 0;
    // Pagina del paginatore PER LINGUA: le viste greca e araba sono indipendenti.
    private final Map<String, Integer> paginatorPages = new HashMap<String, Integer>();
    // Lingue gia' posizionate dopo l'ultima selezione da ricerca.
    private final Set<String> positionedLangs = new HashSet<String>();
    // Ultima lingua resa (per associare gli eventi "page" alla vista giusta).
    private String currentLang = null;
    private boolean scrollToSelected = false;

    /**
     * @return the selectedResultViewEntity
     */
    public ResultViewEntity getSelectedResultViewEntity() {
        return selectedResultViewEntity;
    }

    /**
     * @param selectedResultViewEntity the selectedResultViewEntity to set
     */
    public void setSelectedResultViewEntity(
            ResultViewEntity selectedResultViewEntity) {
        this.selectedResultViewEntity = selectedResultViewEntity;
        //		System.err.println("da convertire: " + selectedResultViewEntity);

        //TODO fare la conversione
        if (null != selectedResultViewEntity) {
            setSelectedViewLink(repositoryBean.getLinkViewEntity(selectedResultViewEntity.getLinkId()));
            scrollToSelected = true;
            positionedLangs.clear(); // entrambe le viste torneranno a posizionarsi
        }
    }

    /**
     * Pagina iniziale del paginatore della vista pericopi. Se si arriva da una
     * selezione nei risultati di ricerca, la calcola una sola volta dalla posizione
     * del link scelto nella lista ordinata per {@code lang}; altrimenti lascia
     * l'ultima pagina usata dall'utente.
     */
    public int consumeInitialPage(String lang) {
        currentLang = lang;
        // Posiziona questa lingua una sola volta dopo una selezione da ricerca.
        if (scrollToSelected && !positionedLangs.contains(lang)) {
            LinkViewEntity selected = getSelectedViewLink();
            if (null != selected && null != selected.getLink()) {
                String selectedId = selected.getLink().getId();
                List<LinkViewEntity> links = repositoryBean.getLinks(lang);
                if (null != links) {
                    for (int i = 0; i < links.size(); i++) {
                        LinkViewEntity lve = links.get(i);
                        if (null != lve && null != lve.getLink() && selectedId.equals(lve.getLink().getId())) {
                            paginatorPages.put(lang, i / Math.max(1, rowsPerPage));
                            break;
                        }
                    }
                }
            }
            positionedLangs.add(lang);
        }
        return pageFor(lang);
    }

    private int pageFor(String lang) {
        Integer page = paginatorPages.get(lang);
        return null != page ? page : 0;
    }

    /**
     * @return the selectedViewLink
     */
    public LinkViewEntity getSelectedViewLink() {
        return sharedBean.getSelectedLinkViewEntity();
    }

    /**
     * @param selectedViewLink the selectedViewLink to set
     */
    public void setSelectedViewLink(LinkViewEntity selectedViewLink) {
        if (getSelectedViewLink() == null || !selectedViewLink.getLink().getId().equals(getSelectedViewLink().getLink().getId())) {
            sharedBean.setSelectedLinkViewEntity(selectedViewLink);
            sharedBean.setLoadNewAnalysis(true);
            // Sincronizza le due viste "Pericopes by": la coppia corrente (la stessa
            // in entrambi gli ordinamenti) va riposizionata/evidenziata anche
            // nell'altra lingua. Search resta invariata (ha il proprio stato).
            scrollToSelected = true;
            positionedLangs.clear();
        }
    }

    /**
     * Ri-arma il posizionamento a ogni click di riga, anche sulla coppia gia'
     * selezionata: cosi' ri-selezionare in una lingua fa ritrovare la stessa
     * coppia nell'altra. La paginazione manuale NON ri-arma (sfogliare resta libero).
     */
    public void onRowSelect() {
        scrollToSelected = true;
        positionedLangs.clear();
    }

    /**
     * @return the paginatorPage
     */
    public int getPaginatorPage() {
        return null != currentLang ? pageFor(currentLang) : 0;
    }

    /**
     * Evento pagina del paginatore: aggiorna la pagina della lingua in vista.
     */
    public void setPaginatorPage(PageEvent pe) {
        if (null != currentLang) {
            paginatorPages.put(currentLang, pe.getPage());
        }
    }

    /**
     * @return the rowsPerPage
     */
    public int getRowsPerPage() {
        return rowsPerPage;
    }

    /**
     * @param rowsPerPage the rowsPerPage to set
     */
    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

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

    /**
     * @return the sharedBean
     */
    public SharedBean getSharedBean() {
        return sharedBean;
    }

    /**
     * @param sharedBean the sharedBean to set
     */
    public void setSharedBean(SharedBean sharedBean) {
        this.sharedBean = sharedBean;
    }

    /**
     *
     */
    private Pericope<RefTokenText> selectedPericope;
    private String selectedContent;

    public ParallelViewBean() {
        // TODO Auto-generated constructor stub
        log.debug("parallel");
    }

    public List<String> getArabicTokenText() {
        //System.err.println("getArabicTokenText()");
        return repositoryBean.getArabicTokens();
    }

    public List<LinkViewEntity> getLinks() {

        return repositoryBean.getLinks();
    }

    /**
     * Retrive all couple of pericopes. It is used by parallelView.xhtml
     *
     * @param lang
     * @return
     */
    public List<LinkViewEntity> getLinks(String lang) {

        //System.err.println("getLinks() " + lang);
        return repositoryBean.getLinks(lang);
    }

    //	public ArrayList<Pericope<Reference>> getPericopesByArabic(){
    //		
    //		return (ArrayList<Pericope<Reference>>) repositoryBean.getPericopes
    //		
    //	}
    ////	
    //	public ArrayList<Pericope> getPericopesByGreek(){
    //		
    //		return repositoryBean.getPericopesOrderedByGreek();
    //		
    //	}
    //
    ///**
    // * @return the managerBean
    // */
    //	public ManagerBean getManagerBean() {
    //		return repositoryBean;
    //	}
    //
    ///**
    // * @param managerBean the managerBean to set
    // */
    //	public void setManagerBean(ManagerBean managerBean) {
    //		this.repositoryBean = managerBean;
    //	}
    /**
     * @return the selectedPericope
     */
    public Pericope getSelectedPericope() {
        return selectedPericope;
    }

    /**
     * @param selectedPericope the selectedPericope to set
     */
    public void setSelectedPericope(Pericope selectedPericope) {
        this.selectedPericope = selectedPericope;
        log.debug("select pericope in parallel");
    }

    /**
     * @return the selectedContent
     */
    public String getSelectedContent() {

        return selectedContent;
    }

    /**
     * @param selectedContent the selectedContent to set
     */
    public void setSelectedContent(String selectedContent) {
        this.selectedContent = selectedContent;
        log.debug("selected content da table view");
    }

    /**
     * @return the filteredPericopes
     */
    public List<LinkViewEntity> getFilteredPericopes() {
        return filteredPericopes;
    }

    /**
     * @param filteredPericopes the filteredPericopes to set
     */
    public void setFilteredPericopes(List<LinkViewEntity> filteredPericopes) {
        this.filteredPericopes = filteredPericopes;
    }

    public String logout() {
        log.info("logout");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "Home.xhtml?faces-redirect=true";
    }

    /**
     * @return the rowSelect
     */
    public int getRowOffset() {
        return rowOffset;
    }

    /**
     * @param rowSelect the rowSelect to set
     */
    public void setRowOffset(String offset) {
        if (null != offset && !"".equals(offset)) {
            this.rowOffset = Integer.parseInt(offset);
        } else {
            log.debug("offset: " + offset);
        }
    }

}
