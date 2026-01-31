package it.unicam.coloni.hackhub.context.workspace.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Report extends BaseEntity {

    private Long teamId;

    private Long authorId;

    private Long eventId;

    private String description;

    @ManyToOne
    @JsonIgnore
    private Workspace workspace;
}
