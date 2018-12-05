package ua.training.cashmachine.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestCommand implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse responce) {
        // Just command template
    }
}
