package ua.training.cashmachine.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface HttpServletCommand {

    void execute(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException;
}
