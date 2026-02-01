package it.unicam.coloni.hackhub.context.identity.application.dto.request;


import it.unicam.coloni.hackhub.context.identity.domain.model.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
@Data
public abstract class UserRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Gender gender;

    @URL
    private String photoUrl;


    private LocalDate birthDate;
}
