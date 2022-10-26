package tr.com.yavuzduran.pim.eventscheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.common.service.ICrudService;
import tr.com.yavuzduran.pim.eventscheduler.dto.EventDto;
import tr.com.yavuzduran.pim.eventscheduler.dto.TitleAndDate;
import tr.com.yavuzduran.pim.eventscheduler.model.Event;
import tr.com.yavuzduran.pim.eventscheduler.repository.EventRepository;
import tr.com.yavuzduran.pim.eventscheduler.util.EventModelConverter;
import tr.com.yavuzduran.pim.eventscheduler.util.TimeFormatter;
import tr.com.yavuzduran.pim.exceptionhandler.exception.eventscheduler.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventSchedulerServiceImp implements ICrudService<EventDto, TitleAndDate> {

    private final EventRepository repository;

    @Override
    public void save(EventDto eventDto) throws EventParseException, EventIdentifierMissingException, EventAlreadyExistException {
        dtoCheck(eventDto);
        repository.save(EventModelConverter.convert(eventDto));
    }

    @Override
    public void update(TitleAndDate titleAndDate, EventDto eventDto) throws EventParseException, EventIdentifierMissingException, EventAlreadyExistException, EventNotFoundException, EventTitleOrDateNullException {
        dtoCheck(eventDto);
        Event event = uniqueKeyCheck(titleAndDate);
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

    @Override
    public void remove(TitleAndDate titleAndDate) throws EventParseException, EventNotFoundException, EventTitleOrDateNullException {
        Event event = uniqueKeyCheck(titleAndDate);
        repository.delete(event);
    }

    @Override
    public List<EventDto> getAllData() {
        return EventModelConverter.convert(repository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    @Override
    public EventDto getData(TitleAndDate titleAndDate) throws EventParseException, EventNotFoundException, EventTitleOrDateNullException {
        Event event = uniqueKeyCheck(titleAndDate);
        return EventModelConverter.convert(event);
    }

    private void dtoCheck(EventDto eventDto) throws EventIdentifierMissingException, EventParseException, EventAlreadyExistException {
        if (eventDto == null ||
                eventDto.getTitle() == null || eventDto.getTitle().equals("") ||
                eventDto.getStartDate() == null || eventDto.getStartDate().equals("") ||
                eventDto.getRepeatPeriod() == null ||
                eventDto.getReminder() == null) {
            throw new EventIdentifierMissingException("Missing one or more elements(Title, Start Date, Repeat Period or Reminder)!");
        }
        Date startDate = TimeFormatter.parse(eventDto.getStartDate());
        Event event = repository.findByUsernameAndTitleAndStartDate(
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), eventDto.getTitle(), startDate);
        if (event != null) {
            throw new EventAlreadyExistException("Event already exist!");
        }
    }


    private Event uniqueKeyCheck(TitleAndDate titleAndDate) throws EventTitleOrDateNullException, EventParseException, EventNotFoundException {
        if (titleAndDate == null ||
                titleAndDate.getDate() == null || titleAndDate.getDate().equals("") ||
                titleAndDate.getTitle() == null || titleAndDate.getTitle().equals("")) {
            throw new EventTitleOrDateNullException("Title or Date does not be null!");
        }
        Date startDate = TimeFormatter.parse(titleAndDate.getDate());
        Event event = repository.findByUsernameAndTitleAndStartDate(
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), titleAndDate.getTitle(), startDate);
        if (event == null) {
            throw new EventNotFoundException("Event Not Found! Title : " + titleAndDate.getTitle() + "  Date : " + startDate);
        }
        return event;
    }
}
