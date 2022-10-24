package tr.com.yavuzduran.pim.blog.personal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.yavuzduran.pim.blog.personal.dto.BlogPersonalDto;
import tr.com.yavuzduran.pim.blog.personal.dto.BlogPersonalSimpleDto;
import tr.com.yavuzduran.pim.blog.personal.service.IBlogPersonalService;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogPersonalController {

    private final IBlogPersonalService service;

    @PostMapping
    public ResponseEntity<Response> createBlogPersonal(BlogPersonalDto blogPersonalDto) {
        service.createBlogPersonal(blogPersonalDto);
        return ResponseBuilder.createSuccess();
    }

    @PatchMapping
    public ResponseEntity<Response> updateBlogPersonal(String title, BlogPersonalDto blogPersonalDto) {
        service.updateBlogPersonal(title, blogPersonalDto);
        return ResponseBuilder.createSuccess();
    }

    @DeleteMapping
    public ResponseEntity<Response> removeBlogPersonal(String title) {
        service.removeBlogPersonal(title);
        return ResponseBuilder.createSuccess();
    }

    @GetMapping
    public ResponseEntity<List<BlogPersonalSimpleDto>> getAllBlogPersonal() {
        return ResponseEntity.ok(service.getAllBlogPersonal());
    }

    @GetMapping("/{title}")
    public ResponseEntity<BlogPersonalDto> getBlogPersonal(@PathVariable String title) {
        return ResponseEntity.ok(service.getBlogPersonal(title));
    }

}
