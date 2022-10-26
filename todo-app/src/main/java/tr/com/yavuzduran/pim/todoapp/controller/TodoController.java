package tr.com.yavuzduran.pim.todoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tr.com.yavuzduran.pim.common.controller.ICrudController;
import tr.com.yavuzduran.pim.exceptionhandler.exception.todo.*;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;
import tr.com.yavuzduran.pim.todoapp.dto.TodoDto;
import tr.com.yavuzduran.pim.todoapp.service.TodoServiceImp;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController extends ICrudController<TodoDto, String> {

    private final TodoServiceImp service;

    @Override
    public ResponseEntity<Response> save(TodoDto todoDto) throws TodoParseException, TodoAlreadyExistException, TodoIdentifierMissingException {
        service.save(todoDto);
        return ResponseBuilder.createSuccess(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response> update(String eventTitle, TodoDto eventDto) throws TodoParseException, TodoTitleNullException, TodoNotFoundException {
        service.update(eventTitle, eventDto);
        return ResponseBuilder.createSuccess();
    }

    @Override
    public ResponseEntity<Response> remove(String eventTitle) throws TodoTitleNullException, TodoNotFoundException {
        service.remove(eventTitle);
        return ResponseBuilder.createSuccess();
    }

    @Override
    public ResponseEntity<List<TodoDto>> getAllData() {
        return ResponseEntity.ok(service.getAllData());
    }

    @Override
    public ResponseEntity<TodoDto> getData(String eventTitle) throws TodoTitleNullException, TodoNotFoundException {
        return ResponseEntity.ok(service.getData(eventTitle));
    }

}
