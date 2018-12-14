package ua.training.cashmachine.model.dao;

import ua.training.cashmachine.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    Optional<User> findByCredentials(String login, String hash);
}
