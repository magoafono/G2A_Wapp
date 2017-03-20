package it.cnr.ilc.g2a.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageProvider {

    private static final Logger log = LogManager.getLogger(MessageProvider.class);

    private ResourceBundle bundle = null;

    private ResourceBundle getBundle(String propertyFilename) {
        log.debug("propertyFilename=(" + propertyFilename + ")");

        //if (bundle == null) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null) {
            bundle = context.getApplication().getResourceBundle(context, propertyFilename);
        }
        //}
        return bundle;
    }

    public String getValue(String propertyFilename, String key) {

        String result = null;
        try {
            result = getBundle(propertyFilename).getString(key);
        } catch (MissingResourceException e) {
            result = "Key: (" + key + ") not found";
            log.error(e);
        }
        log.debug("propertyFilename=(" + propertyFilename + "), key=(" + key + ") => " + result);

        return result;
    }

}
