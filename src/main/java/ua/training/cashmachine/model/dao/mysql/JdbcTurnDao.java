package ua.training.cashmachine.model.dao.mysql;

import ua.training.cashmachine.model.dao.TurnDao;
import ua.training.cashmachine.model.dao.UserDao;
import ua.training.cashmachine.model.dao.common.DataSourceConfiguration;
import ua.training.cashmachine.model.entity.Turn;
import ua.training.cashmachine.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcTurnDao extends AbstractJdbcSimpleDao<Turn> implements TurnDao {

    private UserDao userDao;

    JdbcTurnDao(DataSourceConfiguration configuration, UserDao userDao) {
        super(configuration);
        this.userDao = userDao;
        /*createQuery = () -> CREATE_USER.getQuery(locale);
        deleteQuery = () -> DELETE_USER.getQuery(locale);
        updateQuery = () -> UPDATE_USER.getQuery(locale);
        findQuery = `()-> GET_TURN_BY_ID.getQuery(locale);
        findAllQuery = () -> GET_ALL_USERS.getQuery(locale);
        findByCredentialsQuery = () -> GET_USER_BY_CREDENTIALS.getQuery(locale);*/
    }

    @Override
    public PreparedStatement mapCreate(PreparedStatement statement, Turn entity) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement mapUpdate(PreparedStatement statement, Turn entity) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement mapDelete(PreparedStatement statement, Turn entity) throws SQLException {
        return null;
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
    }
}
