package ua.training.cashmachine.model.db.dao;

import ua.training.cashmachine.model.entity.User;

import java.util.Optional;

public interface UserDao extends LocalizationDao<User> {

    Optional<User> find(String login, String hash);
}
