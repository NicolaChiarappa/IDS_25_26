package it.unicam.coloni.hackhub.context.event.application.dto.requests;

import lombok.Data;

@Data
public class AddJudgeRequest {

    private Long eventId;
    private Long userId;

}
