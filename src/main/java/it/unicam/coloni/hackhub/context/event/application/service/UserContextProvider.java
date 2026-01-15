package it.unicam.coloni.hackhub.context.event.application.service;

import it.unicam.coloni.hackhub.context.event.domain.model.User;

public interface UserContextProvider {
    User getCurrentUser();
}