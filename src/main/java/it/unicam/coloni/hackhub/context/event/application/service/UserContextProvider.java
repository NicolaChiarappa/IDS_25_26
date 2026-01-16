package it.unicam.coloni.hackhub.context.event.application.service;

import it.unicam.coloni.hackhub.context.event.domain.model.StaffMember;

public interface UserContextProvider {
    StaffMember getCurrentUser();
}