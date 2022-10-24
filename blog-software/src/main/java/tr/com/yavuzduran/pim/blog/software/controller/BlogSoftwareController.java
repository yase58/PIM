package tr.com.yavuzduran.pim.blog.software.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.yavuzduran.pim.blog.software.dto.BlogSoftwareDto;
import tr.com.yavuzduran.pim.blog.software.dto.BlogSoftwareSimpleDto;
import tr.com.yavuzduran.pim.blog.software.service.IBlogSoftwareService;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogSoftwareController {

    private final IBlogSoftwareService service;

    @PostMapping
    public ResponseEntity<Response> createBlogSoftware(BlogSoftwareDto blogSoftwareDto) {
        service.createBlogSoftware(blogSoftwareDto);
        return ResponseBuilder.createSuccess();
    }

    @PatchMapping
    public ResponseEntity<Response> updateBlogSoftware(String title, BlogSoftwareDto blogSoftwareDto) {
        service.updateBlogSoftware(title, blogSoftwareDto);
        return ResponseBuilder.createSuccess();
    }

    @DeleteMapping
    public ResponseEntity<Response> removeBlogSoftware(String title) {
        service.removeBlogSoftware(title);
        return ResponseBuilder.createSuccess();
    }

    @GetMapping
    public ResponseEntity<List<BlogSoftwareSimpleDto>> getAllBlogSoftware() {
        return ResponseEntity.ok(service.getAllBlogSoftware());
    }

    @GetMapping("/{title}")
    public ResponseEntity<BlogSoftwareDto> getBlogSoftware(@PathVariable String title) {
        return ResponseEntity.ok(service.getBlogSoftware(title));
    }

}
