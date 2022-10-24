package tr.com.yavuzduran.pim.todoapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.exceptionhandler.exception.TodoParseException;
import tr.com.yavuzduran.pim.todoapp.dto.TodoDto;
import tr.com.yavuzduran.pim.todoapp.model.Todo;
import tr.com.yavuzduran.pim.todoapp.repository.TodoRepository;
import tr.com.yavuzduran.pim.todoapp.util.TimeFormatter;
import tr.com.yavuzduran.pim.todoapp.util.TodoModelConverter;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImp implements ITodoService {

    private final TodoRepository repository;

    @Override
    public void createTodo(TodoDto eventDto) throws TodoParseException {
        try {
            repository.save(TodoModelConverter.convert(eventDto));
        } catch (ParseException e) {
            throw new TodoParseException(e);
        }
    }

    @Override
    public void updateTodo(String eventTitle, TodoDto todoDto) throws TodoParseException {
        try {
            Todo todo = repository.findByUsernameAndTitle(
                    (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), eventTitle);
            if (todo != null) {
                todo.setComment(todoDto.getComment());
                todo.setTitle(todoDto.getTitle());
                todo.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                todo.setDueDate(TimeFormatter.parse(todoDto.getDueDate()));
                repository.save(todo);
            }
        } catch (ParseException e) {
            throw new TodoParseException(e);
        }
    }

    @Override
    public void removeTodo(String eventTitle) {
        Todo todo = repository.findByUsernameAndTitle(
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), eventTitle);
        if (todo != null) {
            repository.delete(todo);
        }
    }

    @Override
    public List<TodoDto> getTodos() {
        return TodoModelConverter.convert(repository.findByUsername(
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    @Override
    public TodoDto getTodo(String eventTitle) {
        return TodoModelConverter.convert(repository.findByUsernameAndTitle(
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), eventTitle));
    }
}
