package it.unicam.coloni.hackhub.context.identity.application.services;

import it.unicam.coloni.hackhub.context.identity.application.dtos.BaseUserDTO;
import it.unicam.coloni.hackhub.context.identity.application.requests.LoginRequest;
import it.unicam.coloni.hackhub.context.identity.application.requests.SignUpRequest;
import it.unicam.coloni.hackhub.context.identity.application.responses.LoginResponse;

public interface UserService {

    LoginResponse logIn(LoginRequest request);

    BaseUserDTO signUp(SignUpRequest request);




}
