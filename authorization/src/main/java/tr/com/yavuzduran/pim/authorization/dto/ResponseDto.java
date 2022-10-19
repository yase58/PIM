package tr.com.yavuzduran.pim.authorization.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ResponseDto {

    private String statusText;
    private int status;

    @Builder
    public ResponseDto(String statusText, int status) {
        this.statusText = statusText;
        this.status = status;
    }
}
