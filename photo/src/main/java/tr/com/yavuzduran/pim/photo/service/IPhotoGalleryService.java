package tr.com.yavuzduran.pim.photo.service;

import org.springframework.web.multipart.MultipartFile;
import tr.com.yavuzduran.pim.exceptionhandler.exception.FileEmptyException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.FileExistException;
import tr.com.yavuzduran.pim.photo.dto.AlbumDto;
import tr.com.yavuzduran.pim.photo.dto.PhotoDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface IPhotoGalleryService {
    void upload(String album, MultipartFile file, HttpServletRequest request) throws IOException, FileEmptyException, FileExistException;

    void addNewAlbums(AlbumDto album);

    List<PhotoDto> getAllPhotos();

    List<AlbumDto> getAllAlbums();

    List<PhotoDto> getAllPhotos(String album);

    void updatePhotoAlbums(String album, String photoUrl);
}
