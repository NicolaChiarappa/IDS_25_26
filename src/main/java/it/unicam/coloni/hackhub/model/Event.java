package it.unicam.coloni.hackhub.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Event extends BaseEntity {

    @Column
    private String name;


    @OneToMany(targetEntity= Assignment.class, mappedBy = "event")
    @Getter(AccessLevel.PRIVATE)
    private List<Assignment> assignments;

    @Embedded
    private DateRange runningPeriod;

    @Enumerated(EnumType.STRING)
    private EventStatus status;


    public Staff getStaff(){
        return new Staff(getAssignments());
    }



}
