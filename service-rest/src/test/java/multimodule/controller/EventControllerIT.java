package multimodule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import multimodule.dto.EventDto;
import multimodule.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EventControllerIT {

    private static final long TEST_EVENT_ID = 104;

    private static final String TEST_EVENT_TITLE = "SWAGGER";

    public static final int PRESENT_EVENT_ID = 100;

    public static final String EVENT_TYPE = "new event";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    EventRepository eventRepository;

    @Test
    void shouldReturnAllEventsIT() throws Exception {
        mockMvc.perform(get("/api/v1/event/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnEventByIdIT() throws Exception {
        this.mockMvc
                .perform(get("/api/v1/event/{id}", TEST_EVENT_ID))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(TEST_EVENT_TITLE));
    }

    @Test
    void shouldDeletePresentEventIT() throws Exception {
        EventDto eventDto = new EventDto();
        eventDto.setId(PRESENT_EVENT_ID);
        mockMvc.perform(delete("/api/v1/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto))
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldCreateEventIT() throws Exception {
        EventDto eventDto = new EventDto();
        eventDto.setEventType(EVENT_TYPE);

        mockMvc.perform(post("/api/v1/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.eventType").value(EVENT_TYPE));
    }

    @Test
    void shouldUpdateEventIT() throws Exception {
        EventDto eventDto = new EventDto();
        eventDto.setId(PRESENT_EVENT_ID);
        eventDto.setEventType(EVENT_TYPE);

        mockMvc.perform(put("/api/v1/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.eventType").value(EVENT_TYPE));
    }

    @Test
    void shouldReturnEventsIT() throws Exception {
        mockMvc.perform(get("/api/v1/event/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
