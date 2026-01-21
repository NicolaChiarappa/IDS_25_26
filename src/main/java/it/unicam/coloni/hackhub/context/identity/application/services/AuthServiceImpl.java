package it.unicam.coloni.hackhub.context.identity.application.services;

import it.unicam.coloni.hackhub.context.identity.application.dtos.BaseUserDTO;
import it.unicam.coloni.hackhub.context.identity.application.mappers.UserMapper;
import it.unicam.coloni.hackhub.context.identity.application.dtos.requests.LoginRequest;
import it.unicam.coloni.hackhub.context.identity.application.dtos.requests.SignUpRequest;
import it.unicam.coloni.hackhub.context.identity.application.dtos.responses.LoginResponse;
import it.unicam.coloni.hackhub.context.identity.application.utilities.JWTHelper;
import it.unicam.coloni.hackhub.context.identity.application.utilities.PasswordHelper;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
import it.unicam.coloni.hackhub.context.identity.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JWTHelper jwtHelper;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthServiceImpl(UserRepository repo, UserMapper mapper, PasswordHelper passHandler, JWTHelper jHelper, AuthenticationManager authManager) {
        userRepository = repo;
        userMapper = mapper;
        jwtHelper = jHelper;
        authenticationManager = authManager;
    }

    @Override
    public LoginResponse logIn(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        return new LoginResponse(jwtHelper.generate(authentication, 1));
    }






    @Override
    public BaseUserDTO signUp(SignUpRequest request){
       User user = userMapper.fromSignUp(request);
       User saved = userRepository.save(user);
       return userMapper.toDto(saved);
    }



}
