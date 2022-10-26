package tr.com.yavuzduran.pim.exceptionhandler.exception.blogpersonal;

public class BlogPersonalAlreadyExistException extends BlogPersonalException{
    public BlogPersonalAlreadyExistException(String message) {
        super(message);
    }
}
