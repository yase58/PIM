package tr.com.yavuzduran.pim.exceptionhandler.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.com.yavuzduran.pim.exceptionhandler.exception.authorization.*;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogpersonal.BlogPersonalException;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthorizationExceptionHandler {

    @ExceptionHandler(RoleAlreadyExistException.class)
    public ResponseEntity<Response> errorHandling(RoleAlreadyExistException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Response> errorHandling(RoleNotFoundException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNullException.class)
    public ResponseEntity<Response> errorHandling(RoleNullException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Response> errorHandling(UserAlreadyExistException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserIdentifiersMissingException.class)
    public ResponseEntity<Response> errorHandling(UserIdentifiersMissingException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNullException.class)
    public ResponseEntity<Response> errorHandling(UserNullException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> errorHandling(UserNotFoundException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Response> errorHandling(AuthorizationException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage());
    }

}
