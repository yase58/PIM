package tr.com.yavuzduran.pim.photo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tr.com.yavuzduran.pim.exceptionhandler.exception.FileEmptyException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.FileExistException;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;
import tr.com.yavuzduran.pim.photo.entity.Album;
import tr.com.yavuzduran.pim.photo.entity.Photo;
import tr.com.yavuzduran.pim.photo.service.IPhotoGalleryService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhotoGalleryController {

    private final IPhotoGalleryService service;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> upload(@RequestParam String album, @RequestParam MultipartFile file, HttpServletRequest request) throws IOException, FileEmptyException, FileExistException {
        service.upload(album, file, request);
        return ResponseBuilder.createSuccess();
    }

    @PostMapping("/NewAlbum")
    public ResponseEntity<Response> addNewAlbums(@RequestBody Album album) {
        album.setCreateDate(new Date());
        service.addNewAlbums(album);
        return ResponseBuilder.createSuccess();
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAllAlbums() {
        return ResponseEntity.ok(service.getAllAlbums());
    }

    @GetMapping("/photos")
    public ResponseEntity<List<Photo>> getAllPhotos() {
        return ResponseEntity.ok(service.getAllPhotos());
    }

    @GetMapping("/{album}")
    public ResponseEntity<List<Photo>> getAllPhotos(@PathVariable(value = "album") String album) {
        return ResponseEntity.ok(service.getAllPhotos(album));
    }

    @PatchMapping("/{album}")
    public ResponseEntity<Response> updatePhotoAlbums(@PathVariable(value = "album") String album, @RequestBody String photoUrl){
        service.updatePhotoAlbums(album, photoUrl);
        return ResponseBuilder.createSuccess();
    }

}
