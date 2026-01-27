package it.unicam.coloni.hackhub.context.assessment.infrastructure.web;

import it.unicam.coloni.hackhub.context.assessment.application.dto.WinnerDto;
import it.unicam.coloni.hackhub.context.assessment.application.service.WinnerService;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponse;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/winner")
@RequiredArgsConstructor
public class WinnerController {

    private final WinnerService winnerService;
    private final ApiResponseFactory responseFactory;

    @PostMapping("/calculate/{eventId}")
    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    public ApiResponse<List<WinnerDto>> calculateWinners(@PathVariable Long eventId) {
        List<WinnerDto> winners = winnerService.calculateAndDeclareWinners(eventId);
        return responseFactory.createSuccessResponse( "Winners calculated successfully", winners);
    }

    @GetMapping("/{eventId}")
    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    public ApiResponse<List<WinnerDto>> getWinners(@PathVariable Long eventId) {
        List<WinnerDto> winners = winnerService.getWinners(eventId);
        return responseFactory.createSuccessResponse("Winners retrieved successfully", winners );
    }
}