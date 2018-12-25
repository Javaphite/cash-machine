package ua.training.cashmachine.model.dao;

import ua.training.cashmachine.model.dao.mapper.UserMapper;

import java.util.Locale;

public interface DaoFactory {

    Transaction getTransaction();

    // ToDO: add all daos
    UserDao getUserDao(UserMapper mapper, Locale locale);

    UserDao getUserDao(UserMapper mapper, Locale locale, Transaction transaction);

    //TurnDao getTurnDao(GenericMapper<Turn> mapper, Locale locale);
}
