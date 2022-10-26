package tr.com.yavuzduran.pim.authorization.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.yavuzduran.pim.authorization.service.RoleServiceImp;
import tr.com.yavuzduran.pim.common.controller.ICrudController;
import tr.com.yavuzduran.pim.exceptionhandler.exception.PIMException;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController extends ICrudController<String, String> {

    private final RoleServiceImp roleService;

    public ResponseEntity<Response> save(String roleDto) throws DataAccessException, PIMException {
        roleService.save(roleDto);
        return ResponseBuilder.createSuccess(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Response> update(String newRoleName, String oldRoleName) throws PIMException {
        roleService.update(oldRoleName, newRoleName);
        return ResponseBuilder.createSuccess();
    }

    public ResponseEntity<Response> remove(String roleName) throws PIMException {
        roleService.remove(roleName);
        return ResponseBuilder.createSuccess();
    }

    public ResponseEntity<List<String>> getAllData() {
        return ResponseEntity.ok(roleService.getAllData());
    }

    public ResponseEntity<String> getData(String roleName) throws PIMException {
        return ResponseEntity.ok(roleService.getData(roleName));
    }

}
