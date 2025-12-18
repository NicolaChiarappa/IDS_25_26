package it.unicam.coloni.hackhub.controller;

import it.unicam.coloni.hackhub.dto.CreateEventRequest;
import it.unicam.coloni.hackhub.dto.EventDto;
import it.unicam.coloni.hackhub.services.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventServiceImpl eventService;

    @PostMapping
    public EventDto createEvent(@RequestBody CreateEventRequest request){
        return eventService.createEvent(request);
    }

}
