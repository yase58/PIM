package tr.com.yavuzduran.pim.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.todoapp.model.Todo;

import java.util.Date;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Todo findByUsernameAndTitle(String username, String title);

    List<Todo> findByUsername(String username);

}
