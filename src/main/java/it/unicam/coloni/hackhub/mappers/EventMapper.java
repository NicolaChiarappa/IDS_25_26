package it.unicam.coloni.hackhub.mappers;

import it.unicam.coloni.hackhub.model.*;
import it.unicam.coloni.hackhub.dto.CreateEventRequest;
import it.unicam.coloni.hackhub.dto.EventDto;
import org.mapstruct.*;


@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = DateRange.class)
public abstract class EventMapper {



    @Mapping(target = "status", ignore = true)
    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "runningPeriod", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Event from(CreateEventRequest request);


    @AfterMapping
    void setDateRange(@MappingTarget Event event, CreateEventRequest request) {
        DateRange dateRange = DateRange.fromDates(request.getStartDate(), request.getEndDate());
        event.setRunningPeriod(dateRange);
    }


    @Mapping(target = "organizerId", ignore = true)
    @Mapping(target = "mentorsIds", ignore = true)
    @Mapping(target = "judgeId", ignore = true)
    @Mapping(target = "eventId", source = "id")
    public abstract EventDto from(Event event);

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

}
