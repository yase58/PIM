package tr.com.yavuzduran.pim.exceptionhandler.exception.authorization;

import tr.com.yavuzduran.pim.exceptionhandler.exception.PIMException;

public class AuthorizationException extends PIMException {

    public AuthorizationException(String message) {
        super(message);
    }
}
