package tr.com.yavuzduran.pim.photo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.Date;
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
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Album album1 = albumRepository.findByNameAndUsername(album, username);
            String realPathToUploads = request.getServletContext().getRealPath(CommonConstant.fileUploadAddress);
            if (!new File(realPathToUploads).exists()) {
                var b = new File(realPathToUploads).mkdirs();
            }
            String fileOrgName = file.getOriginalFilename();
            UUID filename = UUID.randomUUID();
            File dest = new File(realPathToUploads, filename.toString());
            if (dest.exists()) {
                throw new FileExistException("Filename : " + fileOrgName);
            }
            file.transferTo(dest);
            Photo photo = Photo.builder()
                    .album(album1)
                    .url(dest.getAbsolutePath())
                    .id(filename)
                    .name(fileOrgName)
                    .title(file.getContentType())
                    .username(username)
                    .build();
            photoRepository.save(photo);
        } else {
            throw new FileEmptyException(file.getName());
        }
    }

    @Override
    public void addNewAlbums(Album album) {
        album.setCreateDate(new Date());
        album.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        albumRepository.save(album);
    }

    @Override
    public List<Photo> getAllPhotos() {
        return photoRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Override
    public List<Photo> getAllPhotos(String album) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Album album1 = albumRepository.findByNameAndUsername(album, username);
        return photoRepository.findByAlbum_IdAndUsername(album1.getId(), username);
    }

    @Override
    public void updatePhotoAlbums(String album, String photoUrl) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Album album1 = albumRepository.findByNameAndUsername(album, username);
        Photo photo = photoRepository.findByUrlAndUsername(photoUrl, username);
        photo.setAlbum(album1);
        photoRepository.save(photo);
    }
}
