package it.unicam.coloni.hackhub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity {

    @Column
    private String name;


    @OneToMany(targetEntity= Assignment.class, mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private List<Assignment> assignments;

    @Embedded
    private DateRange runningPeriod;

    @Enumerated(EnumType.STRING)
    private EventStatus status;


    public Staff getStaff(){
        return new Staff(getAssignments());
    }


    public Assignment addMentor(User mentor){
        return getStaff().addMentor(mentor);
    }

    public Assignment addJudge(User judge){
        return getStaff().addJudge(judge);
    }

    public boolean isDeletable(){
        return this.status==EventStatus.CLOSED;
    }

    public boolean closeEvent(){
        //TODO:
        return false;
    }



}
