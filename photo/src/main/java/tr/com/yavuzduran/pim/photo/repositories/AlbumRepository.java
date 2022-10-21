package tr.com.yavuzduran.pim.photo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.photo.entity.Album;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    Album findByNameAndUsername(String name, String username);

    List<Album> findByUsername(String username);

}
