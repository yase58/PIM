package tr.com.yavuzduran.pim.exceptionhandler.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.com.yavuzduran.pim.exceptionhandler.exception.FileEmptyException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.FileExistException;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PhotoExceptionHandler {

    @ExceptionHandler(FileEmptyException.class)
    public ResponseEntity<Response> errorHandling(FileEmptyException e) {
        e.printStackTrace();
        return ResponseBuilder.createError("Empty File Error");
    }

    @ExceptionHandler(FileExistException.class)
    public ResponseEntity<Response> errorHandling(FileExistException e) {
        e.printStackTrace();
        return ResponseBuilder.createError("File Already Exist! Try Again upload", HttpStatus.CONTINUE);
    }

}
