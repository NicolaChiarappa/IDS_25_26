package it.unicam.coloni.hackhub.context.event.application.strategies;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.CreateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.EventCreationRequest;
import it.unicam.coloni.hackhub.context.event.application.mapper.EventMapper;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.model.EventStatus;
import it.unicam.coloni.hackhub.context.event.domain.model.StaffMember;
import it.unicam.coloni.hackhub.context.event.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizerOnlyStrategy implements EventCreationStrategy{

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventMapper eventMapper;

    @Override
    public boolean canHandle(EventCreationRequest request) {
        return request instanceof CreateEventRequest;
    }

    @Override
    public Event create(EventCreationRequest request, StaffMember organizer) {
        Event event = Event.fromOrganizer(organizer);
        Event settedUpEvent = eventMapper.toEvent((CreateEventRequest) request, event);
        settedUpEvent.openSubscription();
        return eventRepository.save(settedUpEvent);

    }
}
