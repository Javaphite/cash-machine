package ua.training.cashmachine.model.dao.jdbc;

import ua.training.cashmachine.model.dao.TurnDao;
import ua.training.cashmachine.model.dao.mapper.GenericMapper;
import ua.training.cashmachine.model.entity.Turn;

import java.sql.Connection;
import java.util.Locale;

import static ua.training.cashmachine.model.dao.jdbc.QueryTemplate.*;

public class JdbcTurnDao extends AbstractJdbcDao<Turn> implements TurnDao {

    JdbcTurnDao(Connection connection, GenericMapper<Turn> mapper, Locale locale) {
        super(connection, mapper, locale);
        setupDaoConfiguration();
    }

    JdbcTurnDao(JdbcTransaction transaction, GenericMapper<Turn> mapper, Locale locale) {
        super(transaction, mapper, locale);
        setupDaoConfiguration();
    }

    private void setupDaoConfiguration() {
        createQuery = TURN_CREATE;
        deleteQuery = TURN_DELETE;
        updateQuery = TURN_UPDATE;
        findQuery = TURN_FIND_BY_ID;
        findAllQuery = TURN_FIND_ALL;
        idUpdater = Turn::setTurnId;
    }
}
