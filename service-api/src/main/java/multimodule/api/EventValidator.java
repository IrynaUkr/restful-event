package multimodule.api;

import multimodule.dto.EventDto;

public interface EventValidator {

    void validateForUpdate(EventDto eventDto);

    void validateForCreate(EventDto eventDto);
}
