package tr.com.yavuzduran.pim.photo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.photo.entity.Photo;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findByAlbum_Id(long album_id);

    Photo findByUrl(String url);

}
