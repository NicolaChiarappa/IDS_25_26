package it.unicam.coloni.hackhub.context.event.application.service;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.CreateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.EventDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.mapper.EventMapper;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.model.User;
import it.unicam.coloni.hackhub.context.event.domain.model.UserRole;
import it.unicam.coloni.hackhub.context.event.domain.repository.AssignmentRepository;
import it.unicam.coloni.hackhub.context.event.domain.repository.EventRepository;
import it.unicam.coloni.hackhub.context.event.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventServiceImpl implements EventService{

    @Autowired
    EventMapper eventMapper;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AssignmentRepository assignmentRepository;


    @Override
    @Transactional
    public EventDto createEvent(CreateEventRequest request) {
        User user = userRepository.save(new User("Organizer", UserRole.ORGANIZER ));
        Event event = Event.fromOrganizer(user);
        Event settedUpEvent = eventMapper.toEvent(request, event);
        Event savedEvent = eventRepository.save(settedUpEvent);
        return eventMapper.toDto(savedEvent);
    }


    @Override
    public EventDto deleteEvent(Long id) {
        Event fetchedEvent = eventRepository.findById(id).orElseThrow(NullPointerException::new);
        fetchedEvent.delete();
        return eventMapper.toDto(eventRepository.save(fetchedEvent));
    }



    public EventDto updateEvent(UpdateEventRequest request) {
        Long id = request.getId();
        Event event = eventRepository.findById(id).orElseThrow();
        eventMapper.fromUpdate(event, request);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDto(savedEvent);
    }




}
