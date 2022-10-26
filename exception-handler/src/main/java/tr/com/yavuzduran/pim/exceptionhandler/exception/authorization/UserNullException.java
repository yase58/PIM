package tr.com.yavuzduran.pim.exceptionhandler.exception.authorization;

public class UserNullException extends AuthorizationException{
    public UserNullException(String message) {
        super(message);
    }
}
