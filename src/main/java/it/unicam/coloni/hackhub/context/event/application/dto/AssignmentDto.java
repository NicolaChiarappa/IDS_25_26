package it.unicam.coloni.hackhub.context.event.application.dto;

import lombok.Data;

@Data
public class AssignmentDto {

    private Long eventId;

    private Long userId;

    private Long teamId;
}
