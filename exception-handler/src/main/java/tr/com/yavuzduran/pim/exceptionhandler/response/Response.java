package tr.com.yavuzduran.pim.exceptionhandler.response;

import lombok.Builder;
import lombok.Data;

@Data
public class Response {
    private String statusText;
    private int status;

    @Builder
    public Response(String statusText, int status) {
        this.statusText = statusText;
        this.status = status;
    }
}
