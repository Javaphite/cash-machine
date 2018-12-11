package ua.training.cashmachine.controller;

import ua.training.cashmachine.controller.command.HttpServletCommand;
import ua.training.cashmachine.controller.util.RequestInfoUtil;
import ua.training.cashmachine.model.entity.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"","/main", "/journal", "/invoice", "/reports", "/supplies"})
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init() {
        getServletContext().setAttribute("role", Role.UNKNOWN_USER);
    }

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
        return Activity.commandOf(RequestInfoUtil.getActualPath(req), req.getParameter("command"));
    }
}
