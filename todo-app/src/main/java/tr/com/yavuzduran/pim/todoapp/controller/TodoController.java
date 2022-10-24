package tr.com.yavuzduran.pim.todoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.yavuzduran.pim.exceptionhandler.exception.TodoParseException;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;
import tr.com.yavuzduran.pim.todoapp.dto.TodoDto;
import tr.com.yavuzduran.pim.todoapp.service.ITodoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final ITodoService service;

    @PostMapping
    public ResponseEntity<Response> createTodo(@RequestBody TodoDto todoDto) throws TodoParseException {
        service.createTodo(todoDto);
        return ResponseBuilder.createSuccess();
    }

    @PatchMapping("/{eventTitle}")
    public ResponseEntity<Response> updateTodo(@PathVariable String eventTitle, @RequestBody TodoDto eventDto) throws TodoParseException {
        service.updateTodo(eventTitle, eventDto);
        return ResponseBuilder.createSuccess();
    }

    @DeleteMapping("/{eventTitle}")
    public ResponseEntity<Response> removeTodo(@PathVariable String eventTitle) throws TodoParseException {
        service.removeTodo(eventTitle);
        return ResponseBuilder.createSuccess();
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getTodos() {
        return ResponseEntity.ok(service.getTodos());
    }

    @GetMapping("/{eventTitle}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable String eventTitle) throws TodoParseException {
        return ResponseEntity.ok(service.getTodo(eventTitle));
    }

}
