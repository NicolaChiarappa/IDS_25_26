package it.unicam.coloni.hackhub.context.event.application.service;

import it.unicam.coloni.hackhub.context.event.domain.model.Assignment;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.model.User;
import it.unicam.coloni.hackhub.context.event.domain.model.UserRole;
import it.unicam.coloni.hackhub.context.event.application.dto.AssignmentDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddJudgeRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.mapper.AssignmentMapper;
import it.unicam.coloni.hackhub.context.event.domain.repository.AssignmentRepository;
import it.unicam.coloni.hackhub.context.event.domain.repository.EventRepository;
import it.unicam.coloni.hackhub.context.event.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Assignment assignment;
        User judge = new User("Giudice", UserRole.JUDGE);
        userRepository.save(judge);
        Event event = eventRepository.findById(request.getEventId()).orElseThrow();

        if(judge.isAvailable(event.getRunningPeriod())){
            assignment = event.addJudge(judge);
            eventRepository.save(event);
        }else{
            throw new IllegalStateException("The provided user is not available");
        }
        return assignmentMapper.toDto(assignment);



    }

    @Override
    public AssignmentDto addMentor(AddMentorRequest request) {
        Assignment assignment;
        User mentor = new User("Mentore", UserRole.MENTOR);
        userRepository.save(mentor);
        Event event = eventRepository.findById(request.getEventId()).orElseThrow();

        if(mentor.isAvailable(event.getRunningPeriod())){
            assignment = event.addMentor(mentor);
            eventRepository.save(event);
        }else {
            throw new IllegalStateException("The provided user is not available");
        }
        return assignmentMapper.toDto(assignment);
    }

    @Override
    public AssignmentDto updateMentor(UpdateMentorRequest request) {

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
