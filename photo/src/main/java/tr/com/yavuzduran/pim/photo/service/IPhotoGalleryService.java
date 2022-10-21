package tr.com.yavuzduran.pim.photo.service;

import org.springframework.web.multipart.MultipartFile;
import tr.com.yavuzduran.pim.exceptionhandler.exception.FileEmptyException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.FileExistException;
import tr.com.yavuzduran.pim.photo.entity.Album;
import tr.com.yavuzduran.pim.photo.entity.Photo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface IPhotoGalleryService {
    void upload(String album, MultipartFile file, HttpServletRequest request) throws IOException, FileEmptyException, FileExistException;

    void addNewAlbums(Album album);

    List<Photo> getAllPhotos();

    List<Album> getAllAlbums();

    List<Photo> getAllPhotos(String album);

    void updatePhotoAlbums(String album, String photoUrl);
}
