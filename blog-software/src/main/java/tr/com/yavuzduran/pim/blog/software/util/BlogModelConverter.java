package tr.com.yavuzduran.pim.blog.software.util;

import tr.com.yavuzduran.pim.blog.software.dto.BlogSoftwareDto;
import tr.com.yavuzduran.pim.blog.software.dto.BlogSoftwareSimpleDto;
import tr.com.yavuzduran.pim.blog.software.model.BlogSoftware;

import java.util.ArrayList;
import java.util.List;

public class BlogModelConverter {

    private BlogModelConverter() {
    }

    public static BlogSoftwareSimpleDto convertSimple(BlogSoftware blogSoftware) {
        return BlogSoftwareSimpleDto.builder()
                .title(blogSoftware.getTitle())
                .summary(blogSoftware.getSummary())
                .build();
    }

    public static BlogSoftwareDto convert(BlogSoftware blogSoftware) {
        return BlogSoftwareDto.builder()
                .title(blogSoftware.getTitle())
                .summary(blogSoftware.getSummary())
                .text(blogSoftware.getText())
                .build();
    }

    public static BlogSoftware convert(BlogSoftwareSimpleDto blogSoftware) {
        return BlogSoftware.builder()
                .summary(blogSoftware.getSummary())
                .title(blogSoftware.getTitle())
                .build();
    }

    public static BlogSoftware convert(BlogSoftwareDto blogSoftware) {
        return BlogSoftware.builder()
                .title(blogSoftware.getTitle())
                .summary(blogSoftware.getSummary())
                .text(blogSoftware.getText())
                .build();
    }

    public static List<BlogSoftwareDto> convert(List<BlogSoftware> blogSoftwareList) {
        List<BlogSoftwareDto> blogSoftwareSimpleDtoList = new ArrayList<>();
        if (blogSoftwareList != null && !blogSoftwareList.isEmpty()) {
            for (BlogSoftware blogSoftware : blogSoftwareList) {
                blogSoftwareSimpleDtoList.add(convert(blogSoftware));
            }
        }
        return blogSoftwareSimpleDtoList;
    }

}
