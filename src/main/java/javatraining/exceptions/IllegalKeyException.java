package javatraining.exceptions;

/**
 * Created by shimi on 11/07/2016.
 */
public class IllegalKeyException extends Exception{
    public IllegalKeyException() {
        super();
    }

    public IllegalKeyException(String message) {
        super(message);
    }

    public IllegalKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalKeyException(Throwable cause) {
        super(cause);
    }

    public IllegalKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
