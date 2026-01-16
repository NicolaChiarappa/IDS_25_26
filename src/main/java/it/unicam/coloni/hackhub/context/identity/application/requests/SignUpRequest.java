package it.unicam.coloni.hackhub.context.identity.application.requests;

import it.unicam.coloni.hackhub.context.identity.domain.models.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public final class SignUpRequest extends UserRequest {

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

    @NotBlank
    private String role;

}
