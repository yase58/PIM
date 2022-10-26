package tr.com.yavuzduran.pim.exceptionhandler.exception.eventscheduler;

public class EventTitleOrDateNullException extends EventSchedulerException{
    public EventTitleOrDateNullException(String message) {
        super(message);
    }
}
