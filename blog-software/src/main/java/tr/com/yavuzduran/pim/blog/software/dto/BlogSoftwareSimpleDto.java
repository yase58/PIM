package tr.com.yavuzduran.pim.blog.software.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogSoftwareSimpleDto {

    private String title;
    private String summary;

}
