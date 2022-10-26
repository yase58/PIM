package tr.com.yavuzduran.pim.authorization.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.common.service.ICrudService;
import tr.com.yavuzduran.pim.exceptionhandler.exception.PIMException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.authorization.RoleAlreadyExistException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.authorization.RoleNotFoundException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.authorization.RoleNullException;
import tr.com.yavuzduran.pim.security.model.Role;
import tr.com.yavuzduran.pim.security.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements ICrudService<String, String> {

    private final RoleRepository roleRepository;

    @Override
    public void save(String roleName) throws DataAccessException, PIMException {
        dtoCheck(roleName);
        roleRepository.save(Role.builder().name(roleName).build());
    }

    @Override
    public void update(String roleName, String newRoleName) throws DataAccessException, PIMException {
        dtoCheck(newRoleName);
        Role role = uniqueKeyCheck(roleName);
        role.setName(newRoleName);
        roleRepository.save(role);
    }

    @Override
    public void remove(String roleName) throws DataAccessException, RoleNullException, RoleNotFoundException {
        Role role = uniqueKeyCheck(roleName);
        roleRepository.delete(role);
    }

    @Override
    public List<String> getAllData() throws DataAccessException {
        return roleRepository.findAll().stream().map(Role::getName).collect(Collectors.toList());
    }

    @Override
    public String getData(String roleName) throws DataAccessException, PIMException {
        Role role = uniqueKeyCheck(roleName);
        return role.getName();
    }

    private void dtoCheck(String roleName) throws RoleNullException, RoleAlreadyExistException {
        if (roleName == null || roleName.equals("")) {
            throw new RoleNullException("Role name does not be null!");
        }
        Role role = roleRepository.findByName(roleName);
        if (role != null) {
            throw new RoleAlreadyExistException("Role already exist! Role : " + roleName);
        }
    }


    private Role uniqueKeyCheck(String roleName) throws RoleNullException, RoleNotFoundException {
        if (roleName == null || roleName.equals("")) {
            throw new RoleNullException("Role name does not be null!");
        }
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new RoleNotFoundException("Role Already exist! Role : " + roleName);
        }
        return role;
    }

}
