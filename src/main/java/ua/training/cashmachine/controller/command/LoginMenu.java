package ua.training.cashmachine.controller.command;

import ua.training.cashmachine.controller.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginMenu implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DispatcherServlet.forward("index", request, response);
    }
}
