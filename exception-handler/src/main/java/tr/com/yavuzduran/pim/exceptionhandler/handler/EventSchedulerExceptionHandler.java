package tr.com.yavuzduran.pim.exceptionhandler.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.com.yavuzduran.pim.exceptionhandler.exception.eventscheduler.*;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EventSchedulerExceptionHandler {

    @ExceptionHandler(EventAlreadyExistException.class)
    public ResponseEntity<Response> errorHandling(EventAlreadyExistException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EventIdentifierMissingException.class)
    public ResponseEntity<Response> errorHandling(EventIdentifierMissingException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Response> errorHandling(EventNotFoundException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventParseException.class)
    public ResponseEntity<Response> errorHandling(EventParseException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventTitleOrDateNullException.class)
    public ResponseEntity<Response> errorHandling(EventTitleOrDateNullException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventSchedulerException.class)
    public ResponseEntity<Response> errorHandling(EventSchedulerException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage());
    }

}
