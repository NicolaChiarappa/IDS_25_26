package it.unicam.coloni.hackhub.dto.requests;

import lombok.Data;

@Data
public class AddMentorRequest {
    private Long eventId;
    private Long userId;
}
