package entity;

/**
 * Created by Nataly on 21.10.2016.
 */
public class OutOfSelectionException extends Exception {
    public OutOfSelectionException() {
    }

    public OutOfSelectionException(String message) {
        super(message);
    }

    public OutOfSelectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfSelectionException(Throwable cause) {
        super(cause);
    }

    public OutOfSelectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "OutOfSelectionException{}";
    }
}

