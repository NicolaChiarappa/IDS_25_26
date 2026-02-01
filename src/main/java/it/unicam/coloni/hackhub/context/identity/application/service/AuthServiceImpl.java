package it.unicam.coloni.hackhub.context.identity.application.service;

import it.unicam.coloni.hackhub.context.identity.application.dto.UserDto;
import it.unicam.coloni.hackhub.context.identity.application.mapper.UserMapper;
import it.unicam.coloni.hackhub.context.identity.application.dto.request.LoginRequest;
import it.unicam.coloni.hackhub.context.identity.application.dto.request.SignUpRequest;
import it.unicam.coloni.hackhub.context.identity.application.dto.response.LoginResponse;
import it.unicam.coloni.hackhub.context.identity.application.utility.JWTHelper;
import it.unicam.coloni.hackhub.context.identity.application.utility.PasswordHelper;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
import it.unicam.coloni.hackhub.context.identity.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JWTHelper jwtHelper;
    private final AuthenticationManager authenticationManager;
    private final PasswordHelper passwordHelper;

    private final List<UserRegistrationObserver> userRegistrationObservers;


    @Autowired
    public AuthServiceImpl(UserRepository repo, UserMapper mapper, PasswordHelper passHandler, JWTHelper jHelper, AuthenticationManager authManager, List<UserRegistrationObserver> observers) {
        userRepository = repo;
        userMapper = mapper;
        jwtHelper = jHelper;
        authenticationManager = authManager;
        passwordHelper = passHandler;
        userRegistrationObservers = observers;
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
    @Transactional
    public UserDto signUp(SignUpRequest request){
       User user = userMapper.fromSignUp(request);
       user.setPassword(passwordHelper.encode(request.getPassword()));
       User saved = userRepository.save(user);
       notifyObservers(user);
       return userMapper.toDto(saved);
    }

    @Override
    public User getLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("No logged user");
        }
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Logged user not found in database"));


    }

    private void notifyObservers(User user){
        for(UserRegistrationObserver observer: userRegistrationObservers){
            try {
                observer.onUserRegistered(user);
            } catch (Exception e) {
                System.err.println("Error while notifying observer: " + e.getMessage());
            }
        }
    }





}
