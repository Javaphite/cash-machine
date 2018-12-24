package ua.training.cashmachine.model.db.jdbc;

import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.db.dao.UserDao;
import ua.training.cashmachine.model.db.mapper.UserMapper;
import ua.training.cashmachine.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

import static ua.training.cashmachine.model.db.jdbc.QueryTemplate.*;

public class JdbcUserDao extends AbstractJdbcDao<User> implements UserDao {

    private QueryTemplate findByCredentialsQuery;

    JdbcUserDao(Connection connection, UserMapper mapper, Locale locale) {
        super(connection, mapper, locale);
        createQuery = USER_CREATE;
        deleteQuery = USER_DELETE;
        updateQuery = USER_UPDATE;
        findQuery = USER_FIND_BY_ID;
        findAllQuery = USER_FIND_ALL;
        findByCredentialsQuery = USER_FIND_BY_CREDENTIALS;
        idUpdater = User::setUserId;
    }

    @Override
    public Optional<User> find(String login, String hash) {
        try (PreparedStatement statement = statementOf(findByCredentialsQuery.get(locale))) {
            statement.setString(1, login);
            statement.setString(2, hash);
            try (ResultSet results = statement.executeQuery()) {
                return Optional.ofNullable(mapper.mapFind(results));
            }
        } catch (SQLException exception) {
            LOG.error("User info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
