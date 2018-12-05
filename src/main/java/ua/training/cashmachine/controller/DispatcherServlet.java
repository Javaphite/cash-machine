package ua.training.cashmachine.controller;

import ua.training.cashmachine.controller.command.HttpServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resolveCommand(req).execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resolveCommand(req).execute(req, resp);
    }

    private static HttpServletCommand resolveCommand(HttpServletRequest req) {
        return Activity.resolveCommand(req.getContextPath(), req.getParameter("command"));
    }
}
