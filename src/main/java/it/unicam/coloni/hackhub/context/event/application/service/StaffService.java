package it.unicam.coloni.hackhub.context.event.application.service;

import it.unicam.coloni.hackhub.context.event.application.dto.AssignmentDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddJudgeRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateMentorRequest;


public interface StaffService {


    AssignmentDto addJudge(AddJudgeRequest request);

    AssignmentDto addMentor(AddMentorRequest request);

    AssignmentDto assignMentorToTeam(UpdateMentorRequest request);

}
