package tr.com.yavuzduran.pim.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.security.model.Role;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
