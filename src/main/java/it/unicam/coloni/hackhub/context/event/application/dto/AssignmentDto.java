package it.unicam.coloni.hackhub.context.event.application.dto;

import it.unicam.coloni.hackhub.shared.domain.enums.PlatformRoles;
import lombok.Data;

@Data
public class AssignmentDto {

    private Long eventId;

    private PlatformRoles role;

    private Long userId;

    private Long teamId;
}
