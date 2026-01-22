package it.unicam.coloni.hackhub.context.notification.application;

import it.unicam.coloni.hackhub.context.identity.application.service.UserRegistrationObserver;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationSender implements NotificationSender, UserRegistrationObserver {
    @Override
    public void onUserRegistered(User user) {
        this.send(user.getEmail());
    }

    @Override
    public void send(String message) {
        System.out.println("Email notification sender: user registered with email "+message);
    }
}
