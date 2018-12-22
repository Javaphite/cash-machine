package ua.training.cashmachine.model.db.jdbc;

import ua.training.cashmachine.model.db.dao.TurnDao;
import ua.training.cashmachine.model.db.dao.UserDao;
import ua.training.cashmachine.model.db.DataSourceConfiguration;
import ua.training.cashmachine.model.db.mapper.GenericMapper;
import ua.training.cashmachine.model.entity.Turn;
import ua.training.cashmachine.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Optional;

import static ua.training.cashmachine.model.db.jdbc.QueryTemplate.*;

public class JdbcTurnDao extends AbstractJdbcDao<Turn> implements TurnDao {

    private UserDao userDao;

    JdbcTurnDao(DataSourceConfiguration configuration, GenericMapper<Turn> mapper, Locale locale, UserDao userDao) {
        super(configuration, mapper, locale);
        this.userDao = userDao;
        createQuery = TURN_CREATE::getQuery;
        deleteQuery = TURN_DELETE::getQuery;
        updateQuery = TURN_UPDATE::getQuery;
        findQuery = TURN_FIND_BY_ID::getQuery;
        findAllQuery = TURN_FIND_ALL::getQuery;
        idUpdater = Turn::setTurnId;
    }
/*
    @Override
    public PreparedStatement mapCreate(PreparedStatement statement, Turn entity) throws SQLException {
        statement.setInt(1, entity.getUser().getUserId());
        statement.setLong(2, entity.getIncome());
        statement.setTimestamp(3, Timestamp.valueOf(entity.getTimeOpened()));
        return statement;
    }

    @Override
    public PreparedStatement mapUpdate(PreparedStatement statement, Turn entity) throws SQLException {
        statement.setLong(1, entity.getIncome());
        statement.setTimestamp(2, Timestamp.valueOf(entity.getTimeClosed()));
        statement.setInt(3, entity.getUser().getUserId());
        return statement;
    }

    @Override
    public PreparedStatement mapDelete(PreparedStatement statement, Turn entity) throws SQLException {
        statement.setInt(1, entity.getTurnId());
        return statement;
    }

    @Override
    public Turn mapFind(ResultSet resultSet) throws SQLException {
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
    }*/
}
