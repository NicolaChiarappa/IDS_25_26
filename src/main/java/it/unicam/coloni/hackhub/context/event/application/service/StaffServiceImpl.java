package it.unicam.coloni.hackhub.context.event.application.service;

import it.unicam.coloni.hackhub.context.event.domain.model.*;
import it.unicam.coloni.hackhub.context.event.application.dto.AssignmentDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddJudgeRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.mapper.AssignmentMapper;
import it.unicam.coloni.hackhub.context.event.domain.repository.AssignmentRepository;
import it.unicam.coloni.hackhub.context.event.domain.repository.EventRepository;
import it.unicam.coloni.hackhub.context.identity.domain.repository.UserRepository;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
import it.unicam.coloni.hackhub.shared.infrastructure.web.domain.enums.PlatformRoles;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService{


    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AssignmentMapper assignmentMapper;


    @Override
    @Transactional
    public AssignmentDto addJudge(AddJudgeRequest request) {
        StaffMember judge = findMember(request.getUserId());
        Event event = eventRepository.findById(request.getEventId()).orElseThrow();
        List<DateRange> busyPeriods = assignmentRepository.findBusyPeriodByserId(judge.getId());
        Assignment assignment = event.addJudge(judge, busyPeriods);
        eventRepository.save(event);
        return assignmentMapper.toDto(assignment);
    }



    @Override
    @Transactional
    public AssignmentDto addMentor(AddMentorRequest request) {
        StaffMember mentor = findMember(request.getUserId());
        Event event = eventRepository.findById(request.getEventId()).orElseThrow();
        List<DateRange> busyPeriods = assignmentRepository.findBusyPeriodByserId(mentor.getId());
        Assignment assignment = event.addMentor(mentor, busyPeriods);
        eventRepository.save(event);
        return assignmentMapper.toDto(assignment);
    }


    private StaffMember findMember(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return new StaffMember(user.getId(), user.getUsername(), PlatformRoles.JUDGE);
    }

    @Override
    public AssignmentDto assignMentorToTeam(UpdateMentorRequest request) {

        Assignment assignment;
        Event event = eventRepository.findById(request.getEventId()).orElseThrow();

        //TODO: pronto quando avremo il repository dei team
        //Team team = teamRepository.findById(request.getTeamId()).orElseThrow();

//        User mentor = userRepository.findById(request.getUserId()).orElseThrow();
//
//        assignment = event.updateMentor(mentor, team);
//        eventRepository.save(event);
//
//
//        return assignmentMapper.toDto(assignment);

        return null;

    }
}
