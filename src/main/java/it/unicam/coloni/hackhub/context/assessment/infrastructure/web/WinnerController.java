package it.unicam.coloni.hackhub.context.assessment.infrastructure.web;

import it.unicam.coloni.hackhub.context.assessment.application.dto.WinnerDto;
import it.unicam.coloni.hackhub.context.assessment.application.service.WinnerService;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponse;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponseFactory;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<WinnerDto> calculateWinner(@PathVariable Long eventId) {
        WinnerDto winner = winnerService.calculateAndDeclareWinner(eventId);
        return responseFactory.createSuccessResponse( "Winners calculated successfully", winner);
    }

    @GetMapping("/{eventId}")
    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    public ApiResponse<WinnerDto> getWinner(@PathVariable Long eventId) {
        WinnerDto winner = winnerService.getWinner(eventId);
        return responseFactory.createSuccessResponse("Winner retrieved successfully", winner );
    }
}