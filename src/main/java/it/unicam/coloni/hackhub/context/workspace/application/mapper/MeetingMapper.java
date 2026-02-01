package it.unicam.coloni.hackhub.context.workspace.application.mapper;

import it.unicam.coloni.hackhub.context.workspace.application.dto.MeetingDto;
import it.unicam.coloni.hackhub.context.workspace.application.dto.request.ScheduleMeetingRequest;
import it.unicam.coloni.hackhub.context.workspace.domain.model.Meeting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MeetingMapper {

    @Mapping(target = "upcoming", expression = "java(entity.isUpcoming())")
    @Mapping(target = "link", source = "meetingLink")
    MeetingDto toDto(Meeting entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mentorId", ignore = true)
    @Mapping(target = "ticketId", ignore = true)
    @Mapping(target = "workspace", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "meetingLink", source = "link")
    Meeting toEntity(ScheduleMeetingRequest request);
}
