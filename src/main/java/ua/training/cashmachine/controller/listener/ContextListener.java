package ua.training.cashmachine.controller.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashSet;
import java.util.Set;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Set<String> activeUsers = new HashSet<>();
        sce.getServletContext().setAttribute("activeUsers", activeUsers);
    }
}
