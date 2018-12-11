package ua.training.cashmachine.controller.command;

import ua.training.cashmachine.exception.UserAuthorizationException;
import ua.training.cashmachine.model.entity.User;
import ua.training.cashmachine.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            User user = new UserService().getUserByCredentials(login, password);
            request.getSession().setAttribute("role", user.getRole());
            HttpServletCommand.redirect("main", request, response);
        } catch (UserAuthorizationException exception) {
            //Todo: log me
            request.setAttribute("alertType", "danger");
            request.setAttribute("alertVisibility", "block");
            request.setAttribute("alertText",
                    "Wrong user credentials! Please, check your login and password and try again.");
            HttpServletCommand.forward("index", request, response);
        }
    }
}
