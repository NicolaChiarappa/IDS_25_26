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
import it.unicam.coloni.hackhub.shared.domain.enums.PlatformRoles;
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
        List<DateRange> busyPeriods = assignmentRepository.findBusyPeriodByUserId(judge.getId());
        Assignment assignment = event.addJudge(judge, busyPeriods);
        eventRepository.save(event);
        return assignmentMapper.toDto(assignment);
    }



    @Override
    @Transactional
    public AssignmentDto addMentor(AddMentorRequest request) {
        StaffMember mentor = findMember(request.getUserId());
        Event event = eventRepository.findById(request.getEventId()).orElseThrow();
        List<DateRange> busyPeriods = assignmentRepository.findBusyPeriodByUserId(mentor.getId());
        Assignment assignment = event.addMentor(mentor, busyPeriods);
        eventRepository.save(event);
        return assignmentMapper.toDto(assignment);
    }


    private StaffMember findMember(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return new StaffMember(user.getId(), user.getUsername(), user.getRole());
    }

    @Override
    public AssignmentDto assignMentorToTeam(UpdateMentorRequest request) {
        Assignment assignment;
        Event event = eventRepository.findById(request.getEventId()).orElseThrow();
        StaffMember mentor = findMember(request.getUserId());
        assignment = event.updateMentor(mentor, request.getTeamId());
        eventRepository.save(event);
        return assignmentMapper.toDto(assignment);

    }
}
