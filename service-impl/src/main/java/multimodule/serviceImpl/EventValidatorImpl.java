package multimodule.serviceImpl;

import lombok.RequiredArgsConstructor;
import multimodule.api.EventValidator;
import multimodule.dto.EventDto;
import multimodule.exception.EventAlreadyExistException;
import multimodule.exception.EventNotFoundException;
import multimodule.repository.EventRepository;

import java.util.function.Predicate;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class EventValidatorImpl implements EventValidator {

    private final EventRepository eventRepository;

    public void validateForUpdate(EventDto eventDto) {
        validate(eventDto, this::isPresent, EventNotFoundException::new);
    }

    @Override
    public void validateForCreate(EventDto eventDto) {
        validate(eventDto, e -> !isPresent(e), EventAlreadyExistException::new);
    }

    private <T> void validate(T value,
                              Predicate<T> valueVerifier,
                              Supplier<RuntimeException> exceptionSupplier) {
        if (!valueVerifier.test(value)) {
            throw exceptionSupplier.get();
        }
    }

    private boolean isPresent(EventDto eventDto) {
        return eventRepository.existsById(eventDto.getId());
    }

}
