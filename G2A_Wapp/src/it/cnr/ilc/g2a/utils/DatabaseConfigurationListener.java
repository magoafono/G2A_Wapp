package it.cnr.ilc.g2a.utils;

import java.io.IOException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/** Fail context startup when external DB settings do not match this WAR's profile. */
public final class DatabaseConfigurationListener implements ServletContextListener {
    @Override public void contextInitialized(ServletContextEvent event) {
        try { DatabaseConfiguration.initialize(event.getServletContext()); }
        catch (IOException invalid) { throw new IllegalStateException("Valid external database configuration required", invalid); }
    }
    @Override public void contextDestroyed(ServletContextEvent event) { }
}
