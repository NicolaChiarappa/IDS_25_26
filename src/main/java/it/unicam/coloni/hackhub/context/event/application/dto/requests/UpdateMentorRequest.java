package it.unicam.coloni.hackhub.context.event.application.dto.requests;

import lombok.Data;

@Data
public class UpdateMentorRequest {
    private Long eventId;
    private Long userId;
    private Long teamId;
}
