package it.unicam.coloni.hackhub.services;

import it.unicam.coloni.hackhub.dto.requests.CreateEventRequest;
import it.unicam.coloni.hackhub.dto.EventDto;
import it.unicam.coloni.hackhub.dto.requests.UpdateEventRequest;
import it.unicam.coloni.hackhub.mappers.EventMapper;
import it.unicam.coloni.hackhub.model.Event;
import it.unicam.coloni.hackhub.model.User;
import it.unicam.coloni.hackhub.model.UserRole;
import it.unicam.coloni.hackhub.repository.AssignmentRepository;
import it.unicam.coloni.hackhub.repository.EventRepository;
import it.unicam.coloni.hackhub.repository.UserRepository;
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
        Event settedUpEvent = eventMapper.toDto(request, event);
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
