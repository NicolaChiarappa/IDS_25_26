package it.unicam.coloni.hackhub.context.workspace.application.dto;

import it.unicam.coloni.hackhub.context.workspace.domain.model.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
        private Long id;
        private Long eventId;
        private Long teamId;
        private String description;
        private Integer urgency;
        private TicketStatus status;
}
