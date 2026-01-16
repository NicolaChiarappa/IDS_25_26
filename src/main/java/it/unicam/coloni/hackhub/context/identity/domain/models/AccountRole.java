package it.unicam.coloni.hackhub.context.identity.domain.models;

import lombok.Getter;

@Getter
public enum AccountRole {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");




    private final String desctiption;

    AccountRole(String role){
        this.desctiption=role;
    }
}
