package it.unicam.coloni.hackhub.context.identity.application.services;

import it.unicam.coloni.hackhub.context.identity.application.dtos.BaseUserDTO;
import it.unicam.coloni.hackhub.context.identity.application.dtos.requests.LoginRequest;
import it.unicam.coloni.hackhub.context.identity.application.dtos.requests.SignUpRequest;
import it.unicam.coloni.hackhub.context.identity.application.dtos.responses.LoginResponse;

public interface AuthService {

    LoginResponse logIn(LoginRequest request);

    BaseUserDTO signUp(SignUpRequest request);




}
