package tr.com.yavuzduran.pim.exceptionhandler.exception.contact;

import tr.com.yavuzduran.pim.exceptionhandler.exception.PIMException;

public class ContactException extends PIMException {

    public ContactException() {
    }

    public ContactException(String message) {
        super(message);
    }
}
