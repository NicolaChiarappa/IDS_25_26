package it.unicam.coloni.hackhub.context.assessment.application.service;

import it.unicam.coloni.hackhub.context.assessment.application.dto.AssessmentDto;
import it.unicam.coloni.hackhub.context.assessment.application.dto.request.AssessmentRequest;
import it.unicam.coloni.hackhub.context.assessment.application.mapper.AssessmentMapper;
import it.unicam.coloni.hackhub.context.assessment.domain.model.Assessment;
import it.unicam.coloni.hackhub.context.assessment.domain.repository.AssessmentRepository;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.model.EventStatus;
import it.unicam.coloni.hackhub.context.event.domain.repository.EventRepository;
import it.unicam.coloni.hackhub.context.identity.application.service.AuthService;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final AssessmentMapper assessmentMapper;
    private final EventRepository eventRepository;
    private final AuthService authService;


    @Override
    @Transactional
    public AssessmentDto addAssessment(AssessmentRequest request) {

        User judge = authService.getLoggedUser();
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        checkAuthority(event,judge);

        if (event.getStatus() != EventStatus.EVALUATING) {
            throw new IllegalStateException("Event is not in EVALUATING state.");
        }



        if (assessmentRepository.existsByEventIdAndTeamIdAndJudgeId(
                request.getEventId(),
                request.getTeamId(),
                judge.getId())) {
            throw new IllegalStateException("You have already voted for this team.");
        }
        Assessment assessment = assessmentMapper.toEntity(request);
        assessment.setJudgeId(judge.getId());
        Assessment saved = assessmentRepository.save(assessment);
        return assessmentMapper.toDto(saved);
    }

    @Override
    public List<AssessmentDto> getAssessmentsByEvent(Long eventId) {
        User user = authService.getLoggedUser();
        Event event = eventRepository.findById(eventId).orElseThrow();

        checkAuthority(event,user);

        return assessmentRepository.findByEventId(eventId).stream()
                .map(assessmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssessmentDto> getAssessmentsByTeam(Long teamId) {



        return assessmentRepository.findByTeamId(teamId).stream()
                .map(assessmentMapper::toDto)
                .collect(Collectors.toList());
    }

    private void checkAuthority(Event event, User judge){
        if(event.getStaff().stream().noneMatch(assignment -> assignment.getUserId().equals(judge.getId()))) {
        throw new IllegalArgumentException("Current user is not working in this event");
    }

    }


}