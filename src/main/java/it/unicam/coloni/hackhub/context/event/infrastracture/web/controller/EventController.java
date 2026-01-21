package it.unicam.coloni.hackhub.context.event.infrastracture.web.controller;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.CreateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.EventDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    public EventDto createEvent(@RequestBody CreateEventRequest request){
        return eventService.createEvent(request);
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @DeleteMapping
    public EventDto deleteEvent(@RequestParam Long id){
        return eventService.deleteEvent(id);
    }


    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping
    public EventDto updateEvent(@RequestBody UpdateEventRequest request){
        return eventService.updateEvent(request);
    }


}
