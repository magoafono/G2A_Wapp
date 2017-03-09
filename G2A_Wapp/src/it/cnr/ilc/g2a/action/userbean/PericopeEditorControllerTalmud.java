package it.cnr.ilc.g2a.action.userbean;


import it.cnr.ilc.g2a.action.management.RepositoryBean;
import it.cnr.ilc.g2a.utils.view.BaseController;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PericopeEditorControllerTalmud extends BaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8195576909186725358L;
//	private String arabicText = null;
//	private String greekText = null;
		
	
	@ManagedProperty(value="#{repository}")
	private RepositoryBean repositoryBean;
	
	/**
	 * @return the arabicText
	 */
	public String getArabicText() {
		return "<span class=\"pericope\" rel=\"12\" id=\"2\"><span class=\"token\" rel=\"12\" id=\"1\">إلى</span><span class=\"token\">&nbsp;&nbsp;&nbsp;</span><span class=\"token\" rel=\"12\" id=\"2\">موضعه</span></span>";

		//return repositoryBean.getArabicPericopeDomAsString();
	}

	/**
	 * @param arabicText the arabicText to set
	 */
	public void setArabicText(String arabicText) {
		System.err.println("Arabic: " + arabicText);
	}

	/**

	/**
	 * @return the greekText
	 */
	public String getGreekText() {
		return "<span class=\"pericope\" rel=\"12\" id=\"2\"><span class=\"token\" rel=\"12\" id=\"1\">angelo</span><span class=\"token\">&nbsp;&nbsp;&nbsp;</span><span class=\"token\" rel=\"12\" id=\"2\">mario</span></span>";
		//return repositoryBean.getGreekPericopeDomAsString();
	}

	/**
	 * @param greekText the greekText to set
	 */
	public void setGreekText(String greekText) {
		System.err.println("Greek: " + greekText);
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

	@Override
	public String autoTag(String description) {
		//return processor.process(description).html();
		System.err.println("autoTag " + description);
		return description;
	}
}
