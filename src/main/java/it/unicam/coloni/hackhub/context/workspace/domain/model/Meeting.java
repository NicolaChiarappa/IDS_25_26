package it.unicam.coloni.hackhub.context.workspace.domain.model;

import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Meeting extends BaseEntity {

    private Long teamId;

    private Long mentorId;

    private LocalDateTime date;

    private String meetingLink;

    private Long ticketId;

    private Long eventId;

    @ManyToOne
    @JsonIgnore
    private Workspace workspace;

    public void linkToTicket(Long ticketId) {
        this.ticketId = ticketId;
    }

    public boolean isUpcoming() {
        return this.date != null && this.date.isAfter(LocalDateTime.now());
    }
}
