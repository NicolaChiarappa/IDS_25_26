package it.unicam.coloni.hackhub.dto.requests;

import lombok.Data;


import java.time.LocalDate;

@Data
public abstract class EventRequest {

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String rulesUrl;
}
