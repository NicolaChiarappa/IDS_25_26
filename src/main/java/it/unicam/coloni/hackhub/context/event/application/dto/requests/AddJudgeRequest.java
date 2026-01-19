package it.unicam.coloni.hackhub.context.event.application.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddJudgeRequest {

    private Long eventId;
    private Long userId;

}
