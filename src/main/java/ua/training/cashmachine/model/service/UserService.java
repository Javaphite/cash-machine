package ua.training.cashmachine.model.service;

import org.apache.commons.codec.digest.DigestUtils;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.dao.mysql.MySqlDaoFactory;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;

import java.util.Locale;
import java.util.Optional;

public class UserService {

    public Optional<User> getUserByCredentials(String login, String pass, Locale locale) {
        UserDao userDao = MySqlDaoFactory.getInstance().getUserDao(locale);
        return userDao.find(login, DigestUtils.sha256Hex(pass));
    }

    public User getUnknownUser() {
        User unknownUser = new User();
        unknownUser.setLogin("unknown");
        unknownUser.setRole(Role.UNKNOWN_USER);
        unknownUser.setFirstName("Unknown");
        unknownUser.setLastName("User");
        unknownUser.setHash(User.DEFAULT_HASH);
        return unknownUser;
    }
}
