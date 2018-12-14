package ua.training.cashmachine.model.service;

import org.apache.commons.codec.digest.DigestUtils;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.dao.jdbc.JdbcDaoFactory;
import ua.training.cashmachine.model.entity.User;

import java.util.Optional;

public class UserService {

    public Optional<User> getUserByCredentials(String login, String pass) {
        UserDao userDao = JdbcDaoFactory.getInstance().getUserDao();
        return userDao.findByCredentials(login, DigestUtils.sha512Hex(pass));
    }
}
