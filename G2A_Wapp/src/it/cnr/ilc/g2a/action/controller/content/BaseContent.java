package it.cnr.ilc.g2a.action.controller.content;

import it.cnr.ilc.g2a.model.xmlmapping.SequenceDocument;

public abstract class BaseContent {

	private SequenceDocument sequenceDocument = null;

	public BaseContent(SequenceDocument sd) {

		this.setSequenceDocument(sd);
	}

	public BaseContent() {

	}

	/**
	 * @return the sequenceDocument
	 */
	public SequenceDocument getSequenceDocument() {
		return sequenceDocument;
	}

	/**
	 * @param sequenceDocument the sequenceDocument to set
	 */
	public void setSequenceDocument(SequenceDocument sequenceDocument) {
		this.sequenceDocument = sequenceDocument;
	}

}
