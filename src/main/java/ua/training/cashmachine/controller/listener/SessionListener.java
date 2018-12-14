package ua.training.cashmachine.controller.listener;

import ua.training.cashmachine.model.service.UserService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute("userService", new UserService());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
