package tr.com.yavuzduran.pim.exceptionhandler.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogsoftware.*;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BlogSoftwareExceptionHandler {

    @ExceptionHandler(BlogSoftwareAlreadyExistException.class)
    public ResponseEntity<Response> errorHandling(BlogSoftwareAlreadyExistException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BlogSoftwareIdentifierMissingException.class)
    public ResponseEntity<Response> errorHandling(BlogSoftwareIdentifierMissingException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlogSoftwareNotFoundException.class)
    public ResponseEntity<Response> errorHandling(BlogSoftwareNotFoundException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SoftwareTitleNullException.class)
    public ResponseEntity<Response> errorHandling(SoftwareTitleNullException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlogSoftwareException.class)
    public ResponseEntity<Response> errorHandling(BlogSoftwareException e) {
        e.printStackTrace();
        return ResponseBuilder.createError(e.getMessage());
    }

}
