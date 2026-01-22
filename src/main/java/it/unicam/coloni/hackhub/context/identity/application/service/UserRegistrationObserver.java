package it.unicam.coloni.hackhub.context.identity.application.service;

import it.unicam.coloni.hackhub.context.identity.domain.model.User;

public interface UserRegistrationObserver {

    void onUserRegistered(User user);
}
