package it.unicam.coloni.hackhub.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEventRequest {
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String rulesUrl;
}
