package it.cnr.ilc.g2a.model.text;

import it.cnr.ilc.g2a.model.Pericope;
import it.cnr.ilc.g2a.model.RefPericope;

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
