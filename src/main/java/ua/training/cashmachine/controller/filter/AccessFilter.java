package ua.training.cashmachine.controller.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.controller.command.HttpServletCommand;
import ua.training.cashmachine.controller.util.RequestInfoUtil;
import ua.training.cashmachine.model.entity.Role;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/","/main", "/journal", "/invoice", "/reports", "/supplies"})
public class AccessFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String actualPath = RequestInfoUtil.getActualPath(httpRequest);
        String actualCommand = RequestInfoUtil.getActualCommand(httpRequest);
        Role role = getActualUserRole(httpRequest);

        if (role.getPathsAllowed().contains(actualPath)
                && role.getCommandsAllowed().contains(actualCommand)) {
            chain.doFilter(request, response);
        } else {
            LOG.warn("Access denied for user role {}", role);
            //ToDo: complement with adequate message and custom exception
            throw new RuntimeException("Access denied!");
        }
    }

    private Role getActualUserRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Role role = (Role) session.getAttribute("role");
        if(null == role) {
            role = Role.UNKNOWN_USER;
        }
        return role;
    }
}
