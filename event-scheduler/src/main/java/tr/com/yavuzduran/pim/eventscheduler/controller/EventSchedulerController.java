package tr.com.yavuzduran.pim.eventscheduler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.yavuzduran.pim.eventscheduler.dto.EventDto;
import tr.com.yavuzduran.pim.eventscheduler.service.IEventSchedulerService;
import tr.com.yavuzduran.pim.exceptionhandler.exception.EventParseException;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventSchedulerController {

    private final IEventSchedulerService service;

    @PostMapping
    public ResponseEntity<Response> createEvent(@RequestBody EventDto eventDto) throws EventParseException {
        service.createEvent(eventDto);
        return ResponseBuilder.createSuccess();
    }

    @PatchMapping("/{eventTitle}")
    public ResponseEntity<Response> updateEvent(@PathVariable String eventTitle, @RequestParam String date, @RequestBody EventDto eventDto) throws EventParseException {
        service.updateEvent(eventTitle, date, eventDto);
        return ResponseBuilder.createSuccess();
    }

    @DeleteMapping("/{eventTitle}")
    public ResponseEntity<Response> removeEvent(@PathVariable String eventTitle, @RequestParam String date) throws EventParseException {
        service.removeEvent(eventTitle, date);
        return ResponseBuilder.createSuccess();
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> getEvents() {
        return ResponseEntity.ok(service.getEvents());
    }

    @GetMapping("/{title}")
    public ResponseEntity<EventDto> getEvents(@PathVariable String title,@RequestParam String date) throws EventParseException {
        return ResponseEntity.ok(service.getEvent(title, date));
    }


}
