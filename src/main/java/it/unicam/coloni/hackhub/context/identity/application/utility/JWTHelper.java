package it.unicam.coloni.hackhub.context.identity.application.utility;

import org.springframework.security.core.Authentication;

public interface JWTHelper {



    String generate(Authentication user, Integer duration);

    String extractUsername(String token);


}
