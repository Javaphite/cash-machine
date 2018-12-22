package ua.training.cashmachine.model.db.jdbc;

import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.db.DataSourceConfiguration;
import ua.training.cashmachine.model.db.dao.UserDao;
import ua.training.cashmachine.model.db.mapper.UserMapper;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static ua.training.cashmachine.model.db.jdbc.QueryTemplate.*;

public class JdbcUserDao extends AbstractJdbcDao<User> implements UserDao {

    private final Function<Locale, String> findByCredentialsQuery;

    JdbcUserDao(DataSourceConfiguration configuration, UserMapper mapper, Locale locale) {
        super(configuration, mapper, locale);
        createQuery = USER_CREATE::getQuery;
        deleteQuery = USER_DELETE::getQuery;
        updateQuery = USER_UPDATE::getQuery;
        findQuery = USER_FIND_BY_ID::getQuery;
        findAllQuery = USER_FIND_ALL::getQuery;
        findByCredentialsQuery = USER_FIND_BY_CREDENTIALS::getQuery;
        idUpdater = User::setUserId;
    }

    @Override
    public Optional<User> find(String login, String hash) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement statement =
                     configuration.getStatement(connection, findByCredentialsQuery.apply(locale))) {
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
