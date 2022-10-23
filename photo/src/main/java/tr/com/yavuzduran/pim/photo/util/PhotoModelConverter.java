package tr.com.yavuzduran.pim.photo.util;

import tr.com.yavuzduran.pim.photo.dto.AlbumDto;
import tr.com.yavuzduran.pim.photo.dto.PhotoDto;
import tr.com.yavuzduran.pim.photo.entity.Album;
import tr.com.yavuzduran.pim.photo.entity.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoModelConverter {

    private PhotoModelConverter(){

    }

    public static AlbumDto convert(Album album){
        return AlbumDto.builder()
                .createDate(album.getCreateDate())
                .username(album.getUsername())
                .name(album.getName())
                .description(album.getDescription())
                .build();
    }

    public static Album convert(AlbumDto album){
        return Album.builder()
                .createDate(album.getCreateDate())
                .username(album.getUsername())
                .name(album.getName())
                .description(album.getDescription())
                .build();
    }

    public static Photo convert(PhotoDto photoDto){
        return Photo.builder()
                .createDate(photoDto.getCreateDate())
                .username(photoDto.getUsername())
                .name(photoDto.getName())
                .album(photoDto.getAlbum())
                .comment(photoDto.getComment())
                .title(photoDto.getTitle())
                .url(photoDto.getUrl())
                .build();
    }

    public static PhotoDto convert(Photo photoDto){
        return PhotoDto.builder()
                .createDate(photoDto.getCreateDate())
                .username(photoDto.getUsername())
                .name(photoDto.getName())
                .album(photoDto.getAlbum())
                .comment(photoDto.getComment())
                .title(photoDto.getTitle())
                .url(photoDto.getUrl())
                .build();
    }

    public static List<PhotoDto> convert(List<Photo> photoList){
        List<PhotoDto> photoDtoList = new ArrayList<>();
        if(photoList != null && !photoList.isEmpty()){
            for(Photo photo : photoList){
                photoDtoList.add(convert(photo));
            }
        }
        return photoDtoList;
    }

    public static List<AlbumDto> convertAlbum(List<Album> albumList){
        List<AlbumDto> albumDtoList = new ArrayList<>();
        if(albumList != null && !albumList.isEmpty()){
            for(Album photo : albumList){
                albumDtoList.add(convert(photo));
            }
        }
        return albumDtoList;
    }

}
