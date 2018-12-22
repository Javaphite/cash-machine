package ua.training.cashmachine.model.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.training.cashmachine.exception.UncheckedSQLException;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionAware {

    default void commitAndClose(Connection connection) {
        try {
            if (!connection.isClosed()) {
                connection.commit();
                connection.close();
            }
        } catch (SQLException exception) {
            Logger log = LoggerFactory.getLogger(TransactionAware.class);
            log.error("Transaction commit problem occurred: ", exception);
            rollbackAndClose(connection);
            throw new UncheckedSQLException(exception);
        }
    }

    default void rollbackAndClose(Connection connection) {
        try {
            if (!connection.isClosed()) {
                connection.rollback();
                connection.close();
            }
        } catch (SQLException exception) {
            Logger log = LoggerFactory.getLogger(TransactionAware.class);
            log.error("Transaction rollback problem occurred: ", exception);
            throw new UncheckedSQLException(exception);
        }
    }
}
