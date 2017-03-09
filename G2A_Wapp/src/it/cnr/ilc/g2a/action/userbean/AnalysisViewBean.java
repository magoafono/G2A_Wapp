package it.cnr.ilc.g2a.action.userbean;

import it.cnr.ilc.g2a.action.management.RepositoryBean;
import it.cnr.ilc.g2a.model.view.LinkViewEntity;
import it.cnr.ilc.g2a.utils.Consts;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped
public class AnalysisViewBean {


	@ManagedProperty(value="#{repository}")
	private RepositoryBean repositoryBean;

	@ManagedProperty(value="#{sharedBean}")
	private SharedBean sharedBean;

	//Work-around per la doppia invocazione del metodo di calcolo delle analisi
	private boolean isGreekAlreadyDone = false;
	private List<AnalysisBean> greekAnalyses = null;

	private boolean isArabicAlreadyDone = false;
	private List<AnalysisBean> arabicAnalyses = null;

	private String greekAnalysisLoaded = null;
	private String arabicAnalysisLoaded = null;

	private boolean viewArabic = true;

	public AnalysisViewBean() {
		// TODO Auto-generated constructor stub
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
	 * @return the isArabicAlreadyDone
	 */
	public boolean isArabicAlreadyDone() {
		return isArabicAlreadyDone;
	}

	/**
	 * @param isArabicAlreadyDone the isArabicAlreadyDone to set
	 */
	public void setArabicAlreadyDone(boolean isArabicAlreadyDone) {
		this.isArabicAlreadyDone = isArabicAlreadyDone;
	}

	/**
	 * @return the isGreekAlreadyDone
	 */
	public boolean isGreekAlreadyDone() {
		return isGreekAlreadyDone;
	}

	/**
	 * @param isGreekAlreadyDone the isGreekAlreadyDone to set
	 */
	public void setGreekAlreadyDone(boolean isGreekAlreadyDone) {
		this.isGreekAlreadyDone = isGreekAlreadyDone;
	}

	/**
	 * @return the greekAnalyses
	 */
	public List<AnalysisBean> getGreekAnalyses() {
		return greekAnalyses;
	}

	/**
	 * @param greekAnalyses the greekAnalyses to set
	 */
	public void setGreekAnalyses(List<AnalysisBean> greekAnalyses) {
		this.greekAnalyses = greekAnalyses;
	}

	/**
	 * @return the arabicAnalyses
	 */
	public List<AnalysisBean> getArabicAnalyses() {
		return arabicAnalyses;
	}

	/**
	 * @param arabicAnalyses the arabicAnalyses to set
	 */
	public void setArabicAnalyses(List<AnalysisBean> arabicAnalyses) {
		this.arabicAnalyses = arabicAnalyses;
	}


	/**
	 * @return the viewArabic
	 */
	public boolean isViewArabic() {
		return viewArabic;
	}

	/**
	 * @param viewArabic the viewArabic to set
	 */
	public void setViewArabic(boolean viewArabic) {
		this.viewArabic = viewArabic;
	}

	/**
	 * @return the greekAnalysisLoaded
	 */
	public String getGreekAnalysisLoaded() {
		return greekAnalysisLoaded;
	}

	/**
	 * @param greekAnalysisLoaded the greekAnalysisLoaded to set
	 */
	public void setGreekAnalysisLoaded(String greekAnalysisLoaded) {
		this.greekAnalysisLoaded = greekAnalysisLoaded;
	}

	/**
	 * @return the greekarabicAnalysisLoaded
	 */
	public String getArabicAnalysisLoaded() {
		return arabicAnalysisLoaded;
	}

	/**
	 * @param greekarabicAnalysisLoaded the greekarabicAnalysisLoaded to set
	 */
	public void setArabicAnalysisLoaded(String arabicAnalysisLoaded) {
		this.arabicAnalysisLoaded = arabicAnalysisLoaded;
	}

	public List<AnalysisBean> getRetrieveArabicAnalysisOld() {
		if (isArabicAlreadyDone() == false) {
			if (!getSelectedViewLink().isArabicPericopeHidden()) {
				setArabicAnalyses(repositoryBean.getPericopeAnalysis(getSelectedViewLink().getArabicPericopeRefId(), Consts.ARABIC));
			} else {
				setArabicAnalyses(null);
			}
			setArabicAlreadyDone(true);
		} else {
			setArabicAlreadyDone(false);
		}
		return getArabicAnalyses();
	}

	public List<AnalysisBean> getRetrieveGreekAnalysisOld() {

		if (isGreekAlreadyDone() == false) {
			if (!getSelectedViewLink().isGreekPericopeHidden()) {
				setGreekAnalyses(repositoryBean.getPericopeAnalysis(getSelectedViewLink().getGreekPericopeRefId(), Consts.GREEK));
			} else {
				setGreekAnalyses(null);
			}
			setGreekAlreadyDone(true);
		} else {
			setGreekAlreadyDone(false);
		}
		System.err.println("getRetrieveGreekAnalysis (" + isGreekAlreadyDone() + ")\n");  
		return getGreekAnalyses();
	}

	public void loadAnalyses () {

		if (sharedBean.isLoadNewAnalysis())  {
			if (null != getGreekAnalysisLoaded() ){
				//	if (!getGreekAnalysisLoaded().equals(getSelectedViewLink().getGreekPericopeRefId())) {
				if (!getSelectedViewLink().isGreekPericopeHidden()) {
					setGreekAnalyses(repositoryBean.getPericopeAnalysis(getSelectedViewLink().getGreekPericopeRefId(), Consts.GREEK));
					setGreekAnalysisLoaded(getSelectedViewLink().getGreekPericopeRefId());
				} else {
					setGreekAnalyses(null);
				}
				//	}
			} else {
				if (!getSelectedViewLink().isGreekPericopeHidden()) {// TODO qui capita che schianti  (Caused by: java.lang.NullPointerException) non so perche' 
					setGreekAnalyses(repositoryBean.getPericopeAnalysis(getSelectedViewLink().getGreekPericopeRefId(), Consts.GREEK));
					setGreekAnalysisLoaded(getSelectedViewLink().getGreekPericopeRefId());
				} else {
					setGreekAnalyses(null);
				}
			}

			if (null != getArabicAnalysisLoaded() ){
				//	if (!getArabicAnalysisLoaded().equals(getSelectedViewLink().getArabicPericopeRefId())) {
				if (!getSelectedViewLink().isArabicPericopeHidden()) {
					setArabicAnalyses(repositoryBean.getPericopeAnalysis(getSelectedViewLink().getArabicPericopeRefId(), Consts.ARABIC));
					setArabicAnalysisLoaded(getSelectedViewLink().getArabicPericopeRefId());
				} else {
					setArabicAnalyses(null);
				}
				//	}
			} else {
				if (!getSelectedViewLink().isArabicPericopeHidden()) {
					setArabicAnalyses(repositoryBean.getPericopeAnalysis(getSelectedViewLink().getArabicPericopeRefId(), Consts.ARABIC));
					setArabicAnalysisLoaded(getSelectedViewLink().getArabicPericopeRefId());
				} else {
					setArabicAnalyses(null);
				}
			}
			sharedBean.setLoadNewAnalysis(false);
		}

	}

	/**
	 * @return the greek analysis
	 */
	public List<AnalysisBean> getRetrieveGreekAnalysis() {

		return getGreekAnalyses();
	}
	public List<AnalysisBean> getRetrieveArabicAnalysis() {


		return getArabicAnalyses();
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
		sharedBean.setSelectedLinkViewEntity(selectedViewLink);
	}	

	public String getFormattedArabicPericopeText ( ){
		//da usare con white-space:pre direttiva CSS per avere i ritorni a capo in html dove effettivamente devono essere
		return getSelectedViewLink().getArabicPericopeText().replaceAll("\\s*↳\\s*", "\n");  
	}

	public String getFormattedGreekPericopeText ( ){
		//da usare con white-space:pre direttiva CSS per avere i ritorni a capo in html dove effettivamente devono essere
		return getSelectedViewLink().getGreekPericopeText().replaceAll("\\s*↲\\s*", "\n");
	}

	
	public void switchViewArabic () {
		
		setViewArabic(!isViewArabic());
	}
}

