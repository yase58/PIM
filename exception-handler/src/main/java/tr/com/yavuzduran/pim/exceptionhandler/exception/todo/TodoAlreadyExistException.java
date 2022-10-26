package tr.com.yavuzduran.pim.exceptionhandler.exception.todo;

public class TodoAlreadyExistException extends ToDoAppException{
    public TodoAlreadyExistException(String message) {
        super(message);
    }
}
