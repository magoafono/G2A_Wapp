package it.cnr.ilc.g2a.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFParserRegistry;

/**
 * Disable Jena's unused RDF/Protobuf input before decoding and reject unsafe
 * pre-2022 generated code at the publisher's runtime hook as defense in depth.
 * The application's fixed RDF/XML ontology does not require binary Protobuf.
 *
 * This is a JVM-wide policy for the dedicated Tomcat hosting the four G2A apps,
 * not a regeneration/patch of Jena's bytecode. Revalidate it when changing the
 * pinned Protobuf runtime. Never clear the policy when one context is stopped.
 */
public final class ProtobufSecurityListener implements ServletContextListener {

    public static final String REJECT_UNSAFE_GENCODE =
            "com.google.protobuf.error_on_unsafe_pre22_gencode";
    public static final String SILENCE_UNSAFE_GENCODE =
            "com.google.protobuf.use_unsafe_pre22_gencode";

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Runtime 3.25.9 checks presence, not Boolean.parseBoolean: even a value
        // of "false" for the silence property bypasses its rejection mechanism.
        if (System.getProperty(SILENCE_UNSAFE_GENCODE) != null) {
            throw new IllegalStateException("Remove JVM property "
                    + SILENCE_UNSAFE_GENCODE + ": unsafe Protobuf gencode is disabled");
        }
        // Do not catch SecurityException: inability to enforce this policy must
        // fail context startup, not silently restore the unsafe fallback.
        System.setProperty(REJECT_UNSAFE_GENCODE, "true");

        // Block RIOT dispatch before invoking the old generated parser: the
        // runtime hook alone is not a pre-parse resource/CPU bound. The registry
        // is local to this WAR's Jena classloader (or shared if Tomcat shares it).
        RDFParserRegistry.removeRegistration(Lang.RDFPROTO);
        if (RDFParserRegistry.getFactory(Lang.RDFPROTO) != null) {
            throw new IllegalStateException("RDF/Protobuf input must remain disabled");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Neither clear the JVM policy nor re-register the parser: other G2A
        // contexts in the same JVM/shared Jena classloader may still be running.
    }
}
