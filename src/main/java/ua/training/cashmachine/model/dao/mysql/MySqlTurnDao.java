package ua.training.cashmachine.model.dao.mysql;

import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.dao.TurnDao;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.dao.common.DataSourceConfiguration;
import ua.training.cashmachine.model.entity.Turn;
import ua.training.cashmachine.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import static ua.training.cashmachine.model.dao.mysql.MySqlConfiguration.SqlTemplate.GET_ALL_TURNS;

public class MySqlTurnDao implements TurnDao {

    private DataSourceConfiguration configuration;
    private UserDao userDao;

    MySqlTurnDao(DataSourceConfiguration configuration, UserDao userDao) {
        this.configuration = configuration;
        this.userDao = userDao;
    }

    @Override
    public Turn create(Turn entity) {
        return null;
    }

    @Override
    public boolean update(Turn entity) {
        return false;
    }

    @Override
    public boolean delete(Turn entity) {
        return false;
    }

    @Override
    public Optional<Turn> find(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Turn> findAll() {
        try (Connection connection = configuration.getConnection();
             PreparedStatement statement =
                     configuration.getStatement(connection, GET_ALL_TURNS.getQuery())) {
            try (ResultSet results = statement.executeQuery()) {
                return mapAll(results);
            }
        } catch (SQLException exception) {
            LOG.error("Turn info reading failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public Turn mapRow(ResultSet resultSet) throws SQLException {
        Turn turn = null;
        if (resultSet.next()) {
            Optional<User> optionalUser = userDao.find(resultSet.getInt(2));
            turn = new Turn();
            turn.setTurnId(resultSet.getInt(1));
            turn.setUser(optionalUser.get());
            turn.setIncome(resultSet.getLong(3));
            turn.setTimeOpened(resultSet.getTimestamp(4).toLocalDateTime());
            turn.setTimeClosed(resultSet.getTimestamp(5).toLocalDateTime());
        }
        return turn;
    }
}
