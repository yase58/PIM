package tr.com.yavuzduran.pim.eventscheduler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.yavuzduran.pim.common.controller.ICrudController;
import tr.com.yavuzduran.pim.eventscheduler.dto.EventDto;
import tr.com.yavuzduran.pim.eventscheduler.dto.TitleAndDate;
import tr.com.yavuzduran.pim.eventscheduler.service.EventSchedulerServiceImp;
import tr.com.yavuzduran.pim.exceptionhandler.exception.eventscheduler.*;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventSchedulerController extends ICrudController<EventDto, TitleAndDate> {

    private final EventSchedulerServiceImp service;

    @Override
    public ResponseEntity<Response> save(EventDto eventDto) throws EventParseException, EventIdentifierMissingException, EventAlreadyExistException {
        service.save(eventDto);
        return ResponseBuilder.createSuccess();
    }

    @Override
    @PutMapping("/{title}/{date}")
    public ResponseEntity<Response> update(TitleAndDate titleAndDate, EventDto eventDto) throws EventParseException, EventIdentifierMissingException, EventNotFoundException, EventAlreadyExistException, EventTitleOrDateNullException {
        service.update(titleAndDate, eventDto);
        return ResponseBuilder.createSuccess();
    }

    @Override
    @DeleteMapping("/{title}/{date}")
    public ResponseEntity<Response> remove(TitleAndDate titleAndDate) throws EventParseException, EventNotFoundException, EventTitleOrDateNullException {
        service.remove(titleAndDate);
        return ResponseBuilder.createSuccess();
    }

    @Override
    public ResponseEntity<List<EventDto>> getAllData() {
        return ResponseEntity.ok(service.getAllData());
    }

    @Override
    @GetMapping("/{title}/{date}")
    public ResponseEntity<EventDto> getData(TitleAndDate titleAndDate) throws EventParseException, EventNotFoundException, EventTitleOrDateNullException {
        return ResponseEntity.ok(service.getData(titleAndDate));
    }


}
