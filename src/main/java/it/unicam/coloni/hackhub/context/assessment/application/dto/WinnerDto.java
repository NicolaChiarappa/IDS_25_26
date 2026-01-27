package it.unicam.coloni.hackhub.context.assessment.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinnerDto {
    private Long id;
    private Long eventId;
    private Long teamId;
    private Double totalScore;
    private Integer rankPosition;
}