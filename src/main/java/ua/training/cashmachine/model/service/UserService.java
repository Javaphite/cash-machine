package ua.training.cashmachine.model.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.model.annotation.ServiceScope;
import ua.training.cashmachine.model.db.dao.DaoFactory;
import ua.training.cashmachine.model.db.dao.Transaction;
import ua.training.cashmachine.model.db.dao.UserDao;
import ua.training.cashmachine.model.db.jdbc.JdbcDaoFactory;
import ua.training.cashmachine.model.db.mapper.UserMapper;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;
import ua.training.cashmachine.model.utils.LocalizationUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@ServiceScope(ServiceScope.SESSION)
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public Optional<User> getUserByCredentials(String login, String pass, Locale locale) {
        UserDao userDao = JdbcDaoFactory.getInstance().getUserDao(new UserMapper(), locale);
        return userDao.find(login, DigestUtils.sha256Hex(pass));
    }

    public User switchUserLocale(User user, Locale locale) {
        UserDao userDao = JdbcDaoFactory.getInstance().getUserDao(new UserMapper(), locale);
        return userDao.find(user.getUserId()).orElse(user);
    }

    public User createUser(User user, Locale currentLocale, Map<Locale, Map<String, String>> localizationTable) {
        DaoFactory daoFactory = JdbcDaoFactory.getInstance();

        try (Transaction transaction = daoFactory.getTransaction()) {
            UserDao userDao = daoFactory.getUserDao(new UserMapper(), currentLocale, transaction);

            User userWithId = userDao.create(user);
            userDao.updateLocalization(userWithId, localizationTable);
            userWithId = userDao.find(userWithId.getUserId()).orElse(getUnknownUser());

            transaction.commitTransaction();
            return userWithId;
        } catch (Exception exception) {
            LOG.error("Transaction error: ", exception);
            throw new RuntimeException(exception);
        }
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
