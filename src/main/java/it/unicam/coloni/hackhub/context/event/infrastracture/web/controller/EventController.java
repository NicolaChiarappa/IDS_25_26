
package it.unicam.coloni.hackhub.context.event.infrastracture.web.controller;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.SimpleCreationRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.WithStaffCreationRequest;
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

    @GetMapping("/{id}")
    public EventDto fetchById(@PathVariable Long id){
        return eventService.fetchById(id);
    }

    @PostMapping("/withStaff")
    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    public EventDto createEvent(@RequestBody WithStaffCreationRequest request) {
        return eventService.createEvent(request);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    public EventDto createEvent(@RequestBody SimpleCreationRequest request) {
        return eventService.createEvent(request);
    }


    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @DeleteMapping("/{id}")
    public EventDto deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping
    public EventDto updateEvent(@RequestBody UpdateEventRequest request) {
        return eventService.updateEvent(request);
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping("/{id}/close-sub")
    public EventDto closeSubscriptions(@PathVariable Long id) {
        return eventService.closeSubscriptions(id);
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping("/{id}/start")
    public EventDto startEvent(@PathVariable Long id) {
        return eventService.startEvent(id);
    }


    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping("/{id}/stop")
    public EventDto stopEvent(@PathVariable Long id) {
        return eventService.stopEvent(id);
    }


    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping("/{id}/close")
    public EventDto closeEvent(@PathVariable Long id) {
        return eventService.closeEvent(id);
    }
}


