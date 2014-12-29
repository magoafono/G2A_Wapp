/**
 * 
 */
package it.cnr.ilc.cophi.action.userbean;

import it.cnr.ilc.cophi.action.management.RepositoryBean;
import it.cnr.ilc.cophi.model.Pericope;
import it.cnr.ilc.cophi.model.text.RefTokenText;
import it.cnr.ilc.cophi.model.view.LinkViewEntity;
import it.cnr.ilc.cophi.model.view.ResultViewEntity;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

	private static Logger logger = Logger.getLogger("GAlogger"); 

	@ManagedProperty(value="#{repository}")
	private RepositoryBean repositoryBean;

	@ManagedProperty(value="#{sharedBean}")
	private SharedBean sharedBean;

	private List<LinkViewEntity> filteredPericopes;
	
	private ResultViewEntity selectedResultViewEntity = null;
	private int paginatorPage = 0;
	private int rowsPerPage = 5;
	private int rowOffset = 0;
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
		if (null != selectedResultViewEntity){
			setSelectedViewLink(repositoryBean.getLinkViewEntity(selectedResultViewEntity.getLinkId()));
		} 
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
		}
	}

	/**
	 * @return the paginatorPage
	 */
	public int getPaginatorPage() {
		return paginatorPage;
	}

	/**
	 * @param paginatorPage the paginatorPage to set
	 */
	public void setPaginatorPage(PageEvent pe) {

		this.paginatorPage = pe.getPage();
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
		logger.fine("parallel");
	}


	public List<String> getArabicTokenText () {
		//System.err.println("getArabicTokenText()");
		return repositoryBean.getArabicTokens();
	}


	public List<LinkViewEntity> getLinks() {

		return repositoryBean.getLinks();
	}

	/**
	 * Retrive all couple of pericopes. It is used by parallelView.xhtml
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
		logger.fine("select pericope in parallel");
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
		logger.fine("selected content da table view");
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
		System.err.println("logout");
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
			System.err.println("offset: " + offset);
		}
	}


}
