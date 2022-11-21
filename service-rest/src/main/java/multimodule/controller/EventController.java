package multimodule.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import multimodule.dto.EventDto;
import multimodule.serviceImpl.EventServiceImpl;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
@Api(tags = "API description for SWAGGER documentation Event")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class EventController {

    private final EventServiceImpl eventService;

    @ApiOperation("create Event")
    @ApiParam("eventDto object with required fields")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        log.info("creating eventDto {} starts ", eventDto);
        EventDto createdEvent = eventService.createEvent(eventDto);
        Link selfLink = getSelfEventLink(createdEvent);
        Link eventsLink = getAllEventsLink();
        createdEvent.add(selfLink, eventsLink);
        return createdEvent;
    }

    @ApiOperation("update Event")
    @ApiParam("event object with required fields")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public EventDto updateEvent(@RequestBody EventDto eventDto) {
        log.info("updating event {} starts ", eventDto);
        EventDto updatedEvent = eventService.updateEvent(eventDto);
        Link selfLink = getSelfEventLink(eventDto);
        Link eventsLink = getAllEventsLink();
        updatedEvent.add(selfLink, eventsLink);
        return updatedEvent;
    }

    @ApiOperation("get Event by ID")
    @ApiParam("event id")
    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable("id") long id) {
        log.info("call get event by id {} in controller", id);
        EventDto persistedEvent = eventService.getEventById(id);
        Link selfLink = linkTo(methodOn(EventController.class).getEventById(id)).withSelfRel();
        persistedEvent.add(selfLink, getAllEventsLink());
        return persistedEvent;
    }

    @ApiOperation("get all events")
    @GetMapping("/events")
    public CollectionModel<EventDto> getAllEvents() {
        log.info("call get all events in controller");
        List<EventDto> events = eventService.getAllEvents();
        addEventSelfLink(events);
        return CollectionModel.of(events, getAllEventsLink());
    }

    @ApiOperation("get all events with specific title")
    @GetMapping("/events/{title}")
    public CollectionModel<EventDto> getEventsByTitle(@PathVariable String title) {
        log.info("call get all events in controller");
        List<EventDto> events = eventService.getAllEventsByTitle(title);
        addEventSelfLink(events);
        Link eventsLink = getAllEventsLink();
        Link eventsSelfLinkByTitle = linkTo(methodOn(EventController.class)).slash(title).withSelfRel();
        return CollectionModel.of(events, eventsLink, eventsSelfLinkByTitle);
    }

    @ApiOperation("delete event")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteEvent(@RequestBody EventDto eventDto) {
        log.info("call delete event {} in controller", eventDto);
        eventService.deleteEvent(eventDto);
    }

    private static void addEventSelfLink(List<EventDto> events) {
        for (EventDto event : events) {
            long eventId = event.getId();
            Link selfLink = linkTo(EventController.class).slash(eventId).withSelfRel();
            event.add(selfLink);
        }
    }

    private static Link getAllEventsLink() {
        return linkTo(methodOn(EventController.class).getAllEvents()).withRel("/events");
    }

    private static Link getSelfEventLink(EventDto eventDto) {
        return linkTo(methodOn(EventController.class).getEventById(eventDto.getId())).withSelfRel();
    }

}
