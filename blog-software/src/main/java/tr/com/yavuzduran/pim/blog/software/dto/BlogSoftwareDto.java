package tr.com.yavuzduran.pim.blog.software.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogSoftwareDto {

    private String text;
    private String title;
    private String summary;

}
