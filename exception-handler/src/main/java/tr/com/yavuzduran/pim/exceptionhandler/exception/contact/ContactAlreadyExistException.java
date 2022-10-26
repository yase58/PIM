package tr.com.yavuzduran.pim.exceptionhandler.exception.contact;

public class ContactAlreadyExistException extends ContactException{

    public ContactAlreadyExistException(String message) {
        super(message);
    }
}
