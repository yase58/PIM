package tr.com.yavuzduran.pim.blog.personal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tr.com.yavuzduran.pim.blog.personal.dto.BlogPersonalDto;
import tr.com.yavuzduran.pim.blog.personal.service.BlogPersonalServiceImp;
import tr.com.yavuzduran.pim.common.controller.ICrudController;
import tr.com.yavuzduran.pim.exceptionhandler.exception.PIMException;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogPersonalController extends ICrudController<BlogPersonalDto, String> {

    private final BlogPersonalServiceImp service;

    @Override
    public ResponseEntity<Response> save(BlogPersonalDto blogPersonalDto) throws PIMException {
        service.save(blogPersonalDto);
        return ResponseBuilder.createSuccess(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Response> update(String title, BlogPersonalDto blogPersonalDto) throws PIMException {
        service.update(title, blogPersonalDto);
        return ResponseBuilder.createSuccess();
    }

    @Override
    public ResponseEntity<Response> remove(String title) throws PIMException {
        service.remove(title);
        return ResponseBuilder.createSuccess();
    }

    @Override
    public ResponseEntity<List<BlogPersonalDto>> getAllData() {
        return ResponseEntity.ok(service.getAllData());
    }

    @Override
    public ResponseEntity<BlogPersonalDto> getData(String title) throws PIMException {
        return ResponseEntity.ok(service.getData(title));
    }

}
