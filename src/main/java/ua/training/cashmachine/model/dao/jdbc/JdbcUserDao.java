package ua.training.cashmachine.model.dao.jdbc;

import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.dao.common.DataSourceConfiguration;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.entity.Role;
import ua.training.cashmachine.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static ua.training.cashmachine.model.dao.jdbc.QueryTemplate.*;

public class JdbcUserDao extends AbstractJdbcBilingualDao<User> implements UserDao {

    private final Supplier<String> findByCredentialsQuery;

    JdbcUserDao(DataSourceConfiguration configuration, Locale locale) {
        super(configuration);
        createQuery = () -> USER_CREATE.getQuery(locale);
        deleteQuery = () -> USER_DELETE.getQuery(locale);
        updateQuery = () -> USER_UPDATE.getQuery(locale);
        findQuery = ()-> USER_FIND_BY_ID.getQuery(locale);
        findAllQuery = () -> USER_FIND_ALL.getQuery(locale);
        findByCredentialsQuery = () -> USER_FIND_BY_CREDENTIALS.getQuery(locale);
        idUpdater = User::setUserId;
    }

    @Override
    public Optional<User> find(String login, String hash) {
        try (Connection connection = configuration.getConnection();
             PreparedStatement statement =
                     configuration.getStatement(connection, findByCredentialsQuery.get())) {
            statement.setString(1, login);
            statement.setString(2, hash);
            try (ResultSet results = statement.executeQuery()) {
                return Optional.ofNullable(mapFind(results));
            }
        } catch (SQLException exception) {
            LOG.error("User info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public PreparedStatement mapCreate(PreparedStatement statement, User entity,
                                       Map<String, String> localizedValues) throws SQLException {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getRole().name());
            statement.setString(3, entity.getHash());
            statement.setString(4, entity.getFirstName());
            statement.setString(5, localizedValues.get("firstName"));
            statement.setString(6, entity.getLastName());
            statement.setString(7, localizedValues.get("lastName"));
            return statement;
    }

    @Override
    public PreparedStatement mapDelete(PreparedStatement statement, User entity) throws SQLException {
        statement.setInt(1, entity.getUserId());
        return statement;
    }

    @Override
    public PreparedStatement mapUpdate(PreparedStatement statement, User entity,
                                       Map<String, String> localizedValues) throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getRole().name());
        statement.setString(3, entity.getFirstName());
        statement.setString(4, localizedValues.get("firstName"));
        statement.setString(5, entity.getLastName());
        statement.setString(6, localizedValues.get("lastName"));
        statement.setInt(7, entity.isExpired()? 1: 0);
        statement.setInt(8, entity.getUserId());
        return statement;
    }

    @Override
    public User mapFind(ResultSet resultSet) throws SQLException {
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
