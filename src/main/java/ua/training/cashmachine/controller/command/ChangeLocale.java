package ua.training.cashmachine.controller.command;

import ua.training.cashmachine.controller.DispatcherServlet;
import ua.training.cashmachine.controller.dto.Alert;
import ua.training.cashmachine.controller.util.RequestInfoUtil;
import ua.training.cashmachine.model.entity.User;
import ua.training.cashmachine.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class ChangeLocale implements HttpServletCommand {

    //ToDO: implement me
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService service = (UserService) session.getAttribute("userService");
        User user = (User) session.getAttribute("user");
        String localeTag = request.getParameter("localeTag");

        if(null != localeTag) {
            Locale locale = Locale.forLanguageTag(localeTag);
            session.setAttribute("locale", locale);
            session.setAttribute("user", service.updateUserLocale(user, locale));
        } else {
            Alert alert = new Alert("Error occurred ",
                    "during locale change - changes were discarded.", Alert.Type.DANGER);
            request.setAttribute("alert", alert);
        }
        DispatcherServlet.redirect(RequestInfoUtil.getActualPath(request), request, response);
    }
}
