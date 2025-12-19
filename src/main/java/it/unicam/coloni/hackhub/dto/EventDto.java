package it.unicam.coloni.hackhub.dto;

import it.unicam.coloni.hackhub.model.DateRange;
import it.unicam.coloni.hackhub.model.EventStatus;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventDto {

    private Long eventId;

    private String name;

    private DateRange runningPeriod;

    private String rulesUrl;

    private Long organizerId;

    private Long judgeId;

    private EventStatus status;

    private List<Long> mentorsIds;


    private LocalDateTime createdAt;


    private LocalDateTime deletedAt;


    private LocalDateTime modifiedAt;


}
