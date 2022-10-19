package tr.com.yavuzduran.pim.authorization.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.yavuzduran.pim.authorization.dto.ResponseDto;
import tr.com.yavuzduran.pim.authorization.dto.RoleDto;
import tr.com.yavuzduran.pim.authorization.sevice.IUserService;
import tr.com.yavuzduran.pim.authorization.util.ResponseBuilder;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final IUserService userService;

    @PostMapping("/createRole")
    public ResponseEntity<ResponseDto> save(@RequestBody RoleDto roleDto) throws DataAccessException {
        userService.save(roleDto);
        return ResponseBuilder.createSuccess();
    }
}
