package tr.com.yavuzduran.pim.exceptionhandler.exception.authorization;

public class RoleNullException extends AuthorizationException{
    public RoleNullException(String message) {
        super(message);
    }
}
