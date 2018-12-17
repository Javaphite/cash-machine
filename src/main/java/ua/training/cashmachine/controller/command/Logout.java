package ua.training.cashmachine.controller.command;

import ua.training.cashmachine.model.entity.User;
import ua.training.cashmachine.model.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class Logout implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        UserService service = (UserService) session.getAttribute("userService");
        User user = (User) session.getAttribute("user");
        String login = user.getLogin();

        session.setAttribute("user", service.getUnknownUser());

        Collection<String> activeUsers = (Collection<String>) context.getAttribute("activeUsers");
        if(activeUsers.contains(login)) {
            activeUsers.remove(login);
            session.getServletContext().setAttribute("users", activeUsers);
        }

        HttpServletCommand.redirect("/", request, response);
    }
}
