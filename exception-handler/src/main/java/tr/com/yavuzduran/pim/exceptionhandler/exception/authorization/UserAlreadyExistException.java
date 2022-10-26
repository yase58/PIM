package tr.com.yavuzduran.pim.exceptionhandler.exception.authorization;

public class UserAlreadyExistException extends AuthorizationException {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
