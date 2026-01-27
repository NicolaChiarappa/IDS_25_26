package it.unicam.coloni.hackhub.context.assessment.domain.model;

import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "winners", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"event_id", "rank_position"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Winner extends BaseEntity {

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Column(name = "total_score", nullable = false)
    private Double totalScore;

    @Column(name = "rank_position", nullable = false)
    private Integer rankPosition;

}