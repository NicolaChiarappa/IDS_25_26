package it.unicam.coloni.hackhub.context.identity.application.dtos;

import it.unicam.coloni.hackhub.context.identity.domain.models.Gender;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.Date;

@Data
@NoArgsConstructor
public class BaseUserDTO {

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

    private String role;
}
