package it.unicam.coloni.hackhub.mappers;

import it.unicam.coloni.hackhub.dto.AssignmentDto;
import it.unicam.coloni.hackhub.model.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

public abstract class AssignmentMapper {



    @Mapping(target = "teamId", ignore = true)
    @Mapping(target = "eventId", expression = "java(assignment.getEvent().getId())")
    @Mapping(target = "userId", expression = "java(assignment.getUser().getId())")
    public abstract AssignmentDto toDto(Assignment assignment);



}
