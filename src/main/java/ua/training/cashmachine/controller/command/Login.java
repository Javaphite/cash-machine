package ua.training.cashmachine.controller.command;

import ua.training.cashmachine.controller.dto.Alert;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;
import ua.training.cashmachine.model.service.TurnService;
import ua.training.cashmachine.model.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

public class Login implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        UserService service = getUserService(request);
        Locale locale = (Locale) session.getAttribute("locale");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<User> optionalUser = service.getUserByCredentials(login, password, locale);
        Collection<String> activeUsers = (Collection<String>) context.getAttribute("activeUsers");

        if(optionalUser.isPresent() && !activeUsers.contains(login)) {
            User user = optionalUser.get();
            session.setAttribute("user", user);

            activeUsers.add(user.getLogin());
            session.getServletContext().setAttribute("users", activeUsers);
            HttpServletCommand.redirect("main", request, response);
        } else {
            String warningMessage = optionalUser.isPresent()?
                    "user already logged in.":
                    "no users found for given credentials.";
            Alert alert = new Alert("Authorization failed:", warningMessage, Alert.Type.DANGER);

            LOG.warn("Authorization failed: {} ({})", warningMessage, login);

            request.setAttribute("alert", alert);
            HttpServletCommand.forward("index", request, response);
        }
    }

    // TODO: looks weird, keep your eye on this method
    private UserService getUserService(HttpServletRequest request) {
        return (UserService) request.getSession().getAttribute("userService");
    }

    private void riseServicesForUser(HttpSession session, User user) {
        session.setAttribute("turnService", new TurnService());
    }
}
