package multimodule.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventDto extends RepresentationModel<EventDto> {
    private long id;

    private String title;

    private int place;

    private String speaker;

    private String eventType;

    private LocalDateTime dateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EventDto eventDto = (EventDto) o;
        return id == eventDto.id && place == eventDto.place
                && Objects.equals(title, eventDto.title)
                && Objects.equals(speaker, eventDto.speaker)
                && Objects.equals(eventType, eventDto.eventType)
                && Objects.equals(dateTime, eventDto.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, title, place, speaker, eventType, dateTime);
    }

}
