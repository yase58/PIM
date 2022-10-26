package tr.com.yavuzduran.pim.exceptionhandler.exception.eventscheduler;

public class EventParseException extends EventSchedulerException {

    public EventParseException(String message) {
        super(message);
    }

    public EventParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventParseException(Throwable cause) {
        super(cause);
    }
}
