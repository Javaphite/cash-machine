package ua.training.cashmachine.model.dao.mysql;

import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

import static ua.training.cashmachine.model.dao.mysql.MySqlConfiguration.SqlTemplate.GET_USER_BY_CREDENTIALS;

public class MySqlUserDao implements UserDao {

    private final Locale locale;

    MySqlUserDao(Locale locale) {
        this.locale = locale;
    }

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
    public Optional<User> find(String login, String hash) {
        try (Connection connection = MySqlConfiguration.getConnection();
             PreparedStatement statement =
                     MySqlConfiguration.getStatement(connection, GET_USER_BY_CREDENTIALS, locale)) {
            statement.setString(1, login);
            statement.setString(2, hash);
            ResultSet results = statement.executeQuery();

            User user = null;
            if (results.next()) {
                user = new User();
                user.setUserId(results.getInt(1));
                user.setLogin(results.getNString(2));
                user.setRole(Role.valueOf(results.getNString(3)));
                user.setFirstName(results.getNString(4));
                user.setLastName(results.getNString(5));
                user.setHash(User.DEFAULT_HASH);
            }
            return Optional.ofNullable(user);
        } catch (SQLException exception) {
            LOG.error("User info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }
}
