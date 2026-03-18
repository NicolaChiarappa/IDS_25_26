package it.unicam.coloni.hackhub.context.team.domain.model;

import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team extends BaseEntity {

    @Column
    private String name;

    @Column
    private Long ownerId;

//    @OneToMany
//    private List<Long> teamPartecipants;

}
