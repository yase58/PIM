package tr.com.yavuzduran.pim.exceptionhandler.handler;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

@ControllerAdvice
public class HibernateExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Response> errorHandling(DataAccessException e) {
        e.printStackTrace();
        return ResponseBuilder.createError("Database Access Problem");
    }

}
