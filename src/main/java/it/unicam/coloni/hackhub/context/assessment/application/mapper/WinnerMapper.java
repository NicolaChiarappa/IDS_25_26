package it.unicam.coloni.hackhub.context.assessment.application.mapper;

import it.unicam.coloni.hackhub.context.assessment.application.dto.WinnerDto;
import it.unicam.coloni.hackhub.context.assessment.domain.model.Winner;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class WinnerMapper {
    public  abstract WinnerDto toDto(Winner entity);


    public  abstract List<WinnerDto> toDtoList(List<Winner> entities);
}