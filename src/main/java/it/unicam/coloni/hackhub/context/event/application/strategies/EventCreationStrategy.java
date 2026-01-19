package it.unicam.coloni.hackhub.context.event.application.strategies;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.EventCreationRequest;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.model.StaffMember;

public interface EventCreationStrategy {
    boolean canHandle(EventCreationRequest request);

    Event create(EventCreationRequest request, StaffMember organizer);
}
