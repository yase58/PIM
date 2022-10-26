package tr.com.yavuzduran.pim.eventscheduler.util;

import org.springframework.security.core.context.SecurityContextHolder;
import tr.com.yavuzduran.pim.eventscheduler.dto.EventDto;
import tr.com.yavuzduran.pim.eventscheduler.model.Event;
import tr.com.yavuzduran.pim.exceptionhandler.exception.eventscheduler.EventParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventModelConverter {

    private EventModelConverter() {
    }

    public static Event convert(EventDto eventDto) throws EventParseException {
        Date startDate = TimeFormatter.parse(eventDto.getStartDate());
        Date endDate = TimeFormatter.parse(eventDto.getEndDate());
        return Event.builder()
                .comment(eventDto.getComment())
                .endDate(endDate)
                .startDate(startDate)
                .username((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .title(eventDto.getTitle())
                .reminder(eventDto.getReminder())
                .repeatPeriod(eventDto.getRepeatPeriod())
                .isWholeDay(eventDto.isWholeDay())
                .build();
    }

    public static EventDto convert(Event event) {
        String startDate = TimeFormatter.format(event.getStartDate());
        String endDate = TimeFormatter.format(event.getEndDate());
        return EventDto.builder()
                .comment(event.getComment())
                .endDate(endDate)
                .startDate(startDate)
                .title(event.getTitle())
                .reminder(event.getReminder())
                .repeatPeriod(event.getRepeatPeriod())
                .isWholeDay(event.isWholeDay())
                .build();
    }

    public static List<EventDto> convert(List<Event> eventList) {
        List<EventDto> eventDtoList = new ArrayList<>();
        if (eventList != null && !eventList.isEmpty()) {
            for (Event event : eventList) {
                eventDtoList.add(convert(event));
            }
        }
        return eventDtoList;
    }
}
