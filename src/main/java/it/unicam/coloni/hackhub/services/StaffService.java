package it.unicam.coloni.hackhub.services;

import it.unicam.coloni.hackhub.dto.AssignmentDto;
import it.unicam.coloni.hackhub.dto.requests.AddJudgeRequest;
import it.unicam.coloni.hackhub.dto.requests.AddMentorRequest;
import it.unicam.coloni.hackhub.dto.requests.UpdateMentorRequest;


public interface StaffService {


    AssignmentDto addJudge(AddJudgeRequest request);


    AssignmentDto addMentor(AddMentorRequest request);

    AssignmentDto updateMentor(UpdateMentorRequest request);

}
