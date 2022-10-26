package tr.com.yavuzduran.pim.exceptionhandler.exception.eventscheduler;

import tr.com.yavuzduran.pim.exceptionhandler.exception.PIMException;

public class EventSchedulerException extends PIMException {

    public EventSchedulerException(String message) {
        super(message);
    }

    public EventSchedulerException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventSchedulerException(Throwable cause) {
        super(cause);
    }
}
