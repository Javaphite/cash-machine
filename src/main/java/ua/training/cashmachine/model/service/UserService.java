package ua.training.cashmachine.model.service;

import org.apache.commons.codec.digest.DigestUtils;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.dao.jdbc.JdbcDaoFactory;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;
import ua.training.cashmachine.model.utils.LocalizationUtils;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class UserService {

    public Optional<User> getUserByCredentials(String login, String pass, Locale locale) {
        UserDao userDao = JdbcDaoFactory.getInstance().getUserDao(locale);
        return userDao.find(login, DigestUtils.sha256Hex(pass));
    }

    public User updateUserLocale(User user, Locale locale) {
        UserDao userDao = JdbcDaoFactory.getInstance().getUserDao(locale);
        return userDao.find(user.getUserId()).orElse(user);
    }

    public User createUser(User user, Locale locale, String... localizedValues) {
        UserDao userDao = JdbcDaoFactory.getInstance().getUserDao(locale);
        Map<String, String> localizationMap = LocalizationUtils.getLocalizationMap(user.getClass(), localizedValues);
        return userDao.create(user, localizationMap);
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
