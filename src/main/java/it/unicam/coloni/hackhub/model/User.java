package it.unicam.coloni.hackhub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;


@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"}),
                @UniqueConstraint(columnNames = {"email"})
        }
)
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity{

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Assignment> assignment;

}
