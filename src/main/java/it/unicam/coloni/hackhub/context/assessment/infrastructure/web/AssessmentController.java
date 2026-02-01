package it.unicam.coloni.hackhub.context.assessment.infrastructure.web;

import it.unicam.coloni.hackhub.context.assessment.application.dto.AssessmentDto;
import it.unicam.coloni.hackhub.context.assessment.application.dto.request.AssessmentRequest;
import it.unicam.coloni.hackhub.context.assessment.application.service.AssessmentService;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponse;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessment")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService;
    private final ApiResponseFactory responseFactory;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('JUDGE')")
    public ApiResponse<AssessmentDto> addAssessment(@RequestBody @Valid AssessmentRequest request) {
        AssessmentDto savedDto = assessmentService.addAssessment(request);
        return responseFactory.createSuccessResponse(
                "Assessment submitted successfully",
                savedDto
        );
    }

    @GetMapping("/event/{eventId}")
    @PreAuthorize("hasAnyAuthority('JUDGE', 'MENTOR', 'ORGANIZER')")
    public ApiResponse<List<AssessmentDto>> getByEvent(@PathVariable Long eventId) {
        return responseFactory.createSuccessResponse(
                "Assessments retrieved successfully",
                assessmentService.getAssessmentsByEvent(eventId)
        );
    }

    @GetMapping("/team/{teamId}")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public ApiResponse<List<AssessmentDto>> getByTeam(@PathVariable Long teamId) {
        return responseFactory.createSuccessResponse(
                "Assessments retrieved successfully",
                assessmentService.getAssessmentsByTeam(teamId)
        );
    }
}