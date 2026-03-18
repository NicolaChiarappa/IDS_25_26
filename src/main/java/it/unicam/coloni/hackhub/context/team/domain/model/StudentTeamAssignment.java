package it.unicam.coloni.hackhub.context.team.domain.model;

import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTeamAssignment extends BaseEntity {
    private Long userId;
    private Long teamId;
}
