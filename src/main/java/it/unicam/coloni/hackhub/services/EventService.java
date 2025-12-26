package it.unicam.coloni.hackhub.services;

import it.unicam.coloni.hackhub.dto.requests.CreateEventRequest;
import it.unicam.coloni.hackhub.dto.EventDto;
import it.unicam.coloni.hackhub.dto.requests.UpdateEventRequest;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    EventDto createEvent(CreateEventRequest request);


    EventDto deleteEvent(Long id);

    EventDto updateEvent(UpdateEventRequest request);

//TODO:
//  public void publishWinner(WinnerRequest request);

}
