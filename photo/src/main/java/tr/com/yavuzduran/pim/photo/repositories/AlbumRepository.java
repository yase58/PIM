package tr.com.yavuzduran.pim.photo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.photo.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    Album findByName(String name);

}
