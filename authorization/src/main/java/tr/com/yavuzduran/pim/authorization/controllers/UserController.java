package tr.com.yavuzduran.pim.authorization.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.yavuzduran.pim.authorization.dto.UserDto;
import tr.com.yavuzduran.pim.authorization.service.UserServiceImp;
import tr.com.yavuzduran.pim.common.controller.ICrudController;
import tr.com.yavuzduran.pim.exceptionhandler.exception.authorization.*;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends ICrudController<UserDto, String> {

    private final UserServiceImp userService;
    @Override
    public ResponseEntity<List<UserDto>> getAllData() throws DataAccessException {
        return ResponseEntity.ok(userService.getAllData());
    }
    @Override
    public ResponseEntity<UserDto> getData(String username) throws UserNotFoundException, UserNullException {
        return ResponseEntity.ok(userService.getData(username));
    }
    @Override
    public ResponseEntity<Response> save(UserDto user) throws DataAccessException, UserAlreadyExistException, UserIdentifiersMissingException {
        userService.save(user);
        return ResponseBuilder.createSuccess(HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<Response> update(String username, UserDto userDto) throws UserNotFoundException, UserNullException, UserAlreadyExistException, UserIdentifiersMissingException {
        userService.update(username, userDto);
        return ResponseBuilder.createSuccess();
    }
    @Override
    public ResponseEntity<Response> remove(String username) throws UserNotFoundException, UserNullException {
        userService.remove(username);
        return ResponseBuilder.createSuccess();
    }

    @PatchMapping("/{username}")
    public ResponseEntity<Response> addRoles(@PathVariable String username, @RequestParam String roleName) throws DataAccessException, UserNotFoundException, UserNullException, RoleNotFoundException, RoleNullException {
        userService.addRoleTo(username, roleName);
        return ResponseBuilder.createSuccess();
    }


}
