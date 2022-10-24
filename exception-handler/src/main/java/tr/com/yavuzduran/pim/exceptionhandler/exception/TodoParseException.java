package tr.com.yavuzduran.pim.exceptionhandler.exception;

public class TodoParseException extends Exception {

    public TodoParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TodoParseException(Throwable cause) {
        super(cause);
    }
}
