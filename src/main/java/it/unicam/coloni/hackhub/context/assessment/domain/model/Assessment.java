package it.unicam.coloni.hackhub.context.assessment.domain.model;

import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Assessment extends BaseEntity {

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Column(name = "judge_id", nullable = false)
    private Long judgeId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(
            name = "assessment_votes",
            joinColumns = @JoinColumn(name = "assessment_id")
    )
    @MapKeyColumn(name = "criteria_name")
    @Column(name = "score")
    private Map<String, Integer> votes = new HashMap<>();


    public Double getAverageScore() {
        if (votes.isEmpty()) return 0.0;
        return votes.values().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }
}