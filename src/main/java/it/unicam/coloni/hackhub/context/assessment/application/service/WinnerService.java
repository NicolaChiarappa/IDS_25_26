package it.unicam.coloni.hackhub.context.assessment.application.service;

import it.unicam.coloni.hackhub.context.assessment.application.dto.WinnerDto;

import java.util.List;

public interface WinnerService {

    WinnerDto calculateAndDeclareWinner(Long eventId);

    WinnerDto getWinner(Long eventId);
}