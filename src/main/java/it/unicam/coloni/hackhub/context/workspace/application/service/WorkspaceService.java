package it.unicam.coloni.hackhub.context.workspace.application.service;

import it.unicam.coloni.hackhub.context.workspace.application.dto.*;
import it.unicam.coloni.hackhub.context.workspace.application.dto.request.GetTicketsRequest;
import it.unicam.coloni.hackhub.context.workspace.application.dto.request.ReportRequest;
import it.unicam.coloni.hackhub.context.workspace.application.dto.request.ScheduleMeetingRequest;

import java.util.List;

public interface WorkspaceService {

    ReportDto sendReport(ReportRequest request);

    List<TicketDto> getAllTicketsByMentor(GetTicketsRequest request);

    MeetingDto scheduleMeeting(ScheduleMeetingRequest request);
}