package tr.com.yavuzduran.pim.exceptionhandler.exception.todo;

public class TodoNotFoundException extends ToDoAppException{
    public TodoNotFoundException(String message) {
        super(message);
    }
}
