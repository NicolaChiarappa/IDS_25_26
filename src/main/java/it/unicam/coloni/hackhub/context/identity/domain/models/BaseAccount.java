package it.unicam.coloni.hackhub.context.identity.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;


@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "role")
    private AccountRole role;

    @Column(unique = true, name = "username")
    private String username;

    @Column
    private String passwordHash;


    @Column(name = "photoUrl")
    private String photoUrl;

    @Email
    @Column(name = "email")
    private String email;

}
