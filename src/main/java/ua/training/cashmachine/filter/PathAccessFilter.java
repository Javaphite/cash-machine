package ua.training.cashmachine.filter;

import ua.training.cashmachine.model.Role;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class PathAccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String actualPath = request.getPathInfo();

        Role role = (Role) session.getAttribute("role");
        if(null == role) {
            role = Role.UNKNOWN_USER;
        }

        if (role.getPathsAllowed().contains(actualPath)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //ToDo: complement with adequate message and custom exception
            throw new RuntimeException("Access denied!" + actualPath + "___" + role.getPathsAllowed());
        }
    }
}
