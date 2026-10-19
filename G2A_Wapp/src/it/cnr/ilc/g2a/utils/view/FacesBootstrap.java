package it.cnr.ilc.g2a.utils.view;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;

/** Enables the JSF 2.3 integration with CDI on the Tomcat/Weld runtime. */
@ApplicationScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class FacesBootstrap {
}
