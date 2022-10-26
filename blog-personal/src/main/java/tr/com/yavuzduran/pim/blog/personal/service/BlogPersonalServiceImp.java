package tr.com.yavuzduran.pim.blog.personal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.blog.personal.dto.BlogPersonalDto;
import tr.com.yavuzduran.pim.blog.personal.model.BlogPersonal;
import tr.com.yavuzduran.pim.blog.personal.repository.BlogPersonalRepository;
import tr.com.yavuzduran.pim.blog.personal.util.BlogModelConverter;
import tr.com.yavuzduran.pim.common.service.ICrudService;
import tr.com.yavuzduran.pim.exceptionhandler.exception.PIMException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogpersonal.BlogPersonalAlreadyExistException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogpersonal.BlogPersonalIdentifierMissingException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogpersonal.BlogPersonalNotFoundException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogpersonal.PersonalTitleNullException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogPersonalServiceImp implements ICrudService<BlogPersonalDto, String> {

    private final BlogPersonalRepository blogPersonalRepository;

    @Override
    public void save(BlogPersonalDto blogPersonalDto) throws DataAccessException, PIMException {
        dtoCheck(blogPersonalDto);
        BlogPersonal blogPersonal1 = BlogModelConverter.convert(blogPersonalDto);
        blogPersonal1.setCreateTime(new Date());
        blogPersonal1.setUpdateTime(new Date());
        blogPersonalRepository.save(blogPersonal1);
    }

    @Override
    public void update(String title, BlogPersonalDto blogPersonalDto) throws DataAccessException, PIMException {
        dtoCheck(blogPersonalDto);
        BlogPersonal blogPersonal = uniqueKeyCheck(title);
        blogPersonal.setText(blogPersonalDto.getText());
        blogPersonal.setTitle(blogPersonalDto.getTitle());
        blogPersonal.setSummary(blogPersonalDto.getSummary());
        blogPersonal.setUpdateTime(new Date());
        blogPersonal.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        blogPersonalRepository.save(blogPersonal);
    }

    @Override
    public void remove(String title) throws DataAccessException, PIMException {
        BlogPersonal blogPersonal = uniqueKeyCheck(title);
        blogPersonalRepository.delete(blogPersonal);
    }

    @Override
    public List<BlogPersonalDto> getAllData() throws DataAccessException {
        List<BlogPersonal> blogPersonalList = blogPersonalRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return BlogModelConverter.convert(blogPersonalList);
    }

    @Override
    public BlogPersonalDto getData(String title) throws DataAccessException, PIMException {
        BlogPersonal blogPersonal = uniqueKeyCheck(title);
        return BlogModelConverter.convert(blogPersonal);
    }

    private void dtoCheck(BlogPersonalDto blogPersonalDto) throws BlogPersonalIdentifierMissingException, BlogPersonalAlreadyExistException {
        if (blogPersonalDto == null ||
                blogPersonalDto.getText() == null || blogPersonalDto.getText().equals("") ||
                blogPersonalDto.getTitle() == null || blogPersonalDto.getTitle().equals("")) {
            throw new BlogPersonalIdentifierMissingException("text or title is missing!");
        }
        BlogPersonal blogPersonal = blogPersonalRepository.findByUsernameAndTitle((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), blogPersonalDto.getTitle());
        if (blogPersonal != null) {
            throw new BlogPersonalAlreadyExistException("Blog already exist!");
        }
    }


    private BlogPersonal uniqueKeyCheck(String title) throws PersonalTitleNullException, BlogPersonalNotFoundException {
        if (title == null || title.equals("")) {
            throw new PersonalTitleNullException("Blog Personal Title does not be null");
        }
        BlogPersonal blogPersonal = blogPersonalRepository.findByUsernameAndTitle(
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), title);
        if (blogPersonal == null) {
            throw new BlogPersonalNotFoundException("Personal Blog Not Found! Title :" + title);
        }
        return blogPersonal;
    }
}
