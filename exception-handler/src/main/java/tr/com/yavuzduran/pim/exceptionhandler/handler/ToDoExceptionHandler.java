package tr.com.yavuzduran.pim.exceptionhandler.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.com.yavuzduran.pim.exceptionhandler.exception.todo.*;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ToDoExceptionHandler {

    @ExceptionHandler(TodoAlreadyExistException.class)
    public ResponseEntity<Response> errorHandling(TodoAlreadyExistException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TodoIdentifierMissingException.class)
    public ResponseEntity<Response> errorHandling(TodoIdentifierMissingException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<Response> errorHandling(TodoNotFoundException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TodoParseException.class)
    public ResponseEntity<Response> errorHandling(TodoParseException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TodoTitleNullException.class)
    public ResponseEntity<Response> errorHandling(TodoTitleNullException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ToDoAppException.class)
    public ResponseEntity<Response> errorHandling(ToDoAppException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage());
    }

}
