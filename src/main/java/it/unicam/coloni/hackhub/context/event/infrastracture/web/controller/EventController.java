
package it.unicam.coloni.hackhub.context.event.infrastracture.web.controller;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.SimpleCreationRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.WithStaffCreationRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.EventDto;
import it.unicam.coloni.hackhub.context.event.application.dto.EventDetailsDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.service.EventService;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponse;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    ApiResponseFactory responseFactory;

    @GetMapping("/{id}")
    public ApiResponse<EventDto> fetchById(@PathVariable Long id) {
        return responseFactory.createSuccessResponse("Event fetched successfully", eventService.fetchById(id));
    }

    @GetMapping("/{id}/details")
    public ApiResponse<EventDetailsDto> getDetails(@PathVariable Long id) {
        return responseFactory.createSuccessResponse("Event details fetched successfully", eventService.getDetails(id));
    }

    @PostMapping("/withStaff")
    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    public ApiResponse<EventDto> createEvent(@RequestBody WithStaffCreationRequest request) {
        return responseFactory.createSuccessResponse("Event created successfully", eventService.createEvent(request));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    public ApiResponse<EventDto> createEvent(@RequestBody SimpleCreationRequest request) {
        return responseFactory.createSuccessResponse("Event created successfully", eventService.createEvent(request));
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @DeleteMapping("/{id}")
    public ApiResponse<EventDto> deleteEvent(@PathVariable Long id) {
        return responseFactory.createSuccessResponse("Event deleted successfully", eventService.deleteEvent(id));
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping
    public ApiResponse<EventDto> updateEvent(@RequestBody UpdateEventRequest request) {
        return responseFactory.createSuccessResponse("Event updated successfully", eventService.updateEvent(request));
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping("/{id}/close-sub")
    public ApiResponse<EventDto> closeSubscriptions(@PathVariable Long id) {
        return responseFactory.createSuccessResponse("Subscriptions closed successfully",
                eventService.closeSubscriptions(id));
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping("/{id}/start")
    public ApiResponse<EventDto> startEvent(@PathVariable Long id) {
        return responseFactory.createSuccessResponse("Event started successfully", eventService.startEvent(id));
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping("/{id}/stop")
    public ApiResponse<EventDto> stopEvent(@PathVariable Long id) {
        return responseFactory.createSuccessResponse("Event stopped successfully", eventService.stopEvent(id));
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping("/{id}/stopVals")
    public ApiResponse<EventDto> stopValuating(@PathVariable Long id) {
        return responseFactory.createSuccessResponse("Event valuations closed successfully", eventService.stopValuating(id));
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PatchMapping("/{id}/close")
    public ApiResponse<EventDto> closeEvent(@PathVariable Long id) {
        return responseFactory.createSuccessResponse("Event closed successfully", eventService.closeEvent(id));
    }
}
