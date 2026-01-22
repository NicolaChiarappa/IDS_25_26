package it.unicam.coloni.hackhub.context.identity.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SignUpRequest extends UserRequest {

    @NotBlank
    private String role;

}
