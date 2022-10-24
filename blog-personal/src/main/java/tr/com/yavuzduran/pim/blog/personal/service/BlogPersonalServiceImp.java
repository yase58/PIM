package tr.com.yavuzduran.pim.blog.personal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.blog.personal.dto.BlogPersonalDto;
import tr.com.yavuzduran.pim.blog.personal.dto.BlogPersonalSimpleDto;
import tr.com.yavuzduran.pim.blog.personal.model.BlogPersonal;
import tr.com.yavuzduran.pim.blog.personal.repository.BlogPersonalRepository;
import tr.com.yavuzduran.pim.blog.personal.util.BlogModelConverter;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogPersonalServiceImp implements IBlogPersonalService {

    private final BlogPersonalRepository blogPersonalRepository;

    @Override
    public void createBlogPersonal(BlogPersonalDto blogPersonalDto) {
        BlogPersonal blogPersonal = blogPersonalRepository.findByUsernameAndTitle((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), blogPersonalDto.getTitle());
        if (blogPersonal == null) {
            BlogPersonal blogPersonal1 = BlogModelConverter.convert(blogPersonalDto);
            blogPersonal1.setCreateTime(new Date());
            blogPersonal1.setUpdateTime(new Date());
            blogPersonalRepository.save(blogPersonal1);
        } else {
            updateBlogPersonal(blogPersonal.getTitle(), blogPersonalDto);
        }
    }

    @Override
    public void updateBlogPersonal(String title, BlogPersonalDto blogPersonalDto) {
        BlogPersonal blogPersonal = blogPersonalRepository.findByUsernameAndTitle(title, (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (blogPersonal != null) {
            blogPersonal.setText(blogPersonalDto.getText());
            blogPersonal.setTitle(blogPersonalDto.getTitle());
            blogPersonal.setSummary(blogPersonalDto.getSummary());
            blogPersonal.setUpdateTime(new Date());
            blogPersonal.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            blogPersonalRepository.save(blogPersonal);
        }
    }

    @Override
    public void removeBlogPersonal(String title) {
        BlogPersonal blogPersonal = blogPersonalRepository.findByUsernameAndTitle((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), title);
        if (blogPersonal != null) {
            blogPersonalRepository.delete(blogPersonal);
        }
    }

    @Override
    public List<BlogPersonalSimpleDto> getAllBlogPersonal() {
        List<BlogPersonal> blogPersonalList = blogPersonalRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return BlogModelConverter.convert(blogPersonalList);
    }

    @Override
    public BlogPersonalDto getBlogPersonal(String title) {
        BlogPersonal blogPersonal = blogPersonalRepository.findByUsernameAndTitle((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), title);
        return BlogModelConverter.convert(blogPersonal);
    }
}
