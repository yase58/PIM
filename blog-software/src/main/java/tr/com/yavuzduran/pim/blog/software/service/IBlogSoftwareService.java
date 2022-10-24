package tr.com.yavuzduran.pim.blog.software.service;

import tr.com.yavuzduran.pim.blog.software.dto.BlogSoftwareDto;
import tr.com.yavuzduran.pim.blog.software.dto.BlogSoftwareSimpleDto;

import java.util.List;

public interface IBlogSoftwareService {

    void createBlogSoftware(BlogSoftwareDto blogSoftwareDto);

    void updateBlogSoftware(String title, BlogSoftwareDto blogSoftwareDto);

    void removeBlogSoftware(String title);

    List<BlogSoftwareSimpleDto> getAllBlogSoftware();

    BlogSoftwareDto getBlogSoftware(String title);

}
