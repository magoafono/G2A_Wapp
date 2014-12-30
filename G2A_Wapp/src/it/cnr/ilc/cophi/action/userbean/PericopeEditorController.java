package it.cnr.ilc.cophi.action.userbean;


import it.cnr.ilc.cophi.action.management.RepositoryBean;
import it.cnr.ilc.cophi.model.Link;
import it.cnr.ilc.cophi.model.text.RefPericopeText;
import it.cnr.ilc.cophi.model.view.TokenViewEntity;
import it.cnr.ilc.cophi.utils.Consts;
import it.cnr.ilc.cophi.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class PericopeEditorController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8195576909186725358L;
	//	private String arabicText = null;
	//	private String greekText = null;


	@ManagedProperty(value="#{repository}")
	private RepositoryBean repositoryBean;

	@ManagedProperty(value="#{parallelViewBean}")
	private ParallelViewBean parallelViewBean;

	private HashMap<String, TokenViewEntity> selectedGreekTokenView = null;
	private HashMap<String, TokenViewEntity> selectedArabicTokenView = null;

	private TokenViewEntity leftArabicBoudary = null;
	private TokenViewEntity rightArabicBoudary = null;

	private TokenViewEntity leftGreekBoudary = null;
	private TokenViewEntity rightGreekBoudary = null;



	/**
	 * @return the parallelViewBean
	 */
	public ParallelViewBean getParallelViewBean() {
		return parallelViewBean;
	}

	/**
	 * @param parallelViewBean the parallelViewBean to set
	 */
	public void setParallelViewBean(ParallelViewBean parallelViewBean) {
		this.parallelViewBean = parallelViewBean;
	}

	/**
	 * @return the selectedGreekTokenView
	 */
	public HashMap<String, TokenViewEntity> getSelectedGreekTokenView() {
		return selectedGreekTokenView;
	}

	/**
	 * @param selectedGreekTokenView the selectedGreekTokenView to set
	 */
	public void setSelectedGreekTokenView(
			HashMap<String, TokenViewEntity> selectedGreekTokenView) {
		this.selectedGreekTokenView = selectedGreekTokenView;
	}

	/**
	 * @return the selectedArabicTokenView
	 */
	public HashMap<String, TokenViewEntity> getSelectedArabicTokenView() {
		return selectedArabicTokenView;
	}

	/**
	 * @param selectedArabicTokenView the selectedArabicTokenView to set
	 */
	public void setSelectedArabicTokenView(
			HashMap<String, TokenViewEntity> selectedArabicTokenView) {
		this.selectedArabicTokenView = selectedArabicTokenView;
	}	

	/**
	 * @return the arabicText
	 */
	public List<TokenViewEntity> getArabicTokenViewList() {

		/* Spostato la creazione dello style da jsf a java
		 * styleClass="link_nodecor bk_#{pericopeEditorController.isArabicTokenViewToList(w)?'7':w.color eq -1?9:w.pericopeId eq pericopeEditorController.selectedArabicPericopeId?'arfocused':w.color%4} arabic"
		 */

		List<TokenViewEntity> lotve = new ArrayList<TokenViewEntity>();
		String selectedPericopeId = getSelectedArabicPericopeId();
		/*
		 * es. di range di una pericope 02202.02203
		 * in generale: ppprr dove ppp e' il numero di pagina e rr numero di riga
		 * la coppia separata dal punto si riferisce all'inizio della pericope (a sx del punto)
		 * e alla fine della pericope (a dx del punto)
		 */
		if (selectedPericopeId.contains(".")) {
			String[] limits = selectedPericopeId.split("\\.");

			int startPage = Integer.parseInt(limits[0].substring(0, 3));
			//int startRow  = Integer.parseInt(limits[0].substring(3));
			int endPage   = Integer.parseInt(limits[1].substring(0, 3));
			//int endRow    = Integer.parseInt(limits[1].substring(3));

			for (TokenViewEntity tok : repositoryBean.getArabicTokenViewList()) {

				if ((startPage - 2 < tok.getHisLoc() ) && ( tok.getHisLoc() < endPage + 2 )) {
					String style = null;
					if (isArabicTokenViewToList(tok)) {
						style = "7";
					} else if (tok.getColor() == -1) {
						style = "9";
					} else if (tok.getPericopeId().equals(selectedPericopeId)){
						style = "arfocused";
					} else {
						style = Integer.toString(tok.getColor()%2);
					}
					tok.setStyle(style);
					lotve.add(tok);
				}
			}
		} else {
			System.err.println("Err in PericopeEditor.getArabicTokenViewList(): malformed id: " + selectedPericopeId);
		}

		return lotve;
	}

	/**
	 * @param arabicText the arabicText to set
	 */
	public void setArabicTokenViewList(String arabicText) {

		//System.err.println("Arabic: " + arabicText);
	}

	/**

	/**
	 * @return the greekText
	 */
	public List<TokenViewEntity> getGreekTokenViewList() {

		//forse migliora calcolando qua il colore
		/*
		 * styleClass="link_nodecor bk_#{pericopeEditorController.isGreekTokenViewToList(w)?'7'
		 * :w.color eq -1?9:w.pericopeId eq pericopeEditorController.selectedGreekPericopeId?'grfocused':w.color%4} greek">
		 */

		// styleClass="link_nodecor bk_#{pericopeEditorController.isGreekTokenViewToList(w)?'7':w.color eq -1?9:w.pericopeId eq pericopeEditorController.selectedGreekPericopeId?'grfocused':w.color%4} greek"


		List<TokenViewEntity> lotve = new ArrayList<TokenViewEntity>();
		String selectedPericopeId = getSelectedGreekPericopeId().substring( 0, 5);
		
		int pericopeLoc = Integer.parseInt(selectedPericopeId);

		for (TokenViewEntity tok : repositoryBean.getGreekTokenViewList()) {
			//	if ((pericopeLoc - 10 <= tok.getHisLoc() ) && ( tok.getHisLoc() <= pericopeLoc + 10 )) { //10 perche' l'unità nei paragrafi indicano il sotto paragrafo come 8^2
			if ((pericopeLoc == tok.getHisLoc() )) { //solo gli appartementi a quel paragrafo
				
				String style = null;
				if (isGreekTokenViewToList(tok)) {
					style = "7";
				} else if (tok.getColor() == -1) {
					style = "9";
				} else if (tok.getPericopeId().equals(getSelectedGreekPericopeId())){
					style = "grfocused";
				} else {
					style = Integer.toString(tok.getColor()%2 + 2);
				}
				tok.setStyle(style);
				lotve.add(tok);
			}
		}

		return lotve;
	}

	/**
	 * @param greekText the greekText to set
	 */
	public void setGreekTokenViewList(String greekText) {
		//System.err.println("Greek: " + greekText);
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

	public boolean isGreekTokenViewToList(TokenViewEntity tve){

		boolean ret = false;
		if (null != getSelectedGreekTokenView()) {
			ret = getSelectedGreekTokenView().containsKey(Integer.toString(tve.getFrom()));
		}
		return ret;

	}
	public boolean isArabicTokenViewToList(TokenViewEntity tve){

		boolean ret = false;
		if (null != getSelectedArabicTokenView()) {
			ret = getSelectedArabicTokenView().containsKey(Integer.toString(tve.getFrom()));
		}
		return ret;

	}
	public void setGreekTokenViewToList(TokenViewEntity tve){

		if (null == getSelectedGreekTokenView()) {
			setSelectedGreekTokenView(new HashMap<String, TokenViewEntity>());
		}
		if (!selectedGreekTokenView.containsKey(Integer.toString(tve.getFrom()))){
			selectedGreekTokenView.put(Integer.toString(tve.getFrom()), tve);
		} else {
			selectedGreekTokenView.remove(Integer.toString(tve.getFrom()));
		}
	}

	public void setArabicTokenViewToList(TokenViewEntity tve){

		if (null == getSelectedArabicTokenView()) {
			setSelectedArabicTokenView(new HashMap<String, TokenViewEntity>());
		}
		if (!selectedArabicTokenView.containsKey(Integer.toString(tve.getFrom()))){
			selectedArabicTokenView.put(Integer.toString(tve.getFrom()), tve);
		} else {
			selectedArabicTokenView.remove(Integer.toString(tve.getFrom()));
		}
	}

	/**
	 * @return the leftArabicBoudary
	 */
	public TokenViewEntity getLeftArabicBoudary() {
		return leftArabicBoudary;
	}

	/**
	 * @param leftArabicBoudary the leftArabicBoudary to set
	 */
	public void setLeftArabicBoudary(TokenViewEntity leftArabicBoudary) {
		this.leftArabicBoudary = leftArabicBoudary;
	}

	/**
	 * @return the rightArabicBoudary
	 */
	public TokenViewEntity getRightArabicBoudary() {
		return rightArabicBoudary;
	}

	/**
	 * @param rightArabicBoudary the rightArabicBoudary to set
	 */
	public void setRightArabicBoudary(TokenViewEntity rightArabicBoudary) {
		this.rightArabicBoudary = rightArabicBoudary;
	}

	/**
	 * @return the leftGreekBoudary
	 */
	public TokenViewEntity getLeftGreekBoudary() {
		return leftGreekBoudary;
	}

	/**
	 * @param leftGreekBoudary the leftGreekBoudary to set
	 */
	public void setLeftGreekBoudary(TokenViewEntity leftGreekBoudary) {
		this.leftGreekBoudary = leftGreekBoudary;
	}

	/**
	 * @return the rightGreekBoudary
	 */
	public TokenViewEntity getRightGreekBoudary() {
		return rightGreekBoudary;
	}

	/**
	 * @param rightGreekBoudary the rightGreekBoudary to set
	 */
	public void setRightGreekBoudary(TokenViewEntity rightGreekBoudary) {
		this.rightGreekBoudary = rightGreekBoudary;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSelectedGreekPericopeId() {

		return ((Link<RefPericopeText>)(parallelViewBean.getSelectedViewLink().getLink())).getValue().get(Consts.GREEK).getRef();
	}

	public String getSelectedArabicPericopeId() {
		return ((Link<RefPericopeText>)(parallelViewBean.getSelectedViewLink().getLink())).getValue().get(Consts.ARABIC).getRef();
	}


	private List<Integer> getSelectedTokenViewIndex (int langId) {

		List<Integer> clickedTokenViewId = null;
		HashMap<String,TokenViewEntity> tveHM = null;
		switch (langId) {
		case Consts.GREEK:
			tveHM = getSelectedGreekTokenView();
			break;
		case Consts.ARABIC:
			tveHM = getSelectedArabicTokenView();
			break;

		default:
			break;
		}
		if (null != tveHM) {
			clickedTokenViewId = new ArrayList<Integer>();
			for (TokenViewEntity token : tveHM.values()) {
				clickedTokenViewId.add(new Integer(token.getIndex()));
			}
		}
		return clickedTokenViewId;
	}
	
	private List<String> getSelectedTokenViewId (int langId) {

		List<String> clickedTokenViewId = null;
		HashMap<String,TokenViewEntity> tveHM = null;
		switch (langId) {
		case Consts.GREEK:
			tveHM = getSelectedGreekTokenView();
			break;
		case Consts.ARABIC:
			tveHM = getSelectedArabicTokenView();
			break;

		default:
			break;
		}
		if (null != tveHM) {
			clickedTokenViewId = new ArrayList<String>();
			for (TokenViewEntity token : tveHM.values()) {
				clickedTokenViewId.add(token.getId());
			}
		}
		return clickedTokenViewId;
	}
	
	
	private List<String> getSelectedPericopeId (String excludePericopeId, int langId) {

		List<String> clickedPericopeId = null;
		HashMap<String,TokenViewEntity> tveHM = null;
		switch (langId) {
		case Consts.GREEK:
			tveHM = getSelectedGreekTokenView();
			break;
		case Consts.ARABIC:
			tveHM = getSelectedArabicTokenView();
			break;

		default:
			break;
		}
		if (null != tveHM) {
			clickedPericopeId = new ArrayList<String>();
			for (TokenViewEntity token : tveHM.values()) {
				String id = token.getPericopeId();
				if (!id.equals(excludePericopeId)) {
					if (!clickedPericopeId.contains(token.getPericopeId())) {
						clickedPericopeId.add(token.getPericopeId());
					}
				}
			}
		}
		return clickedPericopeId;
	}

	/*	private List<TokenViewEntity> orderAndCheckTokenView (HashMap<String, TokenViewEntity> hm) {
		List<TokenViewEntity> list = null;
		List<TokenViewEntity> newList = null;
		if (null != hm) {
			list = new ArrayList<TokenViewEntity>(hm.values());
			Collections.sort(list,CophiSort.TOKENVIEW_FROM_ORDER);
			Iterator iterator = list.iterator(); 
			TokenViewEntity prec = null;
			TokenViewEntity tokenViewEntity = null;
			while (iterator.hasNext()){
				if ( null != prec){
					tokenViewEntity = (TokenViewEntity) iterator.next();
					if (tokenViewEntity.getPrec().equals(prec)) {
						newList.add(prec);
					}
				}
			}
		}
		return list;
	}*/

	private void clearSelectedGreekTokenView() {
		if (null != selectedGreekTokenView) {
			selectedGreekTokenView.clear();
			selectedGreekTokenView = null;
		}

	}
	public void clearSelectedArabicTokenView() {
		if (null != selectedArabicTokenView) { 
			selectedArabicTokenView.clear();
			selectedArabicTokenView = null;
		}
	}

	public void clearSelectedTokenView() {
		clearSelectedGreekTokenView();
		clearSelectedArabicTokenView();
	}


	/* Azioni dei bottoni */

	public void addGreekClick() {

		List<String> clickedTokenId = getSelectedTokenViewId(Consts.GREEK);
		List<String> clickedPericopeId = getSelectedPericopeId(getSelectedGreekPericopeId(), Consts.GREEK);
		List<Integer> clickedTokenIndex = getSelectedTokenViewIndex(Consts.GREEK);
		repositoryBean.addGreekClick(clickedTokenId, getSelectedGreekPericopeId(), clickedPericopeId);

		repositoryBean.updateAfterGreekPericopeModification(clickedTokenIndex, getSelectedGreekPericopeId()); 
		clearSelectedGreekTokenView();
	}

	public void removeGreekClick() {
		List<String> clickedTokenId = getSelectedTokenViewId(Consts.GREEK);
		List<String> clickedPericopeId = getSelectedPericopeId(null, Consts.GREEK);
		List<Integer> clickedTokenIndex = getSelectedTokenViewIndex(Consts.GREEK);

		repositoryBean.removeGreekClick(clickedTokenId, null, clickedPericopeId);
		repositoryBean.updateAfterGreekPericopeModification(clickedTokenIndex, getSelectedGreekPericopeId());
		clearSelectedGreekTokenView();
	}

	public void shiftGreekClick() {

		List<String> clickedTokenId = getSelectedTokenViewId(Consts.GREEK);
		List<String> clickedPericopeId = getSelectedPericopeId(getSelectedGreekPericopeId(), Consts.GREEK);
		List<Integer> clickedTokenIndex = getSelectedTokenViewIndex(Consts.GREEK);

		//se clicked non e' la focused => e' come la add to focused seguita da una remove from clicked
		//se clicked e' la focused => e' come la add to before/after seguita da una remove from focued/clicked
		repositoryBean.shiftGreekClick(clickedTokenId, getSelectedGreekPericopeId(), clickedPericopeId);
		repositoryBean.updateAfterGreekPericopeModification(clickedTokenIndex, getSelectedGreekPericopeId());
		clearSelectedGreekTokenView();
	}


	public void addArabicClick() {

		List<String> clickedTokenId = getSelectedTokenViewId(Consts.ARABIC);
		List<String> clickedPericopeId = getSelectedPericopeId(getSelectedArabicPericopeId(), Consts.ARABIC);
		List<Integer> clickedTokenIndex = getSelectedTokenViewIndex(Consts.ARABIC);

		repositoryBean.addArabicClick(clickedTokenId, getSelectedArabicPericopeId(), clickedPericopeId);
		repositoryBean.updateAfterArabicPericopeModification(clickedTokenIndex, getSelectedArabicPericopeId());
		clearSelectedArabicTokenView();
		Utils.addInfoMessageToContext("dblClick", "onDebug");

	}

	public void removeArabicClick() {
		List<String> clickedTokenId = getSelectedTokenViewId(Consts.ARABIC);
		List<String> clickedPericopeId = getSelectedPericopeId(null, Consts.ARABIC);
		List<Integer> clickedTokenIndex = getSelectedTokenViewIndex(Consts.ARABIC);

		repositoryBean.removeArabicClick(clickedTokenId, null, clickedPericopeId);
		repositoryBean.updateAfterArabicPericopeModification(clickedTokenIndex, getSelectedArabicPericopeId());
		clearSelectedArabicTokenView();
	}

	public void shiftArabicClick() {

	}


	public void dblClickOnToken() {
		System.err.println("dblClick");
		Utils.addInfoMessageToContext("dblClick", "onDebug");
	}
	public void clickOnToken(ActionEvent event) {
		System.err.println("click");
		Utils.addInfoMessageToContext("click", "onDebug");
	}


}
