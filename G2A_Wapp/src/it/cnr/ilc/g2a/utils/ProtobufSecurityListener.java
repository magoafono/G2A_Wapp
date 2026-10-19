package it.cnr.ilc.g2a.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Reject unsafe pre-2022 Protobuf generated code rather than merely logging a
 * warning. Jena 4.10's unused binary RDF codec contains such generated classes;
 * the application's fixed RDF/XML ontology does not require them.
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
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Other G2A contexts in the same JVM may still be running.
    }
}
