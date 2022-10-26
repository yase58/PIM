package tr.com.yavuzduran.pim.exceptionhandler.exception.todo;

public class TodoIdentifierMissingException extends ToDoAppException{
    public TodoIdentifierMissingException(String message) {
        super(message);
    }
}
