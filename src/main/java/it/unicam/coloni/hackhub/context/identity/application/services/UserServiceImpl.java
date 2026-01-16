package it.unicam.coloni.hackhub.context.identity.application.services;

import it.unicam.coloni.hackhub.context.identity.application.dtos.BaseUserDTO;
import it.unicam.coloni.hackhub.context.identity.application.mappers.UserMapper;
import it.unicam.coloni.hackhub.context.identity.application.requests.LoginRequest;
import it.unicam.coloni.hackhub.context.identity.application.requests.SignUpRequest;
import it.unicam.coloni.hackhub.context.identity.application.responses.LoginResponse;
import it.unicam.coloni.hackhub.context.identity.application.utilities.JWTHelper;
import it.unicam.coloni.hackhub.context.identity.application.utilities.PasswordHelper;
import it.unicam.coloni.hackhub.context.identity.domain.models.User;
import it.unicam.coloni.hackhub.context.identity.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper  userMapper;
    private final PasswordHelper passwordHandler;
    private final JWTHelper jwtHelper;

    @Autowired
    public UserServiceImpl(UserRepository repo, UserMapper mapper, PasswordHelper passHandler, JWTHelper jHelper ){
        userRepository= repo;
        userMapper = mapper;
        passwordHandler = passHandler;
        jwtHelper= jHelper;
    }

    @Override
    public LoginResponse logIn(LoginRequest request)  {
        User user = userMapper.fromLogin(request);
        user = userRepository.findByUsername(user.getUsername()).orElseThrow();
        if(passwordHandler.match(request.getPassword(), user.getPassword())){
            return new LoginResponse(jwtHelper.generate(user, 1));
        }else {
            throw new UnsupportedOperationException("Invalid credentials");
        }

    }




    @Override
    public BaseUserDTO signUp(SignUpRequest request){
       User user = userMapper.fromSignUp(request);
       User saved = userRepository.save(user);
       return userMapper.toDto(saved);
    }



}
