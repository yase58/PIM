package tr.com.yavuzduran.pim.exceptionhandler.exception.eventscheduler;

public class EventAlreadyExistException extends EventSchedulerException{
    public EventAlreadyExistException(String message) {
        super(message);
    }
}
