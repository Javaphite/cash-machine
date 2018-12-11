package ua.training.cashmachine.exception;

public class BadMappingException extends RuntimeException {

    public BadMappingException() { }

    public BadMappingException(String message) {
        super(message);
    }

    public BadMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadMappingException(Throwable cause) {
        super(cause);
    }

    protected BadMappingException(String message, Throwable cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
