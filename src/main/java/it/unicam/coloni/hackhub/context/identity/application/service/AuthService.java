package it.unicam.coloni.hackhub.context.identity.application.service;

import it.unicam.coloni.hackhub.context.identity.application.dto.UserDto;
import it.unicam.coloni.hackhub.context.identity.application.dto.request.LoginRequest;
import it.unicam.coloni.hackhub.context.identity.application.dto.request.SignUpRequest;
import it.unicam.coloni.hackhub.context.identity.application.dto.response.LoginResponse;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;


public interface AuthService {

    LoginResponse logIn(LoginRequest request);

    UserDto signUp(SignUpRequest request);

    User getLoggedUser();




}
