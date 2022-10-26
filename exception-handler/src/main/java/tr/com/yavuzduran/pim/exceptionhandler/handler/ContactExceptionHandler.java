package tr.com.yavuzduran.pim.exceptionhandler.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.com.yavuzduran.pim.exceptionhandler.exception.contact.*;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ContactExceptionHandler {

    @ExceptionHandler(ContactAlreadyExistException.class)
    public ResponseEntity<Response> errorHandling(ContactAlreadyExistException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ContactIdentifierNullException.class)
    public ResponseEntity<Response> errorHandling(ContactIdentifierNullException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactNameNullException.class)
    public ResponseEntity<Response> errorHandling(ContactNameNullException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<Response> errorHandling(ContactNotFoundException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContactException.class)
    public ResponseEntity<Response> errorHandling(ContactException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage());
    }

}
