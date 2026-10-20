package it.cnr.ilc.g2a.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageProvider {
    private static final Logger log = LogManager.getLogger(MessageProvider.class);

    public String getValue(String propertyFilename, String key) {
        if (Consts.CONFIGNAME.equals(propertyFilename)) {
            // Includes DB credentials: no JSF bundle, no value logging and no
            // fallback to a classpath file when configuration is unavailable.
            return DatabaseConfiguration.current().get(key);
        }
        FacesContext context = FacesContext.getCurrentInstance();
        if (context == null) throw new IllegalStateException("Faces resource bundle unavailable");
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, propertyFilename);
        try { return bundle.getString(key); }
        catch (MissingResourceException missing) {
            log.error("Resource bundle key unavailable");
            return "Key: (" + key + ") not found";
        }
    }
}
