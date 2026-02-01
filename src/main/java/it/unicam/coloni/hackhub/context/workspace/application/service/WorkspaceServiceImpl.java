package it.unicam.coloni.hackhub.context.workspace.application.service;

import it.unicam.coloni.hackhub.context.event.domain.model.Assignment;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.repository.AssignmentRepository;
import it.unicam.coloni.hackhub.context.event.domain.repository.EventRepository;
import it.unicam.coloni.hackhub.context.identity.application.service.AuthService;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final ReportRepository reportRepository;
    private final TicketRepository ticketRepository;
    private final MeetingRepository meetingRepository;
    private final EventRepository eventRepository;
    private final AssignmentRepository assignmentRepository;
    private final AuthService authService;

    private final ReportMapper reportMapper;
    private final TicketMapper ticketMapper;
    private final MeetingMapper meetingMapper;

    @Autowired
    private final List<MeetingObserver> meetingObservers;

    @Override
    public ReportDto sendReport(ReportRequest request) {
        Event event = eventRepository.findById(request.getEventId()).orElseThrow();
        User logged = authService.getLoggedUser();
        checkAuthority(event, logged, request.getTeamId() );

        Report report = reportMapper.toEntity(request);
        report.setAuthorId(logged.getId());

        Report savedReport = reportRepository.save(report);
        return reportMapper.toDto(savedReport);
    }

    @Override
    public List<TicketDto> getAllTicketsByMentor(GetTicketsRequest request) {

        Event event = eventRepository.findById(request.getEventId()).orElseThrow();
        User logged = authService.getLoggedUser();
        checkAuthority(event, logged, null );

        List<Assignment> teams = assignmentRepository.findAllByEventAndUserId(event, logged.getId());

        List<Ticket> tickets= new ArrayList<>();

        for(Assignment a : teams){
            Long teamId = a.getTeamId();
            tickets.addAll(ticketRepository.findAllByEventIdAndTeamId(event.getId(), teamId));
        }

        return tickets.stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MeetingDto scheduleMeeting(ScheduleMeetingRequest request) {

        Event event = eventRepository.findById(request.getEventId()).orElseThrow();
        User logged = authService.getLoggedUser();
        checkAuthority(event, logged, request.getTeamId() );

        Meeting meeting = meetingMapper.toEntity(request);
        meeting.setMentorId(authService.getLoggedUser().getId());

        Meeting savedMeeting = meetingRepository.save(meeting);
        for(MeetingObserver o: meetingObservers){
            o.doActionOnMeetingScheduled(savedMeeting);
        }

        return meetingMapper.toDto(savedMeeting);
    }


    private void checkAuthority(Event event, User logged, Long teamId) {
        if (event.getStaff().stream().noneMatch(assignment -> assignment.getUserId().equals(logged.getId()))) {
            throw new IllegalArgumentException("Current user is not working in this event");
        }
        if (teamId!=null) {
           if( event.getStaff().stream()
                    .filter(assignment -> assignment.getTeamId()!=null)
                    .noneMatch(assignment -> assignment.getTeamId().equals(teamId))){
               throw new IllegalArgumentException("This mentor is not assigned to the specified team");
           }
        }
    }
}
