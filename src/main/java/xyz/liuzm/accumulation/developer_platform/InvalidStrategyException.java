package xyz.liuzm.accumulation.developer_platform;

public class InvalidStrategyException extends RuntimeException {
    public InvalidStrategyException() {
        super();
    }

    public InvalidStrategyException(String message) {
        super(message);
    }

    public InvalidStrategyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStrategyException(Throwable cause) {
        super(cause);
    }

    protected InvalidStrategyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
