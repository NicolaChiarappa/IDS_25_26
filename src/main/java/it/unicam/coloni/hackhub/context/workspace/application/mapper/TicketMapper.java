package it.unicam.coloni.hackhub.context.workspace.application.mapper;

import it.unicam.coloni.hackhub.context.workspace.application.dto.TicketDto;
import it.unicam.coloni.hackhub.context.workspace.domain.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TicketMapper {

    TicketDto toDto(Ticket entity);

}
