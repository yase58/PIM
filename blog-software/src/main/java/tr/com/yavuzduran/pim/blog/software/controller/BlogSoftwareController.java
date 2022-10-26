package tr.com.yavuzduran.pim.blog.software.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tr.com.yavuzduran.pim.blog.software.dto.BlogSoftwareDto;
import tr.com.yavuzduran.pim.blog.software.service.BlogSoftwareServiceImp;
import tr.com.yavuzduran.pim.common.controller.ICrudController;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogsoftware.BlogSoftwareAlreadyExistException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogsoftware.BlogSoftwareIdentifierMissingException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogsoftware.BlogSoftwareNotFoundException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.blogsoftware.SoftwareTitleNullException;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogSoftwareController extends ICrudController<BlogSoftwareDto, String> {

    private final BlogSoftwareServiceImp service;

    @Override
    public ResponseEntity<Response> save(BlogSoftwareDto blogSoftwareDto) throws BlogSoftwareAlreadyExistException, BlogSoftwareIdentifierMissingException {
        service.save(blogSoftwareDto);
        return ResponseBuilder.createSuccess(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Response> update(String title, BlogSoftwareDto blogSoftwareDto) throws BlogSoftwareNotFoundException, SoftwareTitleNullException, BlogSoftwareAlreadyExistException, BlogSoftwareIdentifierMissingException {
        service.update(title, blogSoftwareDto);
        return ResponseBuilder.createSuccess();
    }

    @Override
    public ResponseEntity<Response> remove(String title) throws BlogSoftwareNotFoundException, SoftwareTitleNullException {
        service.remove(title);
        return ResponseBuilder.createSuccess();
    }

    @Override
    public ResponseEntity<List<BlogSoftwareDto>> getAllData() {
        return ResponseEntity.ok(service.getAllData());
    }

    @Override
    public ResponseEntity<BlogSoftwareDto> getData(String title) throws BlogSoftwareNotFoundException, SoftwareTitleNullException {
        return ResponseEntity.ok(service.getData(title));
    }

}
