package tr.com.yavuzduran.pim.blog.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.blog.personal.model.BlogPersonal;

import java.util.List;

public interface BlogPersonalRepository extends JpaRepository<BlogPersonal, Long> {

    BlogPersonal findByUsernameAndTitle(String username, String title);

    List<BlogPersonal> findByUsername(String username);
}
