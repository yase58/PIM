package tr.com.yavuzduran.pim.authorization.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tr.com.yavuzduran.pim.authorization.dto.ResponseDto;

public class ResponseBuilder {

    public static ResponseEntity<ResponseDto> createSuccess(){
        return ResponseEntity.ok(ResponseDto.builder().status(HttpStatus.OK.value()).statusText("OK").build());
    }

    public static ResponseEntity<ResponseDto> createError(String statusText){
        return ResponseEntity.ok(ResponseDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).statusText(statusText).build());
    }

    public static ResponseEntity<ResponseDto> createError(String statusText, HttpStatus status){
        return ResponseEntity.ok(ResponseDto.builder().status(status.value()).statusText(statusText).build());
    }

}
