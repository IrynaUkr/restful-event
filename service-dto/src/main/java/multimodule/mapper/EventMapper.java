package multimodule.mapper;

import multimodule.dto.EventDto;
import multimodule.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventDto mapToEventDto(Event event);

    Event mapToEvent(EventDto eventDto);

    List<EventDto> mapToEventsDto(List<Event> events);

}
