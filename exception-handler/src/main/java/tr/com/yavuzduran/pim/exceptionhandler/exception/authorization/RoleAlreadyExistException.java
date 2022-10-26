package tr.com.yavuzduran.pim.exceptionhandler.exception.authorization;

public class RoleAlreadyExistException extends AuthorizationException{

    public RoleAlreadyExistException(String message) {
        super(message);
    }
}
