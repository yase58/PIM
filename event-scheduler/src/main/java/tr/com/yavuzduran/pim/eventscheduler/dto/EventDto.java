package tr.com.yavuzduran.pim.eventscheduler.dto;

import lombok.Builder;
import lombok.Data;
import tr.com.yavuzduran.pim.eventscheduler.enumaration.EnReminder;
import tr.com.yavuzduran.pim.eventscheduler.enumaration.EnRepeatPeriod;

@Data
@Builder
public class EventDto {

    private String title;

    private boolean isWholeDay;

    private String startDate;

    private String endDate;

    private EnRepeatPeriod repeatPeriod;

    private EnReminder reminder;

    private String comment;

}
