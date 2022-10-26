package tr.com.yavuzduran.pim.exceptionhandler.exception.contact;

public class ContactNotFoundException extends ContactException{

    public ContactNotFoundException(String message) {
        super(message);
    }
}
