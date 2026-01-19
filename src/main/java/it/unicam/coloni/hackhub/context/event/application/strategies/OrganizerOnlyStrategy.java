package it.unicam.coloni.hackhub.context.event.application.strategies;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.CreateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.mapper.EventMapper;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
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
    public boolean canHandle(CreateEventRequest request) {
        return request.getJudgeId()==null && (request.getMentorsId()==null || request.getMentorsId().isEmpty());
    }

    @Override
    public Event create(CreateEventRequest request, StaffMember organizer) {
        Event event = Event.fromOrganizer(organizer);
        Event settedUpEvent = eventMapper.toEvent(request, event);
        return eventRepository.save(settedUpEvent);

    }
}
