package it.cnr.ilc.g2a.action.userbean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Etichetta breve della collezione corrente (una per contesto web).
 * Derivata dal context path, cosi' ogni WAR mostra il proprio nome nell'header.
 */
@ManagedBean(name = "collectionBean", eager = true)
@ApplicationScoped
public class CollectionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Etichetta breve per il context path del WAR in esecuzione. */
    static String labelFor(String contextPath) {
        if (null == contextPath) {
            return "";
        }
        switch (contextPath) {
        case "/Teologia_Wapp":
            return "Theologia Aristotelis";
        case "/Detti_Wapp":
            return "Sayings of the Greek Sage";
        case "/Risala_Wapp":
            return "Epistle on the Divine Science";
        case "/Metafisica_Wapp":
            return "Metaphysics";
        default:
            // Fallback: "Nome_Wapp" -> "Nome".
            return contextPath.replaceFirst("^/", "").replaceFirst("_Wapp$", "");
        }
    }

    public String getLabel() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (null == context) {
            return "";
        }
        return labelFor(context.getExternalContext().getRequestContextPath());
    }
}
