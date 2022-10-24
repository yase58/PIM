package tr.com.yavuzduran.pim.blog.personal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogPersonalSimpleDto {

    private String title;
    private String summary;

}
