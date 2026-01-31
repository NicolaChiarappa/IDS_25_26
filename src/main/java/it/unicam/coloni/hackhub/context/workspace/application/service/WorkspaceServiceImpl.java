package it.unicam.coloni.hackhub.context.workspace.application.service;

import it.unicam.coloni.hackhub.context.identity.application.service.AuthService;
import it.unicam.coloni.hackhub.context.workspace.application.dto.*;
import it.unicam.coloni.hackhub.context.workspace.application.dto.request.GetTicketsRequest;
import it.unicam.coloni.hackhub.context.workspace.application.dto.request.ReportRequest;
import it.unicam.coloni.hackhub.context.workspace.application.dto.request.ScheduleMeetingRequest;
import it.unicam.coloni.hackhub.context.workspace.application.mapper.MeetingMapper;
import it.unicam.coloni.hackhub.context.workspace.application.mapper.ReportMapper;
import it.unicam.coloni.hackhub.context.workspace.application.mapper.TicketMapper;
import it.unicam.coloni.hackhub.context.workspace.domain.model.Meeting;
import it.unicam.coloni.hackhub.context.workspace.domain.model.Report;
import it.unicam.coloni.hackhub.context.workspace.domain.model.Ticket;
import it.unicam.coloni.hackhub.context.workspace.domain.repository.MeetingRepository;
import it.unicam.coloni.hackhub.context.workspace.domain.repository.ReportRepository;
import it.unicam.coloni.hackhub.context.workspace.domain.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final ReportRepository reportRepository;
    private final TicketRepository ticketRepository;
    private final MeetingRepository meetingRepository;
    private final AuthService authService;

    private final ReportMapper reportMapper;
    private final TicketMapper ticketMapper;
    private final MeetingMapper meetingMapper;

    @Override
    public ReportDto sendReport(ReportRequest request) {
        Report report = reportMapper.toEntity(request);
        report.setAuthorId(authService.getLoggedUser().getId());

        Report savedReport = reportRepository.save(report);
        return reportMapper.toDto(savedReport);
    }

    @Override
    public List<TicketDto> getAllTicketsByMentor(GetTicketsRequest request) {
        List<Ticket> tickets = ticketRepository.findAllByEventId(request.getEventId());
        return tickets.stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MeetingDto scheduleMeeting(ScheduleMeetingRequest request) {
        Meeting meeting = meetingMapper.toEntity(request);
        meeting.setMentorId(authService.getLoggedUser().getId());

        Meeting savedMeeting = meetingRepository.save(meeting);
        return meetingMapper.toDto(savedMeeting);
    }
}
