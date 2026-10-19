package it.cnr.ilc.g2a.utils;

import javax.xml.XMLConstants;
import org.jdom2.input.SAXBuilder;

/** XML generated/consumed by the application must not load external resources. */
public final class XmlSecurity {
    private XmlSecurity() {
    }

    public static SAXBuilder newSaxBuilder() {
        SAXBuilder builder = new SAXBuilder();
        builder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        builder.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        builder.setFeature("http://xml.org/sax/features/external-general-entities", false);
        builder.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        builder.setExpandEntities(false);
        return builder;
    }
}
