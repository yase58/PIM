package tr.com.yavuzduran.pim.exceptionhandler.exception.eventscheduler;

public class EventIdentifierMissingException extends EventSchedulerException{
    public EventIdentifierMissingException(String message) {
        super(message);
    }
}
