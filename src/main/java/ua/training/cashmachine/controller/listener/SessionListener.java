package ua.training.cashmachine.controller.listener;

import com.mysql.cj.Session;
import ua.training.cashmachine.model.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collection;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute("userService", new UserService());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        String login = (String) session.getAttribute("login");
        Collection<String> activeUsers = (Collection<String>) context.getAttribute("activeUsers");

        if(activeUsers.contains(login)) {
            activeUsers.remove(login);
            session.getServletContext().setAttribute("users", activeUsers);
        }
    }
}
