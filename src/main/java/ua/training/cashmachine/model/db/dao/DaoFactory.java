package ua.training.cashmachine.model.db.dao;

import ua.training.cashmachine.model.db.mapper.UserMapper;

import java.util.Locale;

public interface DaoFactory {
    // ToDO: add all daos
    UserDao getUserDao(UserMapper mapper, Locale locale);

    //TurnDao getTurnDao(GenericMapper<Turn> mapper, Locale locale);
}
