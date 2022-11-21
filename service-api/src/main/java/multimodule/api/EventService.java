package multimodule.api;

import multimodule.dto.EventDto;

import java.util.List;

public interface EventService {

    EventDto createEvent(EventDto eventDto);

    EventDto updateEvent(EventDto eventDto);

    EventDto getEventById(long id);

    void deleteEvent(EventDto eventDto);

    List<EventDto> getAllEvents();

    List<EventDto> getAllEventsByTitle(String title);
}
