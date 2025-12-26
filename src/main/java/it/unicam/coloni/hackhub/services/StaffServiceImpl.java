package it.unicam.coloni.hackhub.services;

import it.unicam.coloni.hackhub.dto.AssignmentDto;
import it.unicam.coloni.hackhub.dto.requests.AddJudgeRequest;
import it.unicam.coloni.hackhub.dto.requests.AddMentorRequest;
import it.unicam.coloni.hackhub.dto.requests.UpdateMentorRequest;
import it.unicam.coloni.hackhub.mappers.AssignmentMapper;
import it.unicam.coloni.hackhub.model.*;
import it.unicam.coloni.hackhub.repository.AssignmentRepository;
import it.unicam.coloni.hackhub.repository.EventRepository;
import it.unicam.coloni.hackhub.repository.UserRepository;
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
