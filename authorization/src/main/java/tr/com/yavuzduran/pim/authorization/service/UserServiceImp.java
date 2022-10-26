package tr.com.yavuzduran.pim.authorization.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.authorization.dto.UserDto;
import tr.com.yavuzduran.pim.authorization.util.ModelConverter;
import tr.com.yavuzduran.pim.common.service.ICrudService;
import tr.com.yavuzduran.pim.exceptionhandler.exception.authorization.*;
import tr.com.yavuzduran.pim.security.model.Role;
import tr.com.yavuzduran.pim.security.model.User;
import tr.com.yavuzduran.pim.security.repository.RoleRepository;
import tr.com.yavuzduran.pim.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImp implements ICrudService<UserDto, String> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void save(UserDto userDto) throws DataAccessException, UserAlreadyExistException, UserIdentifiersMissingException {
        dtoCheck(userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(ModelConverter.convert(userDto));
    }

    @Override
    public void update(String username, UserDto userDto) throws UserNullException, UserIdentifiersMissingException, UserNotFoundException, UserAlreadyExistException {
        User user = uniqueKeyCheck(username);
        dtoCheck(userDto);
        user.setRoles(ModelConverter.convertAllRoleEntity(userDto.getRoles()));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        userRepository.save(user);
    }

    @Override
    public void remove(String username) throws UserNullException, UserNotFoundException {
        User user = uniqueKeyCheck(username);
        userRepository.delete(user);
    }

    public void addRoleTo(String username, String roleName) throws DataAccessException, UserNullException, RoleNullException, UserNotFoundException, RoleNotFoundException {
        if (roleName == null || roleName.equals("")) {
            throw new RoleNullException("Role name is missing!");
        }
        User user = uniqueKeyCheck(username);
        Role role = roleRepository.findByName(roleName);
        if (role == null || role.getName() == null || role.getName().equals("")) {
            throw new RoleNotFoundException("Role Not Found!");
        }
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public UserDto getData(String username) throws DataAccessException, UserNullException, UserNotFoundException {
        User user = uniqueKeyCheck(username);
        return ModelConverter.convert(user);
    }

    @Override
    public List<UserDto> getAllData() throws DataAccessException {
        return ModelConverter.convertAllUser(userRepository.findAll());
    }


    private void dtoCheck(UserDto userDto) throws UserIdentifiersMissingException, UserAlreadyExistException {
        if (userDto.getUsername() == null || userDto.getUsername().equals("") ||
                userDto.getName() == null || userDto.getName().equals("") ||
                userDto.getSurname() == null || userDto.getSurname().equals("") ||
                userDto.getPassword() == null || userDto.getPassword().equals("")) {
            throw new UserIdentifiersMissingException("Username, name, surname or password is missing!");
        }
        User user = userRepository.findByUsername(userDto.getUsername());
        if (user != null) {
            throw new UserAlreadyExistException("User Already Exist!");
        }
    }


    private User uniqueKeyCheck(String username) throws UserNullException, UserNotFoundException {
        if (username == null || username.equals("")) {
            throw new UserNullException("Username is missing!");
        }
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserNotFoundException("Username Not Found! Username : "+username);
        }
        return user;
    }

}
