package it.unicam.coloni.hackhub.context.event.application.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMentorRequest {
    private Long eventId;
    private Long userId;
}
