package multimodule.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import multimodule.dto.EventDto;
import multimodule.model.Event;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-21T12:23:27+0000",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventDto mapToEventDto(Event event) {
        if ( event == null ) {
            return null;
        }

        EventDto.EventDtoBuilder eventDto = EventDto.builder();

        eventDto.id( event.getId() );
        eventDto.title( event.getTitle() );
        eventDto.place( event.getPlace() );
        eventDto.speaker( event.getSpeaker() );
        eventDto.eventType( event.getEventType() );
        eventDto.dateTime( event.getDateTime() );

        return eventDto.build();
    }

    @Override
    public Event mapToEvent(EventDto eventDto) {
        if ( eventDto == null ) {
            return null;
        }

        Event.EventBuilder event = Event.builder();

        event.id( eventDto.getId() );
        event.title( eventDto.getTitle() );
        event.place( eventDto.getPlace() );
        event.speaker( eventDto.getSpeaker() );
        event.eventType( eventDto.getEventType() );
        event.dateTime( eventDto.getDateTime() );

        return event.build();
    }

    @Override
    public List<EventDto> mapToEventsDto(List<Event> events) {
        if ( events == null ) {
            return null;
        }

        List<EventDto> list = new ArrayList<EventDto>( events.size() );
        for ( Event event : events ) {
            list.add( mapToEventDto( event ) );
        }

        return list;
    }
}
