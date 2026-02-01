package it.unicam.coloni.hackhub.context.identity.domain.model;

import it.unicam.coloni.hackhub.shared.domain.models.BaseEntity;
import it.unicam.coloni.hackhub.shared.domain.enums.PlatformRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


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
@RequiredArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column
    @NonNull
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
    @NotNull
    private PlatformRoles role;

    private Gender gender;

    private LocalDate birthDate;

    private String photoUrl;
}
