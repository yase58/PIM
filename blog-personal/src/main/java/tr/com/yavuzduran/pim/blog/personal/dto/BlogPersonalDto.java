package tr.com.yavuzduran.pim.blog.personal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogPersonalDto {

    private String text;
    private String title;
    private String summary;

}
