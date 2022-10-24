package tr.com.yavuzduran.pim.blog.software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.blog.software.model.BlogSoftware;

import java.util.List;

public interface BlogSoftwareRepository extends JpaRepository<BlogSoftware, Long> {

    BlogSoftware findByUsernameAndTitle(String username, String title);

    List<BlogSoftware> findByUsername(String username);
}
