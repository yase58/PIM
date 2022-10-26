package tr.com.yavuzduran.pim.todoapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.common.service.ICrudService;
import tr.com.yavuzduran.pim.exceptionhandler.exception.todo.*;
import tr.com.yavuzduran.pim.todoapp.dto.TodoDto;
import tr.com.yavuzduran.pim.todoapp.model.Todo;
import tr.com.yavuzduran.pim.todoapp.repository.TodoRepository;
import tr.com.yavuzduran.pim.todoapp.util.TimeFormatter;
import tr.com.yavuzduran.pim.todoapp.util.TodoModelConverter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImp implements ICrudService<TodoDto, String> {

    private final TodoRepository repository;

    @Override
    public void save(TodoDto eventDto) throws TodoParseException, TodoAlreadyExistException, TodoIdentifierMissingException {
        dtoCheck(eventDto);
        repository.save(TodoModelConverter.convert(eventDto));
    }

    @Override
    public void update(String eventTitle, TodoDto todoDto) throws TodoParseException, TodoTitleNullException, TodoNotFoundException {
        Todo todo = uniqueKeyCheck(eventTitle);
        todo.setComment(todoDto.getComment());
        todo.setTitle(todoDto.getTitle());
        todo.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        todo.setDueDate(TimeFormatter.parse(todoDto.getDueDate()));
        repository.save(todo);
    }

    @Override
    public void remove(String eventTitle) throws TodoTitleNullException, TodoNotFoundException {
        Todo todo = uniqueKeyCheck(eventTitle);
        repository.delete(todo);
    }

    @Override
    public List<TodoDto> getAllData() {
        return TodoModelConverter.convert(repository.findByUsername(
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    @Override
    public TodoDto getData(String eventTitle) throws TodoTitleNullException, TodoNotFoundException {
        Todo todo = uniqueKeyCheck(eventTitle);
        return TodoModelConverter.convert(todo);
    }

    private void dtoCheck(TodoDto todoDto) throws TodoIdentifierMissingException, TodoAlreadyExistException {
        if (todoDto == null ||
                todoDto.getTitle() == null || todoDto.getTitle().equals("")) {
            throw new TodoIdentifierMissingException("Title does not be null!");
        }
        Todo todo = repository.findByUsernameAndTitle(
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), todoDto.getTitle());
        if (todo != null) {
            throw new TodoAlreadyExistException("Todo Already exist!");
        }
    }


    private Todo uniqueKeyCheck(String title) throws TodoNotFoundException, TodoTitleNullException {
        if (title == null || title.equals("")) {
            throw new TodoTitleNullException("Blog Software Title does not be null");
        }
        Todo todo = repository.findByUsernameAndTitle(
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), title);
        if (todo == null) {
            throw new TodoNotFoundException("Software Blog Not Found! Title :" + title);
        }
        return todo;
    }
}
