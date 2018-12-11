package ua.training.cashmachine.controller.util;

import ua.training.cashmachine.controller.Activity;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public final class RequestInfoUtil {

    private RequestInfoUtil() {}

    public static String getActualPath(HttpServletRequest request) {
        String actualPath = request.getPathInfo();
        if (null == actualPath) {
            actualPath = request.getServletPath();
        }
        return actualPath;
    }

    public static String getActualCommand(ServletRequest request) {
        String actualCommand = request.getParameter("command");
        if (null == actualCommand) {
            actualCommand = Activity.NO_COMMAND_MAPPING;
        }
        return actualCommand;
    }
}
