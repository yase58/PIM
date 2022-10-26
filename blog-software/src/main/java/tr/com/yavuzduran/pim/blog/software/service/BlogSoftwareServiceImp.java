package tr.com.yavuzduran.pim.blog.software.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.blog.software.dto.BlogSoftwareDto;
import tr.com.yavuzduran.pim.blog.software.model.BlogSoftware;
import tr.com.yavuzduran.pim.blog.software.repository.BlogSoftwareRepository;
import tr.com.yavuzduran.pim.blog.software.util.BlogModelConverter;
import tr.com.yavuzduran.pim.common.service.ICrudService;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogsoftware.BlogSoftwareAlreadyExistException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogsoftware.BlogSoftwareIdentifierMissingException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogsoftware.BlogSoftwareNotFoundException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogsoftware.SoftwareTitleNullException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogSoftwareServiceImp implements ICrudService<BlogSoftwareDto, String> {

    private final BlogSoftwareRepository blogSoftwareRepository;

    @Override
    public void save(BlogSoftwareDto blogSoftwareDto) throws BlogSoftwareAlreadyExistException, BlogSoftwareIdentifierMissingException {
        dtoCheck(blogSoftwareDto);
        BlogSoftware blogSoftware1 = BlogModelConverter.convert(blogSoftwareDto);
        blogSoftware1.setCreateTime(new Date());
        blogSoftware1.setUpdateTime(new Date());
        blogSoftwareRepository.save(blogSoftware1);
    }

    @Override
    public void update(String title, BlogSoftwareDto blogSoftwareDto) throws BlogSoftwareAlreadyExistException, BlogSoftwareIdentifierMissingException, BlogSoftwareNotFoundException, SoftwareTitleNullException {
        dtoCheck(blogSoftwareDto);
        BlogSoftware blogSoftware = uniqueKeyCheck(title);
        blogSoftware.setText(blogSoftwareDto.getText());
        blogSoftware.setTitle(blogSoftwareDto.getTitle());
        blogSoftware.setSummary(blogSoftwareDto.getSummary());
        blogSoftware.setUpdateTime(new Date());
        blogSoftware.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        blogSoftwareRepository.save(blogSoftware);
    }

    @Override
    public void remove(String title) throws BlogSoftwareNotFoundException, SoftwareTitleNullException {
        BlogSoftware blogSoftware = uniqueKeyCheck(title);
        blogSoftwareRepository.delete(blogSoftware);
    }

    @Override
    public List<BlogSoftwareDto> getAllData() {
        List<BlogSoftware> blogSoftwareList = blogSoftwareRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return BlogModelConverter.convert(blogSoftwareList);
    }

    @Override
    public BlogSoftwareDto getData(String title) throws BlogSoftwareNotFoundException, SoftwareTitleNullException {
        BlogSoftware blogSoftware = uniqueKeyCheck(title);
        return BlogModelConverter.convert(blogSoftware);
    }

    private void dtoCheck(BlogSoftwareDto blogSoftwareDto) throws BlogSoftwareIdentifierMissingException, BlogSoftwareAlreadyExistException {
        if (blogSoftwareDto == null ||
                blogSoftwareDto.getText() == null || blogSoftwareDto.getText().equals("") ||
                blogSoftwareDto.getTitle() == null || blogSoftwareDto.getTitle().equals("")) {
            throw new BlogSoftwareIdentifierMissingException("text or title is missing!");
        }
        BlogSoftware blogSoftware = blogSoftwareRepository.findByUsernameAndTitle((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), blogSoftwareDto.getTitle());
        if (blogSoftware != null) {
            throw new BlogSoftwareAlreadyExistException("Blog already exist!");
        }
    }


    private BlogSoftware uniqueKeyCheck(String title) throws BlogSoftwareNotFoundException, SoftwareTitleNullException {
        if (title == null || title.equals("")) {
            throw new SoftwareTitleNullException("Blog Software Title does not be null");
        }
        BlogSoftware blogSoftware = blogSoftwareRepository.findByUsernameAndTitle(
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), title);
        if (blogSoftware == null) {
            throw new BlogSoftwareNotFoundException("Software Blog Not Found! Title :" + title);
        }
        return blogSoftware;
    }
}
