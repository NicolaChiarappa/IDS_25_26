package it.unicam.coloni.hackhub.context.assessment.application.service;

import it.unicam.coloni.hackhub.context.assessment.application.dto.AssessmentDto;
import it.unicam.coloni.hackhub.context.assessment.application.dto.request.AssessmentRequest;

import java.util.List;

public interface AssessmentService {

    AssessmentDto addAssessment(AssessmentRequest request);

    List<AssessmentDto> getAssessmentsByEvent(Long eventId);

    List<AssessmentDto> getAssessmentsByTeam(Long teamId);


}