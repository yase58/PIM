package tr.com.yavuzduran.pim.exceptionhandler.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogpersonal.*;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BlogPersonalExceptionHandler {

    @ExceptionHandler(BlogPersonalAlreadyExistException.class)
    public ResponseEntity<Response> errorHandling(BlogPersonalAlreadyExistException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BlogPersonalIdentifierMissingException.class)
    public ResponseEntity<Response> errorHandling(BlogPersonalIdentifierMissingException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlogPersonalNotFoundException.class)
    public ResponseEntity<Response> errorHandling(BlogPersonalNotFoundException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonalTitleNullException.class)
    public ResponseEntity<Response> errorHandling(PersonalTitleNullException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlogPersonalException.class)
    public ResponseEntity<Response> errorHandling(BlogPersonalException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage());
    }

}
