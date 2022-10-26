package tr.com.yavuzduran.pim.exceptionhandler.exception.authorization;

public class UserIdentifiersMissingException extends AuthorizationException {

    public UserIdentifiersMissingException(String message) {
        super(message);
    }

}
