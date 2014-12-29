package it.cnr.ilc.cophi.model.text;

import it.cnr.ilc.cophi.model.Pericope;
import it.cnr.ilc.cophi.model.RefPericope;

public class RefPericopeText extends RefPericope {

	private PericopeText pericope;

	/**
	 * @return the pericope
	 */
	@Override
	public Pericope getPericope() {
		return pericope;
	}

	/**
	 * @param pericope the pericope to set
	 */
	@Override
	public void setPericope(Pericope pericope) {
		this.pericope = (PericopeText) pericope;
	}
}
