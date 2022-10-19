package tr.com.yavuzduran.pim.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.authorization.model.User;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
