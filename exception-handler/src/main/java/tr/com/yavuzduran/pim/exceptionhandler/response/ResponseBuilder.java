package tr.com.yavuzduran.pim.exceptionhandler.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static ResponseEntity<Response> createSuccess(){
        return ResponseEntity.ok(Response.builder().status(HttpStatus.OK.value()).statusText("OK").build());
    }

    public static ResponseEntity<Response> createError(String statusText){
        return ResponseEntity.ok(Response.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).statusText(statusText).build());
    }

    public static ResponseEntity<Response> createError(String statusText, HttpStatus status){
        return ResponseEntity.ok(Response.builder().status(status.value()).statusText(statusText).build());
    }

}
