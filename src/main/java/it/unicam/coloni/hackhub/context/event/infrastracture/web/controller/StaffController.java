package it.unicam.coloni.hackhub.context.event.infrastracture.web.controller;

import it.unicam.coloni.hackhub.context.event.application.dto.AssignmentDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddJudgeRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PostMapping("/judge")
    public AssignmentDto addJudge(@RequestBody AddJudgeRequest request){
        return staffService.addJudge(request);
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PostMapping("/mentor")
    public AssignmentDto addMentor(@RequestBody AddMentorRequest request){
        return staffService.addMentor(request);
    }

    @PatchMapping("/mentor")
    public AssignmentDto updateMentor(@RequestBody UpdateMentorRequest request){
        return staffService.assignMentorToTeam(request);
    }


}
