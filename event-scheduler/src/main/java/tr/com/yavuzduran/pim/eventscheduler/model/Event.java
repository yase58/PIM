package tr.com.yavuzduran.pim.eventscheduler.model;

import lombok.*;
import tr.com.yavuzduran.pim.eventscheduler.dblistener.EventDatabaseListener;
import tr.com.yavuzduran.pim.eventscheduler.enumaration.EnReminder;
import tr.com.yavuzduran.pim.eventscheduler.enumaration.EnRepeatPeriod;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table
@EntityListeners(EventDatabaseListener.class)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private String title;

    private boolean isWholeDay;

    private Date startDate;

    private Date endDate;

    private EnRepeatPeriod repeatPeriod;

    private EnReminder reminder;

    private String comment;

}
