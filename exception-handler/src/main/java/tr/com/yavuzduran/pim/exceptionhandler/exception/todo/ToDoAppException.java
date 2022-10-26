package tr.com.yavuzduran.pim.exceptionhandler.exception.todo;

import tr.com.yavuzduran.pim.exceptionhandler.exception.PIMException;

public class ToDoAppException extends PIMException {

    public ToDoAppException(String message) {
        super(message);
    }

    public ToDoAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public ToDoAppException(Throwable cause) {
        super(cause);
    }
}
