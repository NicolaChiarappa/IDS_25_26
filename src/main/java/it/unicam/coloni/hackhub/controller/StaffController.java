package it.unicam.coloni.hackhub.controller;

import it.unicam.coloni.hackhub.dto.AssignmentDto;
import it.unicam.coloni.hackhub.dto.requests.AddJudgeRequest;
import it.unicam.coloni.hackhub.dto.requests.AddMentorRequest;
import it.unicam.coloni.hackhub.dto.requests.UpdateMentorRequest;
import it.unicam.coloni.hackhub.services.StaffService;
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
