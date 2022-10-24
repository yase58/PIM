package tr.com.yavuzduran.pim.eventscheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.eventscheduler.model.Event;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByUsernameAndTitleAndStartDate(String username, String title, Date startDate);

    List<Event> findByUsername(String username);
}
