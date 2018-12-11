package ua.training.cashmachine.exception;

import java.sql.SQLException;

/**
 * Unchecked exception wrapper for SQLException.
 */
public class UncheckedSQLException extends RuntimeException {

    public UncheckedSQLException() { }

    public UncheckedSQLException(String message) {
        super(message);
    }

    public UncheckedSQLException(SQLException cause) {
        super(cause);
    }

    public UncheckedSQLException(String message, SQLException cause) {
        super(message, cause);
    }

    protected UncheckedSQLException(String message, SQLException cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
