package it.unicam.coloni.hackhub.context.event.application.mapper;

import it.unicam.coloni.hackhub.context.event.application.dto.AssignmentDto;
import it.unicam.coloni.hackhub.context.event.domain.model.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

public abstract class AssignmentMapper {

    @Mapping(target = "eventId", expression = "java(assignment.getEvent().getId())")
    public abstract AssignmentDto toDto(Assignment assignment);



}
