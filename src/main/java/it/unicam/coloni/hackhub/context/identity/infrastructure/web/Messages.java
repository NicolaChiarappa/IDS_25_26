package it.unicam.coloni.hackhub.context.identity.infrastructure.web;

public interface Messages {

    interface Errors{
        String VALIDATION_ERROR = "Validation error occurred";
        String DATABASE_ERROR = "Database error occurred";
    }

    interface Success{
        String ACCOUNT_FOUND = "Account data retrieved";
        String ACCOUNT_MODIFIED = "Account modified successfully";
        String ACCOUNT_CREATED = "Account created successfully";
        String USER_LOGGED_IN = "User logged successfully";
    }



}
