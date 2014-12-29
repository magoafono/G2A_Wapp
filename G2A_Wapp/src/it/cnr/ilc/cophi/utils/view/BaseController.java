package it.cnr.ilc.cophi.utils.view;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.context.RequestContext;

public abstract class BaseController implements Serializable {
	private static final long serialVersionUID = -1L;

	/**
	 * actionListener for the autoTag service binding of the editor jsf
	 * interface.
	 * 
	 * @throws Exception
	 */
	public void asYouType() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		String description = map.get("description");
		RequestContext rcontext = RequestContext.getCurrentInstance();
		String taggedDescription = autoTag(description);
		rcontext.addCallbackParam("autotagResults", new String(Base64.encodeBase64(taggedDescription.getBytes())));
	}

	/**
	 * Controller specific implementation for the autoTag service of the editor
	 * jsf interface.
	 * 
	 * @param description
	 *            the xhtml string value to be autotagged.
	 * @return a sanitized string with proper tags set.
	 * @throws Exception
	 */
	protected String autoTag(String description) throws Exception {
		// should be overridden
		return description;
	}

}
