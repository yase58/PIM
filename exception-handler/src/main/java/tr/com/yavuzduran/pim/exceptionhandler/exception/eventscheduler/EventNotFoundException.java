package tr.com.yavuzduran.pim.exceptionhandler.exception.eventscheduler;

public class EventNotFoundException extends EventSchedulerException{
    public EventNotFoundException(String message) {
        super(message);
    }
}
