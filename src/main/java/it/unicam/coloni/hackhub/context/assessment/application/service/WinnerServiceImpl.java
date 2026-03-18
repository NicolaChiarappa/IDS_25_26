package it.unicam.coloni.hackhub.context.assessment.application.service;

import it.unicam.coloni.hackhub.context.assessment.domain.model.Assessment;
import it.unicam.coloni.hackhub.context.assessment.domain.repository.AssessmentRepository;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.model.EventStatus;
import it.unicam.coloni.hackhub.context.event.domain.repository.EventRepository;
import it.unicam.coloni.hackhub.context.assessment.application.dto.WinnerDto;
import it.unicam.coloni.hackhub.context.assessment.application.mapper.WinnerMapper;
import it.unicam.coloni.hackhub.context.assessment.domain.model.Winner;
import it.unicam.coloni.hackhub.context.assessment.domain.repository.WinnerRepository;
import it.unicam.coloni.hackhub.context.identity.application.service.AuthService;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
import it.unicam.coloni.hackhub.context.team.domain.repository.TeamEventRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WinnerServiceImpl implements WinnerService {

    private final WinnerRepository winnerRepository;
    private final AssessmentRepository assessmentRepository;
    private final EventRepository eventRepository;
    private final WinnerMapper winnerMapper;
    private final AuthService authService;
    private final TeamEventRepository teamEventRepository;

    @Override
    @Transactional
    public WinnerDto calculateAndDeclareWinner(Long eventId) {

        User logged = authService.getLoggedUser();

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        checkAuthority(event, logged);

        if (event.getStatus() != EventStatus.EVALUATED) {
            throw new IllegalStateException("Event status is:" + event.getStatus());
        }

        List<Assessment> assessments = assessmentRepository.findByEventId(eventId);
        if (assessments.isEmpty()) {
            throw new IllegalStateException("No assessments found for this event. Cannot calculate winners.");
        }

        Winner winner = calculateWinner(eventId, assessments);

        Winner savedWinner = winnerRepository.save(winner);

        return winnerMapper.toDto(savedWinner);
    }

    @Override
    public WinnerDto getWinner(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new EntityNotFoundException("Event not found");
        }
        return winnerMapper.toDto(winnerRepository.findByEventId(eventId));
    }

    private Winner calculateWinner(Long eventId, List<Assessment> assessments) {

        if(teamEventRepository.getAllByEventId(eventId).size()!=assessments.size()){
            throw new IllegalStateException("The assessments table is not completed");
        }

        Map<Long, Double> teamScores = assessments.stream()
                .collect(Collectors.groupingBy(
                        Assessment::getTeamId,
                        Collectors.averagingDouble(Assessment::getAverageScore)));

        List<Map.Entry<Long, Double>> sortedTeams = teamScores.entrySet().stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
                .toList();

        List<Winner> winners = new ArrayList<>();
        int rank = 1;
        for (Map.Entry<Long, Double> entry : sortedTeams) {
            Winner winner = new Winner();
            winner.setEventId(eventId);
            winner.setTeamId(entry.getKey());
            winner.setTotalScore(entry.getValue());
            winner.setRankPosition(rank++);
            winners.add(winner);
        }
        return winners.getFirst();
    }

    private void checkAuthority(Event event, User organizer) {
        if (event.getStaff().stream().noneMatch(assignment -> assignment.getUserId().equals(organizer.getId()))) {
            throw new IllegalArgumentException("Current user is not working in this event");
        }
    }

}