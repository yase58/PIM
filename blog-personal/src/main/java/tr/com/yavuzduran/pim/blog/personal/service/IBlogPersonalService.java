package tr.com.yavuzduran.pim.blog.personal.service;

import tr.com.yavuzduran.pim.blog.personal.dto.BlogPersonalDto;
import tr.com.yavuzduran.pim.blog.personal.dto.BlogPersonalSimpleDto;

import java.util.List;

public interface IBlogPersonalService {

    void createBlogPersonal(BlogPersonalDto blogPersonalDto);

    void updateBlogPersonal(String title, BlogPersonalDto blogPersonalDto);

    void removeBlogPersonal(String title);

    List<BlogPersonalSimpleDto> getAllBlogPersonal();

    BlogPersonalDto getBlogPersonal(String title);

}
