package it.unicam.coloni.hackhub.context.identity.infrastructure.security;

import it.unicam.coloni.hackhub.context.identity.application.utility.PasswordHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Data
@Component
public class SpringPasswordHelper implements PasswordHelper {

    @Autowired
    PasswordEncoder encoder;


    @Override
    public String encode(String password){
        return encoder.encode(password);
    }

    @Override
    public boolean match(String password, String hash){
        return encoder.matches(password,hash);
    }

}
