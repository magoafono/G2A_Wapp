package it.cnr.ilc.g2a.action.userbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static final Logger log = LogManager.getLogger("SideMenuController");

	private TreeNode<String> root;
	private TreeNode<String> selectedNode;

	public SideMenuController() {
		root = new DefaultTreeNode<>("Root", null);

		TreeNode<String> collections = new DefaultTreeNode<>("Collections", root);
		TreeNode<String> manual = new DefaultTreeNode<>("document","User Manual", root);
		TreeNode<String> tutorial = new DefaultTreeNode<>("document","Tutorial", root);
		TreeNode<String> sourceCode = new DefaultTreeNode<>("document","Source Code", root);
		TreeNode<String> copyPubs = new DefaultTreeNode<>("document","Copyright & Publications", root);

		TreeNode<String> kitab  = new DefaultTreeNode<>("document","Kitāb Uṯūlūǧiyā wa-huwa qawl ʿalā l-rubūbiyya [Theologia Aristotelis]", collections);
		TreeNode<String> risala = new DefaultTreeNode<>("document","Risāla fī l-ʿIlm al-ilāhī [Ps.-al-Fārābī, Epistle on the Divine Science]", collections);
		TreeNode<String> detti  = new DefaultTreeNode<>("document","al-Šayḫ al-Yūnānī = [Sayings of the Greek Sage]", collections);
		
		collections.setExpanded(true);
	}

	public TreeNode<String> getRoot() {
		return root;
	}

	/**
	 * @return the selectedNode
	 */
	public TreeNode<String> getSelectedNode() {
		return selectedNode;
	}

	/**
	 * @param selectedNode the selectedNode to set
	 */
	public void setSelectedNode(TreeNode<String> selectedNode) {
		log.debug("setSelectedNode: " + selectedNode.getData());

		this.selectedNode = selectedNode;
	}



	public void onNodeSelect(NodeSelectEvent event) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());  
		log.debug("onNodeSelect: " + message.getSummary());
		FacesContext.getCurrentInstance().addMessage(null, message);  
	}  
	
}

