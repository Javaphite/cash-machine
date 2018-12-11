package ua.training.cashmachine.model.service;

import org.apache.commons.codec.digest.DigestUtils;
import ua.training.cashmachine.exception.UserAuthorizationException;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.dao.jdbc.JdbcDaoFactory;
import ua.training.cashmachine.model.entity.User;

public class UserService {

    public User getUserByCredentials(String login, String pass) throws UserAuthorizationException {
        UserDao userDao = JdbcDaoFactory.getInstance().getUserDao();
        User user = userDao.findByCredentials(login, DigestUtils.sha512Hex(pass));
        if (null == user) {
            //TODO: log me
            throw new UserAuthorizationException("No user with such credentials!");
        }
        return user;
    }
}
