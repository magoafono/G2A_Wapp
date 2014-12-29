package it.cnr.ilc.cophi.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class MessageProvider {

	private static ResourceBundle bundle = null;


	private static ResourceBundle getBundle(String propertyFilename) {
		//if (bundle == null) {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			bundle = context.getApplication().getResourceBundle(context, propertyFilename);
		}
		//}
		return bundle;
	}

	public static  String getValue(String propertyFilename, String key) {

		String result = null;
		try {
			result = getBundle(propertyFilename).getString(key);
		} catch (MissingResourceException e) {
			result = "???" + key + "??? not found";
			e.printStackTrace();
		}
		return result;
	}

}
