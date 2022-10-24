package tr.com.yavuzduran.pim.exceptionhandler.exception;

public class EventParseException extends Exception {

    public EventParseException(String message) {
        super(message);
    }

    public EventParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
