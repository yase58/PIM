package tr.com.yavuzduran.pim.blog.personal.util;

import tr.com.yavuzduran.pim.blog.personal.dto.BlogPersonalDto;
import tr.com.yavuzduran.pim.blog.personal.dto.BlogPersonalSimpleDto;
import tr.com.yavuzduran.pim.blog.personal.model.BlogPersonal;

import java.util.ArrayList;
import java.util.List;

public class BlogModelConverter {

    private BlogModelConverter() {
    }

    public static BlogPersonalSimpleDto convertSimple(BlogPersonal blogPersonal) {
        return BlogPersonalSimpleDto.builderSimple()
                .title(blogPersonal.getTitle())
                .summary(blogPersonal.getSummary())
                .build();
    }

    public static BlogPersonalDto convert(BlogPersonal blogPersonal) {
        return BlogPersonalDto.builder()
                .title(blogPersonal.getTitle())
                .summary(blogPersonal.getSummary())
                .text(blogPersonal.getText())
                .build();
    }

    public static BlogPersonal convert(BlogPersonalSimpleDto blogPersonal) {
        return BlogPersonal.builder()
                .summary(blogPersonal.getSummary())
                .title(blogPersonal.getTitle())
                .build();
    }

    public static BlogPersonal convert(BlogPersonalDto blogPersonal) {
        return BlogPersonal.builder()
                .title(blogPersonal.getTitle())
                .summary(blogPersonal.getSummary())
                .text(blogPersonal.getText())
                .build();
    }

    public static List<BlogPersonalDto> convert(List<BlogPersonal> blogPersonalList) {
        List<BlogPersonalDto> blogPersonalSimpleDtoList = new ArrayList<>();
        if (blogPersonalList != null && !blogPersonalList.isEmpty()) {
            for (BlogPersonal blogPersonal : blogPersonalList) {
                blogPersonalSimpleDtoList.add(convert(blogPersonal));
            }
        }
        return blogPersonalSimpleDtoList;
    }

}
