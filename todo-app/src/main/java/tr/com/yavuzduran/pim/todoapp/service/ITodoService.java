package tr.com.yavuzduran.pim.todoapp.service;

import tr.com.yavuzduran.pim.exceptionhandler.exception.TodoParseException;
import tr.com.yavuzduran.pim.todoapp.dto.TodoDto;

import java.util.List;

public interface ITodoService {

    void createTodo(TodoDto eventDto) throws TodoParseException;

    void updateTodo(String eventTitle, TodoDto eventDto) throws TodoParseException;

    void removeTodo(String eventTitle);

    List<TodoDto> getTodos();

    TodoDto getTodo(String eventTitle);

}
