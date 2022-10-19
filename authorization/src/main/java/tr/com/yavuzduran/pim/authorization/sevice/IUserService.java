package tr.com.yavuzduran.pim.authorization.sevice;

import org.springframework.dao.DataAccessException;
import tr.com.yavuzduran.pim.authorization.dto.RoleDto;
import tr.com.yavuzduran.pim.authorization.dto.UserDto;

import java.util.List;

public interface IUserService {

    void save(UserDto user) throws DataAccessException;

    void save(RoleDto role) throws DataAccessException;

    void addRoleTo(String username, String role) throws DataAccessException;

    UserDto getUser(String username) throws DataAccessException;

    List<UserDto> getUsers() throws DataAccessException;

}
