package it.unicam.coloni.hackhub.services;

import it.unicam.coloni.hackhub.dto.CreateEventRequest;
import it.unicam.coloni.hackhub.dto.EventDto;
import it.unicam.coloni.hackhub.mappers.EventMapper;
import it.unicam.coloni.hackhub.model.Assignment;
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
        //TODO:
        User user = userRepository.save(new User("Organizer", UserRole.ORGANIZER ));
        Event event = Event.fromOrganizer(user);


        Event newEvent = eventRepository.save(event);

        return eventMapper.from(newEvent);
    }

    //TODO:
//    @Override
//    public EventDto deleteEvent(DeleteEventRequest request) {
//        return null;
//    }
//
//    @Override
//    public EventDto updateEvent(UpdateEventRequest request) {
//        return null;
//    }
//
//    @Override
//    public TeamDto publishWinner(WinnerRequest request) {
//        return null;
//    }


    public EventDto getEventInfos(Long id){
        return eventMapper.from(eventRepository.getReferenceById(id));
    }

    @Transactional
    public Assignment getAssignment(Long id){
        return assignmentRepository.getReferenceById(id);
    }
}
