package tr.com.yavuzduran.pim.todoapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoDto {

    private String title;

    private String comment;

    private String dueDate;

}
