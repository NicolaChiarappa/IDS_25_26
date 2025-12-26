package it.unicam.coloni.hackhub.dto.requests;

import lombok.Data;

@Data
public class UpdateMentorRequest {
    private Long eventId;
    private Long userId;
    private Long teamId;
}
