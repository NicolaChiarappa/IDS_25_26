package it.unicam.coloni.hackhub.context.event.domain.model;


import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import it.unicam.coloni.hackhub.shared.domain.enums.PlatformRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Assignment extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "team_id")
    private Long teamId;

    @Enumerated(EnumType.STRING)
    private PlatformRoles role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;


}
