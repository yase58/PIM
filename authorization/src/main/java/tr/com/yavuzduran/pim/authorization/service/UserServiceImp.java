package tr.com.yavuzduran.pim.authorization.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.authorization.dto.RoleDto;
import tr.com.yavuzduran.pim.authorization.dto.UserDto;
import tr.com.yavuzduran.pim.authorization.util.ModelConverter;
import tr.com.yavuzduran.pim.security.model.Role;
import tr.com.yavuzduran.pim.security.model.User;
import tr.com.yavuzduran.pim.security.repository.RoleRepository;
import tr.com.yavuzduran.pim.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void save(UserDto user) throws DataAccessException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(ModelConverter.convert(user));
    }

    @Override
    public void save(RoleDto role) throws DataAccessException {
        roleRepository.save(ModelConverter.convert(role));
    }

    @Override
    public void addRoleTo(String username, String roleName) throws DataAccessException {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public UserDto getUser(String username) throws DataAccessException {
        return ModelConverter.convert(userRepository.findByUsername(username));
    }

    @Override
    public List<UserDto> getUsers() throws DataAccessException {
        return ModelConverter.convertAllUser(userRepository.findAll());
    }

}
