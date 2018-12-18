package ua.training.cashmachine.controller.command;

import ua.training.cashmachine.model.entity.Turn;
import ua.training.cashmachine.model.entity.User;
import ua.training.cashmachine.model.service.TurnService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class OpenTurn implements HttpServletCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        TurnService turnService = (TurnService) session.getAttribute("turnService");
        User user = (User) session.getAttribute("user");
        Turn turn = (Turn) context.getAttribute("turn");
        Locale locale = (Locale) session.getAttribute("locale");

        if(null != turnService && null != user && null != turn) {
            turnService.openTurn(user, locale);
        }
        //TODO: add allert about successful opening or warning if one already exists
    }
}
