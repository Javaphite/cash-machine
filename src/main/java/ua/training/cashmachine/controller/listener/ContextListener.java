package ua.training.cashmachine.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<String> activeUsers = new CopyOnWriteArrayList<>();
        sce.getServletContext().setAttribute("activeUsers", activeUsers);
    }
}
