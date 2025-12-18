package it.unicam.coloni.hackhub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity {
    @Column
    private String name;

    @OneToMany(targetEntity= Assignment.class, mappedBy = "event", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Assignment> staff;

    @Embedded
    private DateRange runningPeriod;


    @Column
    private String rulesUrl;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    public static Event fromOrganizer(User organizer){
        if(organizer.getRole()!=UserRole.ORGANIZER){
            throw new IllegalArgumentException("The provided user is not an organizer");
        }
        Event event = new Event();
        List<Assignment> assignmentList = new ArrayList<>();
        event.staff = assignmentList;
        Assignment organizerAssignment = new Assignment(organizer, event, null);
        assignmentList.add(organizerAssignment);
        return event;
    }


    private Staff getEventStaff(){
        return new Staff(getStaff());
    }


    public Assignment addMentor(User mentor){
        return getEventStaff().addMentor(mentor);
    }

    public Assignment addJudge(User judge){
        return getEventStaff().addJudge(judge);
    }

    public Assignment updateMentor(User mentor, Team team){
        return getEventStaff().updateMentor(mentor, team);
    }

    public boolean isDeletable(){
        return this.status==EventStatus.CLOSED;
    }

    public boolean closeEvent(){
        //TODO:
        return false;
    }



}
