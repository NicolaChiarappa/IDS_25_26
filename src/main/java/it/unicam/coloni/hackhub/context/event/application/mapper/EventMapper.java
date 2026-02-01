package it.unicam.coloni.hackhub.context.event.application.mapper;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.SimpleCreationRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.WithStaffCreationRequest;
import it.unicam.coloni.hackhub.context.event.domain.model.*;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.EventDto;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {
        DateRange.class })
public abstract class EventMapper {

    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "runningPeriod", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Event toEvent(SimpleCreationRequest request, @MappingTarget Event event);

    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "runningPeriod", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Event toEvent(WithStaffCreationRequest request, @MappingTarget Event event);

    @Mapping(target = "eventId", source = "id")
    public abstract EventDto toDto(Event event);

    @Mapping(target = "modifiedAt", source = ".", qualifiedByName = "getCurrentTime")
    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "runningPeriod", ignore = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract void fromUpdate(@MappingTarget Event event, UpdateEventRequest dto);



    /**
     * Sets the {@link Event}'s runningPeriod property from
     * {@link WithStaffCreationRequest}'s startDate and endDate
     * 
     * @param event   the mapping target
     * @param request the mapping source
     */
    @AfterMapping
    void setDateRange(@MappingTarget Event event, WithStaffCreationRequest request) {
        DateRange dateRange = DateRange.fromDates(request.getStartDate(), request.getEndDate());
        event.setRunningPeriod(dateRange);
        event.setCreatedAt(LocalDateTime.now());
    }
    @AfterMapping
    void setDateRange(@MappingTarget Event event, SimpleCreationRequest request) {
        DateRange dateRange = DateRange.fromDates(request.getStartDate(), request.getEndDate());
        event.setRunningPeriod(dateRange);
        event.setCreatedAt(LocalDateTime.now());
    }

    @Named("getCurrentTime")
    protected LocalDateTime getCurrentTime(UpdateEventRequest dto) {
        return LocalDateTime.now();
    }

}
