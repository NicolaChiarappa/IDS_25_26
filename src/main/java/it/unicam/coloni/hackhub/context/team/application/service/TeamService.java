package it.unicam.coloni.hackhub.context.team.application.service;

import it.unicam.coloni.hackhub.context.identity.application.service.AuthService;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
import it.unicam.coloni.hackhub.context.team.domain.model.StudentTeamAssignment;
import it.unicam.coloni.hackhub.context.team.domain.model.Team;
import it.unicam.coloni.hackhub.context.team.domain.model.TeamEventAssignment;
import it.unicam.coloni.hackhub.context.team.domain.repository.StudentTeamRepository;
import it.unicam.coloni.hackhub.context.team.domain.repository.TeamEventRepository;
import it.unicam.coloni.hackhub.context.team.domain.repository.TeamRepository;
import it.unicam.coloni.hackhub.shared.domain.enums.PlatformRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamEventRepository eventAssignmentRepository;

    @Autowired
    StudentTeamRepository studentTeamAssignmentRepository;

    @Autowired
    AuthService authService;


    public Team createTeam(String name){
        User creator = authService.getLoggedUser();
        if(creator.getRole()==PlatformRoles.STUDENT){
            if(studentTeamAssignmentRepository.findAllByUserId(creator.getId()).isEmpty()){
                Team newTeam = new Team(name, creator.getId());
                Team savedTeam = teamRepository.save(newTeam);

                StudentTeamAssignment assignment = new StudentTeamAssignment(creator.getId(), savedTeam.getId());
                studentTeamAssignmentRepository.save(assignment);

                return savedTeam;

            }
            else {
                throw new RuntimeException("not able to create a new team");
            }
        }
        else {
            throw new RuntimeException("not able to create a new team");
        }

    }

    public TeamEventAssignment subscribeTeamToEvent(Long eventId, Long teamId){
        User user = authService.getLoggedUser();
        if(studentTeamAssignmentRepository.findAllByUserId(user.getId()).stream().anyMatch(ass->ass.getTeamId().equals(teamId))) {
            TeamEventAssignment newAssignment = new TeamEventAssignment(eventId, teamId);
            return eventAssignmentRepository.save(newAssignment);
        }else {
            throw new RuntimeException("You are not assigned to this team");
        }
    }

    public List<Team> getByHackathon(Long id){
        List<Long> assignments = eventAssignmentRepository.getAllByEventId(id).stream().map(TeamEventAssignment::getTeamId).toList();
        return teamRepository.findAllById(assignments);
    }


}
