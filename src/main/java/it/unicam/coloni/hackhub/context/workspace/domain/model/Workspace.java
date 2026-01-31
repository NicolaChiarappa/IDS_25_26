package it.unicam.coloni.hackhub.context.workspace.domain.model;

import it.unicam.coloni.hackhub.context.event.domain.model.DateRange;
import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Workspace extends BaseEntity {

    private static final int MAX_OPEN_TICKETS = 3;

    private Long eventId;

    private Long teamId;

    private Long mentorId;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meeting> meetings = new ArrayList<>();

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    public Ticket openTicket(String description, Integer urgency) {
        long openTicketsCount = tickets.stream()
                .filter(t -> t.getStatus() == TicketStatus.OPEN || t.getStatus() == TicketStatus.IN_PROGRESS)
                .count();

        if (openTicketsCount >= MAX_OPEN_TICKETS) {
            throw new IllegalStateException(
                    "Too many open tickets. Please resolve existing ones before opening a new one.");
        }

        Ticket ticket = new Ticket();
        ticket.setTeamId(this.teamId);
        ticket.setEventId(this.eventId);
        ticket.setDescription(description);
        ticket.setUrgency(urgency);
        ticket.setWorkspace(this);

        tickets.add(ticket);
        return ticket;
    }

    public Meeting scheduleMeeting(Long mentorId, LocalDateTime date, String link, DateRange eventRange) {
        if (!eventRange.contains(date)) {
            throw new IllegalArgumentException("Meeting date " + date + " is outside the event running period.");
        }

        Meeting meeting = new Meeting();
        meeting.setTeamId(this.teamId);
        meeting.setMentorId(mentorId);
        meeting.setDate(date);
        meeting.setMeetingLink(link);
        meeting.setEventId(this.eventId);
        meeting.setWorkspace(this);

        meetings.add(meeting);
        return meeting;
    }

    public void submitReport(Long authorId, String description) {
        Report report = new Report();
        report.setTeamId(this.teamId);
        report.setAuthorId(authorId);
        report.setEventId(this.eventId);
        report.setDescription(description);
        report.setWorkspace(this);

        reports.add(report);
    }

    public WorkloadSummary getWorkloadSummary() {
        long openTickets = tickets.stream()
                .filter(t -> t.getStatus() != TicketStatus.RESOLVED)
                .count();

        long scheduledMeetings = meetings.stream()
                .filter(Meeting::isUpcoming)
                .count();

        long submittedReports = reports.size();

        return new WorkloadSummary(openTickets, scheduledMeetings, submittedReports);
    }
}
