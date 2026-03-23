package it.unicam.coloni.hackhub.context.event.infrastracture.web.controller;

import it.unicam.coloni.hackhub.context.event.application.dto.AssignmentDto;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddJudgeRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.AddMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.dto.requests.UpdateMentorRequest;
import it.unicam.coloni.hackhub.context.event.application.service.StaffService;
import it.unicam.coloni.hackhub.context.identity.application.dto.UserDto;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponse;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @Autowired
    ApiResponseFactory apiResponseFactory;

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PostMapping("/judge")
    public ApiResponse<AssignmentDto> addJudge(@RequestBody AddJudgeRequest request){
        return apiResponseFactory.createSuccessResponse("Judge added successfully",staffService.addJudge(request));
    }

    @PreAuthorize("hasAnyAuthority('ORGANIZER')")
    @PostMapping("/mentor")
    public ApiResponse<AssignmentDto> addMentor(@RequestBody AddMentorRequest request){
        return apiResponseFactory.createSuccessResponse("Judge added successfully",staffService.addMentor(request));
    }

    @PatchMapping("/mentor")
    public ApiResponse<AssignmentDto> updateMentor(@RequestBody UpdateMentorRequest request){
        return apiResponseFactory.createSuccessResponse("Mentor assigned successfully",staffService.assignMentorToTeam(request));
    }

    @GetMapping("judges/all")
    public ApiResponse<List<UserDto>> getAllJudges(){
        return apiResponseFactory.createSuccessResponse("Judges found successfully",staffService.getAllJudges());
    }

    @GetMapping("mentors/all")
    public ApiResponse<List<UserDto>> getAllMentors(){
        return apiResponseFactory.createSuccessResponse("Mentors found successfully",staffService.getAllMentors());
    }

}
