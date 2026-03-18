package it.unicam.coloni.hackhub.context.team.application.service.request;

import lombok.Data;

@Data
public class SubscribeTeamRequest {

    private Long eventId;

    private Long teamId;
}
