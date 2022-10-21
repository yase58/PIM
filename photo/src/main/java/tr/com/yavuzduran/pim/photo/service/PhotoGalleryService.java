package tr.com.yavuzduran.pim.photo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tr.com.yavuzduran.pim.exceptionhandler.exception.FileEmptyException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.FileExistException;
import tr.com.yavuzduran.pim.photo.common.CommonConstant;
import tr.com.yavuzduran.pim.photo.entity.Album;
import tr.com.yavuzduran.pim.photo.entity.Photo;
import tr.com.yavuzduran.pim.photo.repositories.AlbumRepository;
import tr.com.yavuzduran.pim.photo.repositories.PhotoRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PhotoGalleryService implements IPhotoGalleryService {

    private final AlbumRepository albumRepository;
    private final PhotoRepository photoRepository;

    @Override
    public void upload(String album, MultipartFile file, HttpServletRequest request) throws IOException, FileEmptyException, FileExistException {
        if (!file.isEmpty()) {
            Album album1 = albumRepository.findByName(album);
            String realPathToUploads = request.getServletContext().getRealPath(CommonConstant.fileUploadAddress);
            if (!new File(realPathToUploads).exists()) {
                new File(realPathToUploads).mkdirs();
            }
            String fileOrgName = file.getOriginalFilename();
            UUID filename = UUID.randomUUID();
            File dest = new File(realPathToUploads, filename.toString());
            if(dest.exists()){
                throw new FileExistException("Filename : " + fileOrgName);
            }
            file.transferTo(dest);
            Photo photo = Photo.builder()
                    .album(album1)
                    .url(dest.getAbsolutePath())
                    .id(filename)
                    .name(fileOrgName)
                    .title(file.getContentType())
                    .build();
            photoRepository.save(photo);
        } else {
            throw new FileEmptyException(file.getName());
        }
    }

    @Override
    public void addNewAlbums(Album album) {
        albumRepository.save(album);
    }

    @Override
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public List<Photo> getAllPhotos(String album) {
        Album album1 = albumRepository.findByName(album);
        return photoRepository.findByAlbum_Id(album1.getId());
    }

    @Override
    public void updatePhotoAlbums(String album, String photoUrl) {
        Album album1 = albumRepository.findByName(album);
        Photo photo = photoRepository.findByUrl(photoUrl);
        photo.setAlbum(album1);
        photoRepository.save(photo);
    }
}
