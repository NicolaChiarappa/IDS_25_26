package it.unicam.coloni.hackhub.mappers;

import it.unicam.coloni.hackhub.dto.requests.UpdateEventRequest;
import it.unicam.coloni.hackhub.model.*;
import it.unicam.coloni.hackhub.dto.requests.CreateEventRequest;
import it.unicam.coloni.hackhub.dto.EventDto;
import org.mapstruct.*;
import java.time.LocalDateTime;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {DateRange.class, EventMapper.class})
public abstract class EventMapper {



    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "runningPeriod", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Event toDto(CreateEventRequest request, @MappingTarget Event event);



    @Mapping(target = "organizerId", ignore = true)
    @Mapping(target = "mentorsIds", ignore = true)
    @Mapping(target = "judgeId", ignore = true)
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
     * Maps the users' ids from {@link Event} to {@link EventDto} based on their roles
     * @param eventDto the mapping target
     * @param event the mapping source
     */
    @AfterMapping
    void takeRoles(@MappingTarget EventDto eventDto, Event event) {
        eventDto.setOrganizerId(
                event.getStaff().stream()
                        .filter(assignment -> assignment.getUser().getRole()== UserRole.ORGANIZER)
                        .map(assignment -> assignment.getUser().getId())
                        .findAny()
                        .orElse(null)
        );

        eventDto.setJudgeId(
                event.getStaff().stream()
                        .filter(assignment -> assignment.getUser().getRole()== UserRole.JUDGE)
                        .map(assignment -> assignment.getUser().getId())
                        .findAny()
                        .orElse(null)

        );

        eventDto.setMentorsIds(
                event.getStaff().stream()
                        .filter(assignment -> assignment.getUser().getRole()== UserRole.MENTOR)
                        .map(assignment -> assignment.getUser().getId())
                        .toList()
        );
    }


    /**
     * Sets the {@link Event}'s runningPeriod property from {@link CreateEventRequest}'s startDate and endDate
     * @param event the mapping target
     * @param request the mapping source
     */
    @AfterMapping
    void setDateRange(@MappingTarget Event event, CreateEventRequest request) {
        DateRange dateRange = DateRange.fromDates(request.getStartDate(), request.getEndDate());
        event.setRunningPeriod(dateRange);
        event.setCreatedAt(LocalDateTime.now());
        event.setStatus(EventStatus.SUBSCRIPTION);
    }


    @Named("getCurrentTime")
    protected LocalDateTime getCurrentTime(UpdateEventRequest dto) {
        return LocalDateTime.now();
    }


}
