package tr.com.yavuzduran.pim.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.authorization.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
