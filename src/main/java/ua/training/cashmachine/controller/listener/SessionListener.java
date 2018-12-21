package ua.training.cashmachine.controller.listener;

import ua.training.cashmachine.model.entity.User;
import ua.training.cashmachine.model.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collection;
import java.util.Locale;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        UserService service = new UserService();
        HttpSession session = se.getSession();
        session.setAttribute("user", service.getUnknownUser());
        session.setAttribute("userService", service);
        session.setAttribute("locale", Locale.forLanguageTag("uk-UA"));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        User user = (User) session.getAttribute("user");
        String login = user.getLogin();
        Collection<String> activeUsers = (Collection<String>) context.getAttribute("activeUsers");

        if(activeUsers.contains(login)) {
            activeUsers.remove(login);
            session.getServletContext().setAttribute("users", activeUsers);
        }
    }
}
