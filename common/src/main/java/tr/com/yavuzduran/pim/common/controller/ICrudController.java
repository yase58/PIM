package tr.com.yavuzduran.pim.common.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.yavuzduran.pim.exceptionhandler.exception.PIMException;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;

import java.util.List;

@RestController
public abstract class ICrudController<DTO, UniqKey> {

    @PostMapping
    public abstract ResponseEntity<Response> save(@RequestBody DTO dto) throws DataAccessException, PIMException;

    @PutMapping("/{uniqKey}")
    public abstract ResponseEntity<Response> update(@PathVariable UniqKey uniqKey,@RequestBody DTO dto) throws DataAccessException, PIMException;

    @DeleteMapping("/{uniqKey}")
    public abstract ResponseEntity<Response> remove(@PathVariable UniqKey uniqKey) throws DataAccessException, PIMException;

    @GetMapping("/{uniqKey}")
    public abstract ResponseEntity<DTO> getData(@PathVariable UniqKey uniqKey) throws DataAccessException, PIMException;

    @GetMapping("/data")
    public abstract ResponseEntity<List<DTO>> getAllData() throws DataAccessException, PIMException;

    @GetMapping("/test")
    public String test(){
        return "test";
    }


}
