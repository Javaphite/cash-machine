package ua.training.cashmachine.model.dao.mysql;

import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.dao.common.DataSourceConfiguration;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static ua.training.cashmachine.model.dao.mysql.MySqlConfiguration.SqlTemplate.*;

public class MySqlUserDao implements UserDao {

    private final DataSourceConfiguration configuration;
    private final Locale locale;

    MySqlUserDao(DataSourceConfiguration configuration, Locale locale) {
        this.configuration = configuration;
        this.locale = locale;
    }

    //TODO: test me (integration test successful)
    @Override
    public User create(User entity, Map<String, String> localizedValues) {
        Connection connection = configuration.getConnection();

        try (PreparedStatement statement = configuration.getStatement(connection, CREATE_USER.getQuery(locale))) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getRole().name());
            statement.setString(3, entity.getHash());
            statement.setString(4, entity.getFirstName());
            statement.setString(5, localizedValues.get("firstName"));
            statement.setString(6, entity.getLastName());
            statement.setString(7, localizedValues.get("lastName"));
            statement.executeUpdate();

            try (ResultSet results = statement.getGeneratedKeys()) {
                if (results.next()) {
                    entity.setUserId(results.getInt(1));
                    commitAndClose(connection);
                } else {
                    rollbackAndClose(connection);
                }
            }

        } catch (SQLException exception) {
            LOG.error("User creation failed: ", exception);
            rollbackAndClose(connection);
            throw new UncheckedSQLException(exception);
        }
        return entity;
    }

    @Override
    public boolean delete(User entity) {
        Connection connection = configuration.getConnection();

        try (PreparedStatement statement = configuration.getStatement(connection, DELETE_USER.getQuery(locale))) {
            statement.setInt(1, entity.getUserId());
            statement.executeUpdate();
            commitAndClose(connection);
            return true;
         } catch (SQLException exception) {
            LOG.error("User deactivation failed: ", exception);
            rollbackAndClose(connection);
            return false;
        }
    }

    // TODO: test me
    @Override
    public boolean update(User entity, Map<String, String> localizedValues) {
        Connection connection = configuration.getConnection();

        try (PreparedStatement statement = configuration.getStatement(connection, UPDATE_USER.getQuery(locale))) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getRole().name());
            statement.setString(3, entity.getFirstName());
            statement.setString(4, localizedValues.get("firstName"));
            statement.setString(5, entity.getLastName());
            statement.setString(6, localizedValues.get("lastName"));
            statement.setInt(7, entity.isExpired()? 1: 0);
            statement.setInt(8, entity.getUserId());
            statement.executeUpdate();

            commitAndClose(connection);
            return true;
        } catch (SQLException exception) {
            LOG.error("User update failed: ", exception);
            rollbackAndClose(connection);
            return false;
        }
    }

    @Override
    public Optional<User> find(int id) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement statement =
                     configuration.getStatement(connection, GET_USER_BY_ID.getQuery(locale))) {
            statement.setString(1, Integer.toString(id));
            try (ResultSet results = statement.executeQuery()) {
                return Optional.ofNullable(mapRow(results));
            }
        } catch (SQLException exception) {
            LOG.error("User info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    //TODO: test me (manual test successful)
    @Override
    public Optional<User> find(String login, String hash) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement statement =
                     configuration.getStatement(connection, GET_USER_BY_CREDENTIALS.getQuery(locale))) {
            statement.setString(1, login);
            statement.setString(2, hash);
            try (ResultSet results = statement.executeQuery()) {
                return Optional.ofNullable(mapRow(results));
            }
        } catch (SQLException exception) {
            LOG.error("User info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    //Todo: test me
    @Override
    public Collection<User> findAll() {
        try (Connection connection = configuration.getConnection();
             PreparedStatement statement =
                     configuration.getStatement(connection, GET_ALL_USERS.getQuery(locale))) {
            try (ResultSet results = statement.executeQuery()) {
                return mapAll(results);
            }
        } catch (SQLException exception) {
            LOG.error("User info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    //TODO: test me
    @Override
    public User mapRow(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setUserId(resultSet.getInt(1));
            user.setLogin(resultSet.getNString(2));
            user.setRole(Role.valueOf(resultSet.getNString(3)));
            user.setFirstName(resultSet.getNString(4));
            user.setLastName(resultSet.getNString(5));
            user.setExpired(resultSet.getInt(6)==1);
            user.setHash(User.DEFAULT_HASH);
        }
        return user;
    }
}
