package it.unicam.coloni.hackhub.services;

import it.unicam.coloni.hackhub.dto.CreateEventRequest;
import it.unicam.coloni.hackhub.dto.EventDto;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    EventDto createEvent(CreateEventRequest request);


    EventDto deleteEvent(Long id);
//
//    public EventDto updateEvent(UpdateEventRequest request);
//
//    public TeamDto publishWinner(WinnerRequest request);

}
