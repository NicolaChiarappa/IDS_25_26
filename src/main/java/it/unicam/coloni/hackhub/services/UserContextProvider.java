package it.unicam.coloni.hackhub.services;

import it.unicam.coloni.hackhub.model.User;

public interface UserContextProvider {
    User getCurrentUser();
}