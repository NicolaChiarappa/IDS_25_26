package it.unicam.coloni.hackhub.context.assessment.application.mapper;

import it.unicam.coloni.hackhub.context.assessment.application.dto.AssessmentDto;
import it.unicam.coloni.hackhub.context.assessment.application.dto.request.AssessmentRequest;
import it.unicam.coloni.hackhub.context.assessment.domain.model.Assessment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class AssessmentMapper {

    @Mapping(target = "averageScore", expression = "java(assessment.getAverageScore())")
    public abstract AssessmentDto toDto(Assessment assessment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Assessment toEntity(AssessmentRequest request);
}