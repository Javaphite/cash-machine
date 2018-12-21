package ua.training.cashmachine.controller.command;

import ua.training.cashmachine.controller.DispatcherServlet;
import ua.training.cashmachine.controller.dto.Alert;
import ua.training.cashmachine.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainMenu implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Alert alert = new Alert("Welcome, ", getWelcomeMessage(user), Alert.Type.INFO);
        request.setAttribute("alert", alert);

        DispatcherServlet.forward("main", request, response);
    }

    private String getWelcomeMessage(User user) {
        return new StringBuilder(user.getRole().name().toLowerCase())
                .append(' ')
                .append(user.getFirstName())
                .append(' ')
                .append(user.getLastName())
                .append('!')
                .toString();
    }
}
