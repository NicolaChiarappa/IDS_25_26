package it.unicam.coloni.hackhub.context.assessment.application.dto;

import lombok.Data;
import java.util.Map;

@Data
public class AssessmentDto {
    private Long id;
    private Long eventId;
    private Long teamId;
    private Long judgeId;
    private Map<String, Integer> votes;
    private Double averageScore;
    private String description;
}