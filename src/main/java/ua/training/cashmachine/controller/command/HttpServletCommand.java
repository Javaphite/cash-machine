package ua.training.cashmachine.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface HttpServletCommand {

    void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    static void forward(String pageName, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/"+ pageName +".jsp").forward(request, response);
    }

    static void redirect(String path, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + '/' + path);
    }
}
