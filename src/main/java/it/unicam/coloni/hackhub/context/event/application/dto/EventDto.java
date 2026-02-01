package it.unicam.coloni.hackhub.context.event.application.dto;

import it.unicam.coloni.hackhub.context.event.domain.model.DateRange;
import it.unicam.coloni.hackhub.context.event.domain.model.EventStatus;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventDto {

    private Long eventId;

    private String name;

    private DateRange runningPeriod;

    private String rulesUrl;

    private EventStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    private LocalDateTime modifiedAt;

}
