package it.unicam.coloni.hackhub.dto;



import it.unicam.coloni.hackhub.model.DateRange;
import lombok.Data;


import java.util.List;

@Data
public class EventDto {

    private Long eventId;

    private String name;

    private DateRange runningPeriod;

    private String rulesUrl;

    private Long organizerId;

    private Long judgeId;

    private List<Long> mentorsIds;


}
