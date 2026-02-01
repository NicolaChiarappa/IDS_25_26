package it.unicam.coloni.hackhub.context.event.application.dto;

import it.unicam.coloni.hackhub.context.assessment.application.dto.AssessmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDetailsDto {

    private EventDto event;

    private List<AssignmentDto> staff;

    private List<AssessmentDto> assessments;

}
