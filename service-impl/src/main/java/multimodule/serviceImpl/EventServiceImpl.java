package multimodule.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import multimodule.api.EventService;
import multimodule.api.EventValidator;
import multimodule.dto.EventDto;
import multimodule.exception.EventNotFoundException;
import multimodule.mapper.EventMapper;
import multimodule.model.Event;
import multimodule.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final EventValidator eventValidator;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        log.info("eventService method createEvent {} was called", eventDto);
        Event event = EventMapper.INSTANCE.mapToEvent(eventDto);
        eventValidator.validateForCreate(eventDto);
        Event savedEvent = eventRepository.save(event);
        log.info("eventDto was created");
        return EventMapper.INSTANCE.mapToEventDto(savedEvent);
    }

    @Override
    public EventDto updateEvent(EventDto eventDto) {
        log.info("eventService method updateEvent {} was called", eventDto);
        eventValidator.validateForUpdate(eventDto);
        Event event = EventMapper.INSTANCE.mapToEvent(eventDto);
        Event saved = eventRepository.save(event);
        return EventMapper.INSTANCE.mapToEventDto(saved);
    }

    @Override
    public EventDto getEventById(long id) {
        log.info("eventService method getEventById {} was called", id);
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("event not found"));
        return EventMapper.INSTANCE.mapToEventDto(event);
    }

    @Override
    public void deleteEvent(EventDto eventDto) {
        log.info("eventService method delete event {} was called", eventDto);
        Event event = EventMapper.INSTANCE.mapToEvent(eventDto);
        eventRepository.delete(event);
    }

    @Override
    public List<EventDto> getAllEvents() {
        log.info("eventService method geAllEvents was called");
        List<Event> events = eventRepository.findAll();
        return EventMapper.INSTANCE.mapToEventsDto(events);
    }

    @Override
    public List<EventDto> getAllEventsByTitle(String title) {
        log.info("eventService method geEventByTitle  {} was called", title);
        List<Event> events = eventRepository.findAllByTitle(title);
        return EventMapper.INSTANCE.mapToEventsDto(events);
    }

}
