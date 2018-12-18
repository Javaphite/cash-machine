package ua.training.cashmachine.model.service;

import ua.training.cashmachine.model.annotation.ServiceScope;
import ua.training.cashmachine.model.dao.TurnDao;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.dao.jdbc.JdbcDaoFactory;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.Turn;
import ua.training.cashmachine.model.entity.User;

import java.time.LocalDateTime;
import java.util.Locale;

@ServiceScope(value = ServiceScope.USER, roles={Role.SENIOR_CASHIER, Role.MANAGER})
public class TurnService {

    public Turn openTurn(User user, Locale locale) {
        TurnDao turnDao = JdbcDaoFactory.getInstance().getTurnDao(locale);
        Turn turn = new Turn();
        turn.setUser(user);
        turn.setIncome(0);
        turn.setTimeOpened(LocalDateTime.now());

        return turnDao.create(turn);
    }
}
