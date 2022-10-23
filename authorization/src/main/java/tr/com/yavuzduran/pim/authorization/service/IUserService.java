package tr.com.yavuzduran.pim.authorization.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.authorization.dto.RoleDto;
import tr.com.yavuzduran.pim.authorization.dto.UserDto;

import java.util.List;

@Service
public interface IUserService {

    void save(UserDto user) throws DataAccessException;

    void save(RoleDto role) throws DataAccessException;

    void addRoleTo(String username, String role) throws DataAccessException;

    UserDto getUser(String username) throws DataAccessException;

    List<UserDto> getUsers() throws DataAccessException;

}
