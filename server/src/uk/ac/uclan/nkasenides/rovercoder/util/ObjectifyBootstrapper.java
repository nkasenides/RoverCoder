package uk.ac.uclan.nkasenides.rovercoder.util;

import com.googlecode.objectify.ObjectifyService;
import uk.ac.uclan.nkasenides.rovercoder.model.PlayerCodeEntry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ObjectifyBootstrapper implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ObjectifyService.init();
        ObjectifyService.register(PlayerCodeEntry.class);
        // etc...
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
