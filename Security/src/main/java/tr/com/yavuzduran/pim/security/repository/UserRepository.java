package tr.com.yavuzduran.pim.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
