package it.cnr.ilc.g2a.action.userbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "sideMenuController")
@SessionScoped
public class SideMenuController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2523882070010733059L;

	private TreeNode root;
	private TreeNode selectedNode;  

	public SideMenuController() {
		root = new DefaultTreeNode("Root", null);

		TreeNode collections = new DefaultTreeNode("Collections", root);
		TreeNode manual = new DefaultTreeNode("document","User Manual", root);
		TreeNode tutorial = new DefaultTreeNode("document","Tutorial", root);
		TreeNode sourceCode = new DefaultTreeNode("document","Source Code", root);
		TreeNode copyPubs = new DefaultTreeNode("document","Copyright & Publications", root);

		TreeNode kitab  = new DefaultTreeNode("document","Kitāb Uṯūlūǧiyā wa-huwa qawl ʿalā l-rubūbiyya [Theologia Aristotelis]", collections);
		TreeNode risala = new DefaultTreeNode("document","Risāla fī l-ʿIlm al-ilāhī [Ps.-al-Fārābī, Epistle on the Divine Science]", collections);
		TreeNode detti  = new DefaultTreeNode("document","al-Šayḫ al-Yūnānī = [Sayings of the Greek Sage]", collections);
		
		collections.setExpanded(true);
	}

	public TreeNode getRoot() {
		return root;
	}

	/**
	 * @return the selectedNode
	 */
	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	/**
	 * @param selectedNode the selectedNode to set
	 */
	public void setSelectedNode(TreeNode selectedNode) {
		System.err.println("setSelectedNode: " + selectedNode.getData());

		this.selectedNode = selectedNode;
	}



	public void onNodeSelect(NodeSelectEvent event) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());  
		System.err.println("onNodeSelect: " + message.getSummary());
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}  
	
}

