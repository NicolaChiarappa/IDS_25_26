package it.unicam.coloni.hackhub.context.identity.application.utility;

import org.mapstruct.Named;

public interface PasswordHelper {

    @Named("encodePassword")
    String encode(String password);

    boolean match(String hashedPassword, String clearPassword);
}
