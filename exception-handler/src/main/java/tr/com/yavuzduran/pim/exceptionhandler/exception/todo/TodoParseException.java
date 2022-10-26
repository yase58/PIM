package tr.com.yavuzduran.pim.exceptionhandler.exception.todo;

import tr.com.yavuzduran.pim.exceptionhandler.exception.PIMException;

public class TodoParseException extends PIMException {

    public TodoParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TodoParseException(Throwable cause) {
        super(cause);
    }
}
