package tr.com.yavuzduran.pim.exceptionhandler.exception.authorization;

public class RoleNotFoundException extends AuthorizationException{
    public RoleNotFoundException(String message) {
        super(message);
    }
}
