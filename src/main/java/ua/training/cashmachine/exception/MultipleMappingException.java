package ua.training.cashmachine.exception;

public class MultipleMappingException extends RuntimeException {

    public MultipleMappingException() {
    }

    public MultipleMappingException(String message) {
        super(message);
    }

    public MultipleMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleMappingException(Throwable cause) {
        super(cause);
    }

    protected MultipleMappingException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
