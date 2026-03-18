package it.unicam.coloni.hackhub.context.team.infrastructure.web;

import it.unicam.coloni.hackhub.context.team.application.service.TeamService;
import it.unicam.coloni.hackhub.context.team.application.service.request.CreateTeamRequest;
import it.unicam.coloni.hackhub.context.team.application.service.request.SubscribeTeamRequest;
import it.unicam.coloni.hackhub.context.team.domain.model.TeamEventAssignment;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponse;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.unicam.coloni.hackhub.context.team.domain.model.Team;

@RestController
@RequestMapping("api/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    ApiResponseFactory responseFactory;

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public ApiResponse<Team> createTeam(@RequestBody CreateTeamRequest request) {
        return responseFactory.createSuccessResponse("Team created successfully", teamService.createTeam(request.getName()));
    }

    @PostMapping("/subscribe")
    @PreAuthorize("hasAnyAuthority('STUDENT')")

    public ApiResponse<TeamEventAssignment> subscribeToEvent(@RequestBody SubscribeTeamRequest request) {
        return responseFactory.createSuccessResponse("Team subscribed successfully", teamService.subscribeTeamToEvent(request.getEventId(), request.getTeamId()));
    }





}
