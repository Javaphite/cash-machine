package ua.training.cashmachine.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveProduct implements HttpServletCommand {

    //ToDO: implement me
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("command", getClass().getCanonicalName());
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}
