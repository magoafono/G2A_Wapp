package it.cnr.ilc.cophi.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class MessageProvider {


	private ResourceBundle bundle = null;


	private  ResourceBundle getBundle(String propertyFilename) {
		//if (bundle == null) {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			bundle = context.getApplication().getResourceBundle(context, propertyFilename);
		}
		//}
		return bundle;
	}

	public  String getValue(String propertyFilename, String key) {

		String result = null;
		try {
			result = getBundle(propertyFilename).getString(key);
		} catch (MissingResourceException e) {
			result = "Key: (" + key + ") not found";
			e.printStackTrace();
		}
		return result;
	}

}
