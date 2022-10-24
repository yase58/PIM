package tr.com.yavuzduran.pim.eventscheduler.service;

import tr.com.yavuzduran.pim.eventscheduler.dto.EventDto;
import tr.com.yavuzduran.pim.exceptionhandler.exception.EventParseException;

import java.util.List;

public interface IEventSchedulerService {

    void createEvent(EventDto eventDto) throws EventParseException;

    void updateEvent(String eventTitle, String date, EventDto eventDto) throws EventParseException;

    void removeEvent(String eventTitle, String date) throws EventParseException;

    List<EventDto> getEvents();

    EventDto getEvent(String eventTitle, String date) throws EventParseException;

}
