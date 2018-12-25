package ua.training.cashmachine.model.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.exception.UncheckedSQLException;
import ua.training.cashmachine.model.dao.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTransaction implements Transaction {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcTransaction.class);

    private final Connection connection;

    JdbcTransaction(Connection connection) {
        try {
            connection.setAutoCommit(false);
            this.connection = connection;
        } catch (SQLException exception) {
            LOG.error("Autocommit mode cannot be disabled: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }

    public Connection getConnection() {
        return connection;
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
                connection.rollback();
                connection.close();
            }
        } catch (SQLException exception) {
            LOG.error("Transaction connection closing failed: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
