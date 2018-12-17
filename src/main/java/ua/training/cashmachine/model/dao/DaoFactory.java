package ua.training.cashmachine.model.dao;

import java.util.Locale;

public interface DaoFactory {
    // ToDO: add all daos
    UserDao getUserDao(Locale locale);

    TurnDao getTurnDao(Locale locale);
}
