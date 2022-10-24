package tr.com.yavuzduran.pim.eventscheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.eventscheduler.dto.EventDto;
import tr.com.yavuzduran.pim.eventscheduler.model.Event;
import tr.com.yavuzduran.pim.eventscheduler.repository.EventRepository;
import tr.com.yavuzduran.pim.eventscheduler.util.EventModelConverter;
import tr.com.yavuzduran.pim.eventscheduler.util.TimeFormatter;
import tr.com.yavuzduran.pim.exceptionhandler.exception.EventParseException;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventSchedulerServiceImp implements IEventSchedulerService {

    private final EventRepository repository;

    @Override
    public void createEvent(EventDto eventDto) throws EventParseException {
        try {
            repository.save(EventModelConverter.convert(eventDto));
        } catch (ParseException e) {
            throw new EventParseException("", e);
        }
    }

    @Override
    public void updateEvent(String eventTitle, String date, EventDto eventDto) throws EventParseException {
        try {
            Date date1 = TimeFormatter.parse(date);
            Event event = repository.findByUsernameAndTitleAndStartDate(
                    (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), eventTitle, date1);
            if (event != null) {
                Date date2 = TimeFormatter.parse(eventDto.getStartDate());
                Date date3 = TimeFormatter.parse(eventDto.getEndDate());
                event.setComment(eventDto.getComment());
                event.setTitle(eventDto.getTitle());
                event.setReminder(eventDto.getReminder());
                event.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                event.setEndDate(date3);
                event.setStartDate(date2);
                event.setRepeatPeriod(eventDto.getRepeatPeriod());
                event.setWholeDay(eventDto.isWholeDay());
                repository.save(event);
            }
        } catch (ParseException e) {
            throw new EventParseException("", e);
        }

    }

    @Override
    public void removeEvent(String eventTitle, String date) throws EventParseException {
        try {
            Date date1 = TimeFormatter.parse(date);
            Event event = repository.findByUsernameAndTitleAndStartDate(
                    (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), eventTitle, date1);
            if (event != null) {
                repository.delete(event);
            }
        } catch (ParseException e) {
            throw new EventParseException("", e);
        }

    }

    @Override
    public List<EventDto> getEvents() {
        return EventModelConverter.convert(repository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    @Override
    public EventDto getEvent(String eventTitle, String date) throws EventParseException {
        try {
            Date date1 = TimeFormatter.parse(date);
            return EventModelConverter.convert(repository.findByUsernameAndTitleAndStartDate(
                    (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), eventTitle, date1));
        } catch (ParseException e) {
            throw new EventParseException("", e);
        }
    }
}
