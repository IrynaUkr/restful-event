package multimodule.config;

import lombok.RequiredArgsConstructor;
import multimodule.api.EventValidator;
import multimodule.repository.EventRepository;
import multimodule.serviceImpl.EventValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {

     private final EventRepository eventRepository;

    @Bean
    public EventValidator eventValidator(){
        return new EventValidatorImpl(eventRepository);
    }
}
