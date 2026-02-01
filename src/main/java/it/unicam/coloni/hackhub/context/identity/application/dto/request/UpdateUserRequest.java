package it.unicam.coloni.hackhub.context.identity.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserRequest extends UserRequest{
    @NotNull
    private Long id;
}
