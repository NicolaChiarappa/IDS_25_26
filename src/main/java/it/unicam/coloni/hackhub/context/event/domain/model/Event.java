package it.unicam.coloni.hackhub.context.event.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import it.unicam.coloni.hackhub.shared.domain.enums.PlatformRoles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity {

    public static Event fromOrganizer(StaffMember organizer) {
        if (organizer.getRole() != PlatformRoles.ORGANIZER) {
            throw new IllegalArgumentException("The provided user is not an organizer");
        }
        Event event = new Event();
        List<Assignment> assignmentList = new ArrayList<>();
        event.staff = assignmentList;
        Assignment organizerAssignment = new Assignment(organizer.getId(), null, PlatformRoles.ORGANIZER, event);
        assignmentList.add(organizerAssignment);
        return event;
    }

    @Column
    private String name;

    @OneToMany(targetEntity = Assignment.class, mappedBy = "event", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private List<Assignment> staff;

    @Embedded
    private DateRange runningPeriod;

    @Column
    private String rulesUrl;

    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.PRIVATE)
    private EventStatus status;

    private Staff getEventStaff() {
        return new Staff(getStaff());
    }

    public Assignment addMentor(StaffMember member, List<DateRange> busyPeriods) {

        if (busyPeriods.stream()
                .anyMatch(period -> period.overlap(this.runningPeriod))) {
            throw new IllegalStateException("The given user is not available in the period of the event");
        } else {
            return getEventStaff().addMentor(member);
        }
    }

    public Assignment addJudge(StaffMember judge, List<DateRange> busyPeriods) {
        for(DateRange range : busyPeriods){
            if(range.overlap(this.runningPeriod)){
                throw new IllegalStateException("The given user is not available in the period of the event");
            }
        }
        return getEventStaff().addJudge(judge);
    }

    public Assignment updateMentor(StaffMember mentor, Long teamId) {
        return getEventStaff().updateMentor(mentor, teamId);
    }

    public void delete() {
        this.checkIfDeletable();
        this.setDeletedAt(LocalDateTime.now());
    }

    private void checkIfDeletable() {
        if (this.getStatus() != EventStatus.CLOSED) {
            throw new IllegalStateException("Unable to delete this event, its current status is: " + this.getStatus());
        }
    }

    private void checkAvailability(List<DateRange> busyPeriods) {
        if (busyPeriods.stream()
                .anyMatch(period -> period.overlap(this.runningPeriod))) {
            throw new IllegalStateException("The given user is not available in the period of the event");
        }
    }

    public void openSubscription() {
        this.status = EventStatus.SUBSCRIPTION;
    }

    public void closeSubscriptions() {
        nextStatus(EventStatus.SUBSCRIPTION, EventStatus.WAITING);
    }

    public void startEvent() {
        nextStatus(EventStatus.WAITING, EventStatus.RUNNING);
    }

    public void stopEvent() {
        nextStatus(EventStatus.RUNNING, EventStatus.EVALUATING);
    }

    public void stopValuating() {
        nextStatus(EventStatus.EVALUATING, EventStatus.EVALUATED);
    }

    public void closeEvent() {
        nextStatus(EventStatus.EVALUATED, EventStatus.CLOSED);
    }

    private void nextStatus(EventStatus prev, EventStatus next) {
        if (this.status == prev) {
            setStatus(next);
        } else {
            throw new IllegalStateException("The status of the event cannot be modified");
        }
    }

}
