package it.cnr.ilc.cophi.action.userbean;

import it.cnr.ilc.cophi.action.management.RepositoryBean;
import it.cnr.ilc.cophi.model.comment.Comment;
import it.cnr.ilc.cophi.model.text.RefPericopeText;
import it.cnr.ilc.cophi.model.view.LinkViewEntity;
import it.cnr.ilc.cophi.model.view.SelectedTextBoundaries;
import it.cnr.ilc.cophi.utils.Consts;
import it.cnr.ilc.cophi.utils.Utils;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;


@ManagedBean
@ViewScoped
public class CommentViewBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1736278370494347942L;

	private String greekText = null;
	private String arabicText = null;

	private String greekSelectedText = null;
	private String arabicSelectedText = null;

	private String greekSelectedPericopeId = null;
	private String arabicSelectedPericopeId = null;

	private String greektextarea = null;
	private String arabictextarea = null;

	private SelectedTextBoundaries greekSelectionBoundaries = null;
	private SelectedTextBoundaries arabicSelectionBoundaries = null;

	private String commentText = null;
	private String commentType = null;

	private Comment selectedComment = null;

	@ManagedProperty(value="#{repository}")
	private RepositoryBean repositoryBean;

	@ManagedProperty(value="#{sharedBean}")
	private SharedBean sharedBean;

	public  CommentViewBean() {

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
	 * @return the greekText
	 */
	public String getGreekText() {
		return greekText;
	}

	/**
	 * @param greekText the greekText to set
	 */
	public void setGreekText(String greekText) {
		this.greekText = greekText;
	}

	/**
	 * @return the arabicText
	 */
	public String getArabicText() {
		return arabicText;
	}

	/**
	 * @param arabicText the arabicText to set
	 */
	public void setArabicText(String arabicText) {
		this.arabicText = arabicText;
	}

	/**
	 * @return the greekSelectedText
	 */
	public String getGreekSelectedText() {
		return greekSelectedText;
	}

	/**
	 * @param greekSelectedText the greekSelectedText to set
	 */
	public void setGreekSelectedText(String greekSelectedText) {
		this.greekSelectedText = greekSelectedText;
	}

	/**
	 * @return the arabicSelectedText
	 */
	public String getArabicSelectedText() {
		return arabicSelectedText;
	}

	/**
	 * @param arabicSelectedText the arabicSelectedText to set
	 */
	public void setArabicSelectedText(String arabicSelectedText) {
		this.arabicSelectedText = arabicSelectedText;
	}

	/**
	 * @return the greektextarea
	 */
	public String getGreektextarea() {
		return greektextarea;
	}

	/**
	 * @param greektextarea the greektextarea to set
	 */
	public void setGreektextarea(String greektextarea) {
		this.greektextarea = greektextarea;
	}

	/**
	 * @return the arabictextarea
	 */
	public String getArabictextarea() {
		return arabictextarea;
	}

	/**
	 * @param arabictextarea the arabictextarea to set
	 */
	public void setArabictextarea(String arabictextarea) {
		this.arabictextarea = arabictextarea;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the commentType
	 */
	public String getCommentType() {
		return commentType;
	}




	/**
	 * @param commentType the commentType to set
	 */
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}




	/**
	 * @return the selectedViewLink
	 */
	public LinkViewEntity getSelectedLinkViewEntity() {
		return sharedBean.getSelectedLinkViewEntity();
	}

	/**
	 * @param selectedViewLink the selectedViewLink to set
	 */
	public void setSelectedViewLink(LinkViewEntity selectedViewLink) {
		this.sharedBean.setSelectedLinkViewEntity(selectedViewLink);
		if (null != selectedViewLink) {
			setGreekSelectedPericopeId(((RefPericopeText)selectedViewLink.getLink().getValue().get(Consts.GREEK)).getRef());
			setArabicSelectedPericopeId(((RefPericopeText)selectedViewLink.getLink().getValue().get(Consts.ARABIC)).getRef());
			setSelectedComment(null);

		} else {
			System.err.println("setSelectedViewLink(): selectedViewLink is null!!!");
		}
	}


	/**
	 * @return the selectedComment
	 */
	public Comment getSelectedComment() {
		return selectedComment;
	}

	/**
	 * @param selectedComment the selectedComment to set
	 */
	public void setSelectedComment(Comment selectedComment) {
		this.selectedComment = selectedComment;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Comment> getLinkComments(){
		List<Comment> loc = null;
		LinkViewEntity lve = getSelectedLinkViewEntity();
		if (null != lve) {
			loc = repositoryBean.getCommentsByLinkId(lve.getLink().getId()); 
		} else {
			System.err.println("Warn: getLinkComments() no link selected");
		}
		return loc;
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
	 * @return the greekSelectionBoundaries
	 */
	public SelectedTextBoundaries getGreekSelectionBoundaries() {
		return greekSelectionBoundaries;
	}

	/**
	 * @param greekSelectionBoundaries the greekSelectionBoundaries to set
	 */
	public void setGreekSelectionBoundaries(
			SelectedTextBoundaries greekSelectionBoundaries) {
		this.greekSelectionBoundaries = greekSelectionBoundaries;
	}

	/**
	 * @return the arabicSelectionBoundaries
	 */
	public SelectedTextBoundaries getArabicSelectionBoundaries() {
		return arabicSelectionBoundaries;
	}

	/**
	 * @param arabicSelectionBoundaries the arabicSelectionBoundaries to set
	 */
	public void setArabicSelectionBoundaries(SelectedTextBoundaries arabicSelectionBoundaries) {
		this.arabicSelectionBoundaries = arabicSelectionBoundaries;
	}

	/**
	 * @return the greekSelectedPericopeId
	 */
	public String getGreekSelectedPericopeId() {
		return greekSelectedPericopeId;
	}

	/**
	 * @param greekSelectedPericopeId the greekSelectedPericopeId to set
	 */
	public void setGreekSelectedPericopeId(String greekSelectedPericopeId) {
		this.greekSelectedPericopeId = greekSelectedPericopeId;
	}

	/**
	 * @return the arabicSelectedPericopeId
	 */
	public String getArabicSelectedPericopeId() {
		return arabicSelectedPericopeId;
	}

	/**
	 * @param arabicSelectedPericopeId the arabicSelectedPericopeId to set
	 */
	public void setArabicSelectedPericopeId(String arabicSelectedPericopeId) {
		this.arabicSelectedPericopeId = arabicSelectedPericopeId;
	}


	private SelectedTextBoundaries click (String text, String lang) {

		SelectedTextBoundaries selectedBound = new SelectedTextBoundaries();

		Pattern p = Pattern.compile("(.+?)\\s+\\[(\\d+)\\$(\\d+)\\)");
		Matcher m = p.matcher(text);
		if (m.find()) {
			if ("greek".equals(lang)){
				setGreekSelectedText(m.group(1));
			} else {
				setArabicSelectedText(m.group(1));
			}
			selectedBound.setStart(new Integer (m.group(2)));
			selectedBound.setEnd(new Integer (m.group(3)));

		} else {
			System.err.println("SelectedTextBoundaries click() error!!!");		
		}

		return selectedBound;
	}

	public void greekClick(){

		//setGreekSelectionBoundaries(click(getGreekText()));

		getSelectedComment().setGreekSelectedBound(click(getGreekText(), "greek"));

	}

	public void arabicClick(){

		//setArabicSelectionBoundaries(click(getArabicText()));

		getSelectedComment().setArabicSelectedBound(click(getArabicText(), "arabic"));

	}

	/**
	 * Calcola il nuovo ID: Prende l'ultimo id e lo incrementa di uno
	 * @param linkId
	 * @return
	 */
	private String newCommentId (String linkId){
		String newId = null;
		List<Comment> loc = repositoryBean.getOrderedCommentsByLinkId(linkId);
		if (null != loc) {
			Comment c = loc.get(loc.size() - 1);
			//comment_542.0_19.0304_3
			String lastId = c.getCommentId();
			newId = (new Integer(Integer.parseInt(lastId.substring(lastId.lastIndexOf("_") + 1)) + 1)).toString();
		} else {
			newId = (new Integer(0)).toString();
		}
		return newId;
	}

	public String newComment () {

		Comment newComment = new Comment();
		LinkViewEntity lve = getSelectedLinkViewEntity();
		String linkId = lve.getLink().getId();
		newComment.setLinkId(linkId);
		//newComment.setCommentId(linkId.replace("link", "comment") + "_" + newCommentId(linkId));
		newComment.setCommentId(null);
		newComment.setVersionId("0");
		newComment.setEditor("");
		newComment.setData(Utils.getDate());
		setCommentText(null);
		setArabicSelectedText(null);
		setGreekSelectedText(null);

		setSelectedComment(newComment);

		return "";
	}

	public String deleteComment() {

		Comment comment = getSelectedComment();
		boolean success = repositoryBean.deleteComment(comment);
		if(success) {
			Utils.addInfoMessageToContext("Delete comment done", "onSubmitMessage");
			resetForm();
			repositoryBean.reloadAllComments(getSelectedLinkViewEntity().getLink().getId());
		} else {
			Utils.addFatalMessageToContext("Problems on delete comment!!", "onSubmitMessage");
		}
		return "";
	}

	public void clearCommentForm() {

		setCommentText(null);
	}

	public void submit() {

		Comment commentToSubmit = getSelectedComment();
		commentToSubmit.setCommentText(getCommentText());
		commentToSubmit.setType(getCommentType());
		if (null == commentToSubmit.getArabicSelectedBound()) {
			commentToSubmit.setArabicSelectedBound(new SelectedTextBoundaries("0", "0"));
		}
		if (null == commentToSubmit.getGreekSelectedBound()) {
			commentToSubmit.setGreekSelectedBound(new SelectedTextBoundaries("0", "0"));
		}

		if (null == commentToSubmit.getCommentId() ){
			LinkViewEntity lve = sharedBean.getSelectedLinkViewEntity();
			String linkId = lve.getLink().getId();
			commentToSubmit.setCommentId(linkId.replace("link", "comment") + "_" + newCommentId(linkId));
		}

		boolean success = repositoryBean.saveComment(commentToSubmit);

		if(success) {
			Utils.addInfoMessageToContext("Save comment done", "onSubmitMessage");
			resetForm();
			repositoryBean.reloadAllComments(sharedBean.getSelectedLinkViewEntity().getLink().getId());
		} else {
			Utils.addFatalMessageToContext("Problems on save comment!!", "onSubmitMessage");
		}
	}

	private void resetForm () {
		setCommentText(null);
		setCommentType(null);
		setSelectedComment(null);
		setArabicSelectedText(null);
		setGreekSelectedText(null);
	}

	public String loadComment () {

		if (null != selectedComment.getArabicSelectedBound()) {
			try {
				setArabicSelectedText(Utils.substring(selectedComment.getArabicSelectedBound(), sharedBean.getSelectedLinkViewEntity().getArabicPericopeText().replaceAll("\n"," ↳ " )));
			} catch (java.lang.StringIndexOutOfBoundsException e) {

				System.err.println(e.toString() + " Error in "+ selectedComment.getCommentId() +", arabic substring, boundaries are outside " + selectedComment.getGreekSelectedBound().getStart() + ":" + selectedComment.getGreekSelectedBound().getEnd() + ", over " + sharedBean.getSelectedLinkViewEntity().getArabicPericopeText().length());

			}
		}
		if (null != selectedComment.getGreekSelectedBound()) {
			try {
				setGreekSelectedText(Utils.substring(selectedComment.getGreekSelectedBound(), sharedBean.getSelectedLinkViewEntity().getGreekPericopeText().replaceAll("\n", " ↲ ")));
			} catch (java.lang.StringIndexOutOfBoundsException e) {
				System.err.println(e.toString() + " Error in "+ selectedComment.getCommentId() +", greek substring, boundaries are outside " + selectedComment.getGreekSelectedBound().getStart() + ":" + selectedComment.getGreekSelectedBound().getEnd() + ", over " + sharedBean.getSelectedLinkViewEntity().getGreekPericopeText().length());
			}
		}

		setCommentType(selectedComment.getType());
		setCommentText(selectedComment.getCommentText());

		return "";
	}

	public void checkIfPericopeSelected() {

		if (null == getSelectedLinkViewEntity()) {
			Utils.addFatalMessageToContext("Please, select a pericope pair before entring in comment editor", "selectBeforeMessage");

		}
	}

	public String getGreekPericopeText(String pericopeId) {

		return  repositoryBean.getPericopeTextById(pericopeId, Consts.GREEK);
	}

	public String getArabicPericopeText(String pericopeId) {

		return  repositoryBean.getPericopeTextById(pericopeId, Consts.ARABIC);
	}

	public void handleRowSelection (SelectEvent event) {

		Object item = event.getObject();
		setSelectedViewLink((LinkViewEntity) item);
		resetForm();
	}	
}
