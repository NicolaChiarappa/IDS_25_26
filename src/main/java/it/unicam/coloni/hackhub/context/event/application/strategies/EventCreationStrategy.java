package it.unicam.coloni.hackhub.context.event.application.strategies;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.CreateEventRequest;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.model.StaffMember;

public interface EventCreationStrategy {
    boolean canHandle(CreateEventRequest request);

    Event create(CreateEventRequest request, StaffMember organizer);
}
