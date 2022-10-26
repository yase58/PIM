package tr.com.yavuzduran.pim.exceptionhandler.exception.authorization;

public class UserNotFoundException extends AuthorizationException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
