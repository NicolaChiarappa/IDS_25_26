package it.unicam.coloni.hackhub.context.workspace.application.mapper;

import it.unicam.coloni.hackhub.context.workspace.application.dto.ReportDto;
import it.unicam.coloni.hackhub.context.workspace.application.dto.request.ReportRequest;
import it.unicam.coloni.hackhub.context.workspace.domain.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReportMapper {

    ReportDto toDto(Report entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "workspace", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    Report toEntity(ReportRequest request);
}
