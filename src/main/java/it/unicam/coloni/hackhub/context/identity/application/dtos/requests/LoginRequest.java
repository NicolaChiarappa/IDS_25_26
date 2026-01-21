package it.unicam.coloni.hackhub.context.identity.application.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
