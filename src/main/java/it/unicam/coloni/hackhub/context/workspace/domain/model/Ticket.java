package it.unicam.coloni.hackhub.context.workspace.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Ticket extends BaseEntity {

    private Long teamId;

    private Long eventId;

    private String description;

    @Min(1)
    @Max(5)
    private Integer urgency;

    @ManyToOne
    @JsonIgnore
    private Workspace workspace;

    private TicketStatus status = TicketStatus.OPEN;

    public void resolve() {
        if (this.status == TicketStatus.RESOLVED) {
            throw new IllegalStateException("Ticket is already resolved");
        }
        this.status = TicketStatus.RESOLVED;
    }

    public void inProgress() {
        if (this.status == TicketStatus.RESOLVED) {
            throw new IllegalStateException("Cannot move a resolved ticket back to in progress");
        }
        this.status = TicketStatus.IN_PROGRESS;
    }
}
