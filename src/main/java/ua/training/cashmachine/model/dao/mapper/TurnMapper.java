package ua.training.cashmachine.model.dao.mapper;

import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.entity.Turn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TurnMapper implements GenericMapper<Turn> {

    private UserDao userDao;

    TurnMapper(UserDao userDao) {
        this.userDao = userDao;
    }

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
            turn = new Turn();
            turn.setTurnId(resultSet.getInt(1));
            turn.setUser(userDao.find(resultSet.getInt(2)).orElse(null));
            turn.setIncome(resultSet.getLong(3));
            turn.setTimeOpened(resultSet.getTimestamp(4).toLocalDateTime());
            turn.setTimeClosed(resultSet.getTimestamp(5).toLocalDateTime());
        }
        return turn;
    }
}
