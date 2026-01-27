package it.unicam.coloni.hackhub.context.event.application.service;

import it.unicam.coloni.hackhub.context.event.application.dto.EventDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.EventCreationRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.mapper.AssignmentMapper;
import it.unicam.coloni.hackhub.context.event.application.mapper.EventMapper;
import it.unicam.coloni.hackhub.context.event.application.strategies.EventCreationStrategy;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.model.StaffMember;
import it.unicam.coloni.hackhub.context.identity.application.service.AuthService;
import it.unicam.coloni.hackhub.context.event.domain.repository.EventRepository;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
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

    @Autowired
    private AuthService authService;

    private AssignmentMapper assignmentMapper;


    @Override
    @Transactional
    public EventDto createEvent(EventCreationRequest request) {

        User user = authService.getLoggedUser();

        StaffMember organizer = new StaffMember(user.getId(), user.getUsername(), user.getRole());
        EventCreationStrategy strategy = strategies.stream()
                .filter(s-> s.canHandle(request))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("No strategy found"));
        Event event = strategy.create(request, organizer);
        return eventMapper.toDto(event);
    }

    @Override
    public EventDto fetchById(Long id) {
        return eventMapper.toDto(eventRepository.findById(id).orElseThrow());
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

    @Override
    public EventDto closeSubscriptions(Long id) {
        Event fetchedEvent = eventRepository.findById(id).orElseThrow(NullPointerException::new);
        fetchedEvent.closeSubscriptions();
        return eventMapper.toDto(eventRepository.save(fetchedEvent));
    }

    @Override
    public EventDto startEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow();
        event.startEvent();
        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventDto stopEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow();
        event.stopEvent();
        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventDto closeEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow();
        event.closeEvent();
        return eventMapper.toDto(eventRepository.save(event));
    }




}
