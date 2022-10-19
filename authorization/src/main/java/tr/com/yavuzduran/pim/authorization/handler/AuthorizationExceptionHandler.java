package tr.com.yavuzduran.pim.authorization.handler;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.com.yavuzduran.pim.authorization.dto.ResponseDto;
import tr.com.yavuzduran.pim.authorization.exception.JWTException;
import tr.com.yavuzduran.pim.authorization.util.ResponseBuilder;

@ControllerAdvice
public class AuthorizationExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseDto> errorHandling(DataAccessException e) {
        e.printStackTrace();
        return ResponseBuilder.createError("Database Access Problem");
    }

    @ExceptionHandler(JWTException.class)
    public ResponseEntity<ResponseDto> errorHandling(JWTException e) {
        e.printStackTrace();
        return ResponseBuilder.createError("Invalid Token", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> errorHandling(Exception e) {
        e.printStackTrace();
        return ResponseBuilder.createError("UnExpected Problem");
    }
}
