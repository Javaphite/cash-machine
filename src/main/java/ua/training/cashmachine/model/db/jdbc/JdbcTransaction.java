package ua.training.cashmachine.model.db.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.db.dao.GenericDao;
import ua.training.cashmachine.model.db.dao.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class JdbcTransaction implements Transaction, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcTransaction.class);

    private final Connection connection;
    private List<AbstractJdbcDao<?>> appendedDaos = new LinkedList<>();

    public JdbcTransaction(Connection connection) {
        this.connection = connection;
    }

    public List<AbstractJdbcDao<?>> getAppendedDaos() {
        return Collections.unmodifiableList(appendedDaos);
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void append(GenericDao<?> dao) {
        AbstractJdbcDao<?> jdbcDao = (AbstractJdbcDao<?>) dao;
        jdbcDao.setConnection(connection);
        jdbcDao.setTransaction(this);
        appendedDaos.add(jdbcDao);
    }

    @Override
    public void commitTransaction() {
        try {
            connection.commit();
        } catch (SQLException exception) {
            LOG.error("Transaction commit failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            LOG.error("Transaction rollback failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    @Override
    public void close() {
        try {
            if(!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException exception) {
            LOG.error("Transaction commit failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
