package multimodule.serviceImpl;

import multimodule.dto.EventDto;
import multimodule.exception.EventAlreadyExistException;
import multimodule.exception.EventNotFoundException;
import multimodule.mapper.EventMapper;
import multimodule.model.Event;
import multimodule.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {
    public static final String EVENT_TEST_TITLE = "event test title";

    public static final long EVENT_TEST_ID = 1L;

    Event testEvent;

    EventDto testEventDto;

    List<Event> testEventList;
    List<EventDto> testEventDtoList;

    @BeforeEach
    void setUp() {
        testEvent = new Event();
        testEvent.setTitle(EVENT_TEST_TITLE);
        testEvent.setId(EVENT_TEST_ID);
        testEventDto = EventMapper.INSTANCE.mapToEventDto(testEvent);
        testEventList = new ArrayList<>();
        testEventList.add(testEvent);
        testEventDtoList = EventMapper.INSTANCE.mapToEventsDto(testEventList);
    }

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventRepository eventRepository;

    @Test
    void shouldReturnValidEventWhenEventExistTest() {
        when(eventRepository.findById(EVENT_TEST_ID)).thenReturn(Optional.of(testEvent));

        EventDto eventDto = eventService.getEventById(EVENT_TEST_ID);
        Event actualEvent = EventMapper.INSTANCE.mapToEvent(eventDto);

        assertThat(testEvent, equalTo(actualEvent));
    }

    @Test
    void shouldThrowEventNotFoundExceptionIfEventIsNotExistTest() {
        when(eventRepository.findById(EVENT_TEST_ID)).thenReturn(Optional.empty());
        assertThrows(EventNotFoundException.class,
                () -> eventService.getEventById(EVENT_TEST_ID));
    }

    @Test
    void shouldCreateValidEventTest() {
        when(eventRepository.existsById(EVENT_TEST_ID)).thenReturn(false);
        when(eventRepository.save(testEvent)).thenReturn(testEvent);


        EventDto actualEventDto = eventService.createEvent(testEventDto);

        assertThat(testEventDto, equalTo(actualEventDto));
    }

    @Test
    void shouldThrowEventAlreadyExistExceptionIfCreatingEventPresentsTest() {
        when(eventRepository.existsById(any())).thenReturn(true);

        assertThrows(EventAlreadyExistException.class,
                () -> eventService.createEvent(testEventDto));
    }

    @Test
    void shouldBeUpdatedByIdTest() {
        when(eventRepository.save(testEvent)).thenReturn(testEvent);

        EventDto actualEventDto = eventService.updateEvent(testEventDto);
        Event actualEvent = EventMapper.INSTANCE.mapToEvent(actualEventDto);

        assertThat(actualEvent, samePropertyValuesAs(testEvent));
        assertThat(actualEvent, hasProperty("id", equalTo(EVENT_TEST_ID)));
    }

    @Test
    void deleteById() {
        eventService.deleteEvent(testEventDto);

        verify(eventRepository).delete(testEvent);
    }

    @Test
    void shouldGetEventsByTitleTest() {
        when(eventRepository.findAllByTitle(EVENT_TEST_TITLE)).thenReturn(testEventList);

        List<EventDto> allEventsByTitle = eventService.getAllEventsByTitle(EVENT_TEST_TITLE);

        for (EventDto ev : allEventsByTitle) {
            assertThat(ev, hasProperty("title", equalTo(EVENT_TEST_TITLE)));
        }
        assertThat(testEventList, hasSize(1));
    }

    @Test
    void shouldGetAllEventsTest() {
        when(eventRepository.findAll()).thenReturn(testEventList);

        List<EventDto> allEvents = eventService.getAllEvents();

        assertThat(allEvents, hasSize(1));
    }

}
