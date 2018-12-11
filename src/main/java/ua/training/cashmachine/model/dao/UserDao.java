package ua.training.cashmachine.model.dao;

import ua.training.cashmachine.exception.UserAuthorizationException;
import ua.training.cashmachine.model.entity.User;

public interface UserDao extends GenericDao<User> {

    User findByCredentials(String login, String hash) throws UserAuthorizationException;
}
