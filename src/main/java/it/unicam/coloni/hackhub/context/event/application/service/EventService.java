package it.unicam.coloni.hackhub.context.event.application.service;

import it.unicam.coloni.hackhub.context.event.application.dto.EventDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.EventCreationRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.EventDetailsDto;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    EventDto createEvent(EventCreationRequest request);

    EventDto fetchById(Long id);

    EventDto deleteEvent(Long id);

    EventDto updateEvent(UpdateEventRequest request);

    EventDto closeSubscriptions(Long id);

    EventDto startEvent(Long id);

    EventDto stopEvent(Long id);

    EventDto closeEvent(Long id);

    EventDto stopValuating(Long id);

    EventDetailsDto getDetails(Long id);

}
