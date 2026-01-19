package it.unicam.coloni.hackhub.context.event.application.service;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.CreateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.EventDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.mapper.EventMapper;
import it.unicam.coloni.hackhub.context.event.application.strategies.EventCreationStrategy;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.model.StaffMember;
import it.unicam.coloni.hackhub.context.event.domain.model.UserRole;
import it.unicam.coloni.hackhub.context.event.domain.repository.EventRepository;
import it.unicam.coloni.hackhub.context.identity.domain.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventServiceImpl implements EventService{

    @Autowired
    EventMapper eventMapper;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private List<EventCreationStrategy> strategies;


    @Override
    @Transactional
    public EventDto createEvent(CreateEventRequest request) {
        StaffMember organizer = new StaffMember(1L, "Organizer", UserRole.ORGANIZER);
        EventCreationStrategy strategy = strategies.stream()
                .filter(s-> s.canHandle(request))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("No strategy found"));
        Event event = strategy.create(request, organizer);
        return eventMapper.toDto(event);
    }


    @Override
    public EventDto deleteEvent(Long id) {
        Event fetchedEvent = eventRepository.findById(id).orElseThrow(NullPointerException::new);
        fetchedEvent.delete();
        return eventMapper.toDto(eventRepository.save(fetchedEvent));
    }


    @Override
    public EventDto updateEvent(UpdateEventRequest request) {
        Long id = request.getId();
        Event event = eventRepository.findById(id).orElseThrow();
        eventMapper.fromUpdate(event, request);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDto(savedEvent);
    }



}
