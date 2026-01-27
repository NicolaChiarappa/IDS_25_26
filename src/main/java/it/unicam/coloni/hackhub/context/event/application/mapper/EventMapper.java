package it.unicam.coloni.hackhub.context.event.application.mapper;

import it.unicam.coloni.hackhub.context.event.application.dto.requests.SimpleCreationRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.WithStaffCreationRequest;
import it.unicam.coloni.hackhub.context.event.domain.model.*;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateEventRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.EventDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {DateRange.class, EventMapper.class, AssignmentMapper.class})
public abstract class EventMapper {

    @Autowired
    private AssignmentMapper assignmentMapper;



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
    @Mapping(target = "assignments", source = "staff")
    public abstract EventDto toDto(Event event);





    @Mapping(target = "modifiedAt", source = ".", qualifiedByName = "getCurrentTime")
    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "runningPeriod", ignore = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract void fromUpdate(@MappingTarget Event event, UpdateEventRequest dto);


    /**
     * Maps the users' ids from {@link Event} to {@link EventDto} based on their roles
     * @param eventDto the mapping target
     * @param event the mapping source
     */
//    @AfterMapping
//    void takeRoles(@MappingTarget EventDto eventDto, Event event) {
//        eventDto.setOrganizerId(
//                event.getStaff().stream()
//                        .filter(assignment -> assignment.getRole()== PlatformRoles.ORGANIZER)
//                        .map(Assignment::getUserId)
//                        .findAny()
//                        .orElse(null)
//        );
//
//        eventDto.setJudgeId(
//                event.getStaff().stream()
//                        .filter(assignment -> assignment.getRole()== PlatformRoles.JUDGE)
//                        .map(Assignment::getUserId)
//                        .findAny()
//                        .orElse(null)
//
//        );
//
//        eventDto.setMentorsIds(
//                event.getStaff().stream()
//                        .filter(assignment -> assignment.getRole()== PlatformRoles.MENTOR)
//                        .map(Assignment::getUserId)
//                        .toList()
//        );
//    }


    /**
     * Sets the {@link Event}'s runningPeriod property from {@link WithStaffCreationRequest}'s startDate and endDate
     * @param event the mapping target
     * @param request the mapping source
     */
    @AfterMapping
    void setDateRange(@MappingTarget Event event, WithStaffCreationRequest request) {
        DateRange dateRange = DateRange.fromDates(request.getStartDate(), request.getEndDate());
        event.setRunningPeriod(dateRange);
        event.setCreatedAt(LocalDateTime.now());
    }


    @Named("getCurrentTime")
    protected LocalDateTime getCurrentTime(UpdateEventRequest dto) {
        return LocalDateTime.now();
    }



}
