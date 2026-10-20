package it.cnr.ilc.g2a.utils;

import java.io.IOException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/** Refuse startup with missing/plaintext/malformed external credentials. */
public final class CredentialPolicyListener implements ServletContextListener {
    @Override public void contextInitialized(ServletContextEvent event) {
        try { CredentialStore.validateContext(event.getServletContext().getContextPath()); }
        catch (IOException invalid) {
            throw new IllegalStateException("A valid external hashed credential store is required", invalid);
        }
    }
    @Override public void contextDestroyed(ServletContextEvent event) { }
}
