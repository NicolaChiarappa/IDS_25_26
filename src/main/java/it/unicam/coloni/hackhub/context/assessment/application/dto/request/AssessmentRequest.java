package it.unicam.coloni.hackhub.context.assessment.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class AssessmentRequest {
    @NotNull
    private Long eventId;

    @NotNull
    private Long teamId;


    private Map<String, Integer> votes;


    private String description;
}