package it.unicam.coloni.hackhub.context.identity.application.utilities;

import it.unicam.coloni.hackhub.context.identity.domain.models.User;

public interface JWTHelper {



    String generate(User user, Integer duration);

    String extractUsername(String token);


}
