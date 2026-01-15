package it.unicam.coloni.hackhub.context.event.infrastracture.web.controller;

import it.unicam.coloni.hackhub.context.event.application.dto.AssignmentDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddJudgeRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @PostMapping("/judge")
    public AssignmentDto addJudge(@RequestBody AddJudgeRequest request){
        return staffService.addJudge(request);
    }

    @PostMapping("/mentor")
    public AssignmentDto addMentor(@RequestBody AddMentorRequest request){
        return staffService.addMentor(request);
    }

    @PatchMapping
    public AssignmentDto updateMentor(@RequestBody UpdateMentorRequest request){
        return staffService.updateMentor(request);
    }


}
