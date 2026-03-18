package it.unicam.coloni.hackhub.context.team.domain.model;

import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class TeamEventAssignment extends BaseEntity {

    @Column
    private Long eventId;

    @Column
    private Long teamId;
}
