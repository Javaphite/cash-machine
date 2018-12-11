package ua.training.cashmachine.model.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.exception.UserAuthorizationException;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class JdbcUserDao implements UserDao {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcUserDao.class);

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }

    @Override
    public User findByCredentials(String login, String hash) throws UserAuthorizationException {
        try (Connection connection = JdbcConnectionPoolHolder.getConnection();
             PreparedStatement statement = JdbcConnectionPoolHolder.getStatement(connection,
                     "SELECT * FROM users WHERE login=? AND hash=?")) {
            statement.setString(1, login);
            statement.setString(2, hash);
            ResultSet results = statement.executeQuery();

            User user = null;
            if (results.next()) {
                user = new User();
                user.setUserId(results.getInt(1));
                user.setLogin(results.getNString(6));
                user.setFirstName(results.getNString(2));
                user.setLastName(results.getNString(3));
                user.setRole(Role.valueOf(results.getNString(4)));
                user.setHash(results.getNString(5));
            }
            return user;
        } catch (SQLException exception) {
            LOG.error("User info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
