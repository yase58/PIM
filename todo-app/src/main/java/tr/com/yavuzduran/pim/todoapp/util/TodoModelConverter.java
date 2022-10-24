package tr.com.yavuzduran.pim.todoapp.util;

import org.springframework.security.core.context.SecurityContextHolder;
import tr.com.yavuzduran.pim.todoapp.dto.TodoDto;
import tr.com.yavuzduran.pim.todoapp.model.Todo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoModelConverter {

    private TodoModelConverter() {
    }

    public static Todo convert(TodoDto toDoDto) throws ParseException {
        Date date = TimeFormatter.parse(toDoDto.getDueDate());
        return Todo.builder()
                .dueDate(date)
                .comment(toDoDto.getComment())
                .title(toDoDto.getTitle())
                .username((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .build();
    }

    public static TodoDto convert(Todo toDoDto) {
        String date = TimeFormatter.format(toDoDto.getDueDate());
        return TodoDto.builder()
                .dueDate(date)
                .comment(toDoDto.getComment())
                .title(toDoDto.getTitle())
                .build();
    }

    public static List<TodoDto> convert(List<Todo> todoList){
        List<TodoDto> todoDtoList = new ArrayList<>();
        if(todoList != null && !todoList.isEmpty()){
            for (Todo todo : todoList){
                todoDtoList.add(convert(todo));
            }
        }
        return todoDtoList;
    }
}
