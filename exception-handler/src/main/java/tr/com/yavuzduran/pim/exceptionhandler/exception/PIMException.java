package tr.com.yavuzduran.pim.exceptionhandler.exception;

public class PIMException extends Exception {

    public PIMException() {
    }

    public PIMException(String message) {
        super(message);
    }

    public PIMException(String message, Throwable cause) {
        super(message, cause);
    }

    public PIMException(Throwable cause) {
        super(cause);
    }

    public PIMException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
