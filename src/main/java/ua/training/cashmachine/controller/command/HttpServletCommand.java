package ua.training.cashmachine.controller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface HttpServletCommand {
    Logger LOG = LoggerFactory.getLogger(HttpServletCommand.class);

    void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    static void forward(String pageName, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/"+ pageName +".jsp").forward(request, response);
    }

    static void redirect(String path, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String transformedPath = ('/'==path.charAt(0))? path: ('/' + path);
        response.sendRedirect(request.getContextPath() + transformedPath);
    }
}
