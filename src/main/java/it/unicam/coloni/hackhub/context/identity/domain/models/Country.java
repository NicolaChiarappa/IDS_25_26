package it.unicam.coloni.hackhub.context.identity.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "country")
public class Country {

    @Column
    private String name;

    @Column
    @Id
    private Long id;




}
