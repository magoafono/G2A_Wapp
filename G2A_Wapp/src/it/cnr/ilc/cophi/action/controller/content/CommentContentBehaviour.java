package it.cnr.ilc.cophi.action.controller.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.sax.SAXHandler;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import it.cnr.ilc.cophi.model.Reference;
import it.cnr.ilc.cophi.model.ReferenceSet;
import it.cnr.ilc.cophi.model.comment.Comment;
import it.cnr.ilc.cophi.model.view.SelectedTextBoundaries;
import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument;
import it.cnr.ilc.cophi.model.xmlmapping.SequenceDocument.Sequence;

public class CommentContentBehaviour extends BaseContent implements
ContentBehaviour<Comment> {

	private HashMap<String, Resource> resources = null; 

	public CommentContentBehaviour(SequenceDocument sd) {
		super(sd);
		// TODO Auto-generated constructor stub
	}

	public CommentContentBehaviour() {

	}

	/**
	 * @return the resources
	 */
	public HashMap<String, Resource> getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(HashMap<String, Resource> resources) {
		this.resources = resources;
	}

	@Override
	public HashMap<String, Comment> getValue() {

		HashMap<String, Comment> comments = new HashMap<String, Comment>();
		for(Resource resource: getResources().values()){
			//i++;
			XMLResource xr = (XMLResource) resource;
			SAXHandler saxhandler = new SAXHandler();
			try {
				xr.getContentAsSAX(saxhandler);
				Document document=saxhandler.getDocument();

				Element commentNode = document.getRootElement();

				Element refsNode = commentNode.getChild("refs");
				Element versNode = commentNode.getChild("vers");
				Element selsNode = commentNode.getChild("selections");
				Element textsNode = commentNode.getChild("texts");

				List<Element> refs2selection = refsNode.getChildren();
				List<Element> vers = versNode.getChildren();
				List<Element> sels = selsNode.getChildren();
				List<Element> texts = textsNode.getChildren();

				String idTarget = commentNode.getAttributeValue("id");
				String xmlFileName = idTarget.concat(".xml");
				idTarget = idTarget.substring(0,idTarget.indexOf('_'));//TODO NON PENSO VADA BENE PER I NUOVI COMMENTI!!!
				/*
				 * vecchi ID di commento: <comment id="539.0_0">
				 * nuovi ID di commento : <comment id="comment_429.0_121.0505_8">
				 */

				Comment comment = new Comment();

				comment.setXmlFileName(xmlFileName);
				
				int currVersion = vers.size();
				Element currentComment = vers.get(currVersion-1);

				String commentType = currentComment.getChild("comment-type").getAttributeValue("type"); 
				//currentComment.getChildTextNormalize("comment-type");
				comment.setType(commentType);

				String commentText = currentComment.getChild("text-ref").getAttributeValue("text-id");
				//currentComment.getChildTextNormalize("text-ref");
				commentText = texts.get(Integer.parseInt(commentText)).getChildText("rich");
				comment.setCommentText(commentText);

				int refs2selSize = refs2selection.size();
				ArrayList<Integer>[] bounds = new ArrayList[2];
				for (int i = 0; i < refs2selSize; i++) {

					Element selection1Node = sels.get(Integer.parseInt(refs2selection.get(i).getAttributeValue("select-name") ));
					List<Element> selects = selection1Node.getChildren();
					Iterator<Element> ie = selects.iterator();
					if (ie.hasNext()){
						Element e = ie.next();
						Integer start = new Integer(e.getChild("start").getAttributeValue("x")); 
						Integer end = new Integer(e.getChild("end").getAttributeValue("x"));
						bounds[i] = new ArrayList<Integer>(2);
						bounds[i].add(start);
						bounds[i].add(end);
					}
				}

				String linkId = "link_" + refs2selection.get(0).getAttributeValue("target") + "_"+refs2selection.get(1).getAttributeValue("target");
				comment.setLinkId(linkId);

				SelectedTextBoundaries arabicStb = new SelectedTextBoundaries();
				SelectedTextBoundaries greekStb = new SelectedTextBoundaries();
				greekStb.setStart((Integer)bounds[0].get(0));
				greekStb.setEnd((Integer)bounds[0].get(1));
				arabicStb.setStart((Integer)bounds[1].get(0));
				arabicStb.setEnd((Integer)bounds[1].get(1));

				comment.setGreekSelectedBound(greekStb);
				comment.setArabicSelectedBound(arabicStb);

				comment.setCommentId(commentNode.getAttributeValue("id"));

				comments.put(comment.getCommentId(),comment);
			} catch (XMLDBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}
		return comments;

	}

	public Document createJDOM (Comment comment) {

		String commentId = comment.getCommentId();

		Element xmlComment = new Element("comment");
		xmlComment.setAttribute(new Attribute("id", commentId));
		Document doc = new Document();
		doc.setRootElement(xmlComment);

		Element refsNode = new Element("refs");

		String[] type = {"greek selection", "arabic selection"};
		List<String> target = new ArrayList<String>();

		Pattern p = Pattern.compile("link_(.+?)_(.+)");
		Matcher m = p.matcher(comment.getLinkId());
		if (m.find()) {
			target.add(m.group(1));
			target.add(m.group(2));
		}
		for (int i = 0; i < type.length; i++) {
			Element rts = new Element("ref-to-selection");
			rts.setAttribute("target", target.get(i));
			rts.setAttribute("select-name", String.valueOf(i));
			rts.setAttribute("name", type[i]);
			refsNode.addContent(rts);
		}

		doc.getRootElement().addContent(refsNode);

		Element versNode = new Element("vers");
		Element verNode = new Element("ver");
		verNode.setAttribute("id", "0");
		Element authorNode = new Element("author");
		authorNode.setAttribute("author-id", "0");
		Element commentTypeNode = new Element("comment-type");
		commentTypeNode.setAttribute("type", comment.getType());
		Element textRefNode = new Element("text-ref");
		textRefNode.setAttribute("text-id", "0");
		Element validNode = new Element("valid");
		validNode.setAttribute("from", "0" /*comment.getData()*/);

		verNode.addContent(authorNode);
		verNode.addContent(commentTypeNode);
		verNode.addContent(textRefNode);
		verNode.addContent(validNode);

		versNode.addContent(verNode);

		doc.getRootElement().addContent(versNode);

		Element selectionsNode = new Element("selections");


		SelectedTextBoundaries greekBound = comment.getGreekSelectedBound();
		SelectedTextBoundaries arabicBound = comment.getArabicSelectedBound();

		Integer[] start =  {greekBound.getStart(), arabicBound.getStart()};
		Integer[] end =  {greekBound.getEnd(), arabicBound.getEnd()};

		for (int i = 0; i < 2; i++) {
			Element selectionNode = new Element("selection");
			selectionNode.setAttribute("type", "text");
			selectionNode.setAttribute("name", String.valueOf(i));
			Element selectNode = new Element("select");
			selectNode.setAttribute("sel", "0");
			Element startNode = new Element("start");
			startNode.setAttribute("x", String.valueOf(start[i]));
			Element endNode = new Element("end");
			endNode.setAttribute("x", String.valueOf(end[i]));

			selectNode.addContent(startNode);
			selectNode.addContent(endNode);

			selectionNode.addContent(selectNode);

			selectionsNode.addContent(selectionNode);
		}

		doc.getRootElement().addContent(selectionsNode);

		Element textsNode = new Element("texts");
		Element textNode = new Element("text");
		textNode.setAttribute("id", "0");
		Element richNode = new Element("rich");
		richNode.addContent(comment.getCommentText());
		Element plainNode = new Element("plain");
		plainNode.addContent(comment.getCommentText().replaceAll("\\<.*?\\>", ""));

		textNode.addContent(plainNode);
		textNode.addContent(richNode);
		textsNode.addContent(textNode);

		doc.getRootElement().addContent(textsNode);

		return doc;

	}

	@Override
	public SequenceDocument createSequenceDocument(List<ReferenceSet<Reference>> t,
			String work) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sequence createSequence(ReferenceSet<Reference> refs) {
		// TODO Auto-generated method stub
		return null;
	}



}
