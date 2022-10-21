package tr.com.yavuzduran.pim.authorization.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.yavuzduran.pim.authorization.dto.UserDto;
import tr.com.yavuzduran.pim.authorization.sevice.IUserService;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser() throws DataAccessException {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/createUser")
    public ResponseEntity<Response> save(@RequestBody UserDto user) throws DataAccessException {
        userService.save(user);
        return ResponseBuilder.createSuccess();
    }

    @PatchMapping("/addRoleToUser")
    public ResponseEntity<Response> addRoles(@RequestBody AddRoleRequest request) throws DataAccessException {
        userService.addRoleTo(request.getUsername(), request.getRoleName());
        return ResponseBuilder.createSuccess();
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @Data
    static class AddRoleRequest {
        private String username;
        private String roleName;
    }
}
