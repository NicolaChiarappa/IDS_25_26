package it.unicam.coloni.hackhub.context.identity.application.dto;

import it.unicam.coloni.hackhub.context.identity.domain.model.Gender;
import it.unicam.coloni.hackhub.shared.domain.enums.PlatformRoles;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private Date birthDate;

    @Email
    private String email;

    private Gender gender;

    @URL
    private String photoUrl;

    private PlatformRoles role;
}
