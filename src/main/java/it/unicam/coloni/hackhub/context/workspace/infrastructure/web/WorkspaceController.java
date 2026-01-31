package it.unicam.coloni.hackhub.context.workspace.infrastructure.web;

import it.unicam.coloni.hackhub.context.workspace.application.dto.*;
import it.unicam.coloni.hackhub.context.workspace.application.dto.request.GetTicketsRequest;
import it.unicam.coloni.hackhub.context.workspace.application.dto.request.ReportRequest;
import it.unicam.coloni.hackhub.context.workspace.application.dto.request.ScheduleMeetingRequest;
import it.unicam.coloni.hackhub.context.workspace.application.service.WorkspaceService;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponse;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workspace")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;
    private final ApiResponseFactory responseFactory;

    @PostMapping("/report")
    @PreAuthorize("hasAnyAuthority('MENTOR')")
    public ResponseEntity<ApiResponse<ReportDto>> sendReport(@RequestBody @Valid ReportRequest request) {
        ReportDto report = workspaceService.sendReport(request);
        return ResponseEntity.ok(responseFactory.createSuccessResponse("Report sent successfully", report));
    }

    @PostMapping("/tickets")
    @PreAuthorize("hasAnyAuthority('MENTOR')")
    public ResponseEntity<ApiResponse<List<TicketDto>>> getAllTicketsByMentor(
            @RequestBody @Valid GetTicketsRequest request) {
        List<TicketDto> tickets = workspaceService.getAllTicketsByMentor(request);
        return ResponseEntity.ok(responseFactory.createSuccessResponse("Tickets retrieved successfully", tickets));
    }

    @PostMapping("/meeting")
    @PreAuthorize("hasAnyAuthority('MENTOR')")
    public ResponseEntity<ApiResponse<MeetingDto>> scheduleMeeting(@RequestBody @Valid ScheduleMeetingRequest request) {
        MeetingDto meeting = workspaceService.scheduleMeeting(request);
        return ResponseEntity.ok(responseFactory.createSuccessResponse("Meeting scheduled successfully", meeting));
    }
}
