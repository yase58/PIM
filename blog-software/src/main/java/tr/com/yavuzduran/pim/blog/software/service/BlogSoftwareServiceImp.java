package tr.com.yavuzduran.pim.blog.software.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.blog.software.dto.BlogSoftwareDto;
import tr.com.yavuzduran.pim.blog.software.dto.BlogSoftwareSimpleDto;
import tr.com.yavuzduran.pim.blog.software.model.BlogSoftware;
import tr.com.yavuzduran.pim.blog.software.repository.BlogSoftwareRepository;
import tr.com.yavuzduran.pim.blog.software.util.BlogModelConverter;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogSoftwareServiceImp implements IBlogSoftwareService {

    private final BlogSoftwareRepository blogSoftwareRepository;

    @Override
    public void createBlogSoftware(BlogSoftwareDto blogSoftwareDto) {
        BlogSoftware blogSoftware = blogSoftwareRepository.findByUsernameAndTitle((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), blogSoftwareDto.getTitle());
        if (blogSoftware == null) {
            BlogSoftware blogSoftware1 = BlogModelConverter.convert(blogSoftwareDto);
            blogSoftware1.setCreateTime(new Date());
            blogSoftware1.setUpdateTime(new Date());
            blogSoftwareRepository.save(blogSoftware1);
        } else {
            updateBlogSoftware(blogSoftware.getTitle(), blogSoftwareDto);
        }
    }

    @Override
    public void updateBlogSoftware(String title, BlogSoftwareDto blogSoftwareDto) {
        BlogSoftware blogSoftware = blogSoftwareRepository.findByUsernameAndTitle(title, (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (blogSoftware != null) {
            blogSoftware.setText(blogSoftwareDto.getText());
            blogSoftware.setTitle(blogSoftwareDto.getTitle());
            blogSoftware.setSummary(blogSoftwareDto.getSummary());
            blogSoftware.setUpdateTime(new Date());
            blogSoftware.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            blogSoftwareRepository.save(blogSoftware);
        }
    }

    @Override
    public void removeBlogSoftware(String title) {
        BlogSoftware blogSoftware = blogSoftwareRepository.findByUsernameAndTitle((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), title);
        if (blogSoftware != null) {
            blogSoftwareRepository.delete(blogSoftware);
        }
    }

    @Override
    public List<BlogSoftwareSimpleDto> getAllBlogSoftware() {
        List<BlogSoftware> blogSoftwareList = blogSoftwareRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return BlogModelConverter.convert(blogSoftwareList);
    }

    @Override
    public BlogSoftwareDto getBlogSoftware(String title) {
        BlogSoftware blogSoftware = blogSoftwareRepository.findByUsernameAndTitle((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), title);
        return BlogModelConverter.convert(blogSoftware);
    }
}
