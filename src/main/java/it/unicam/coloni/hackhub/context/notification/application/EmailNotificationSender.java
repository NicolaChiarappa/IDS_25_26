package it.unicam.coloni.hackhub.context.notification.application;

import it.unicam.coloni.hackhub.context.identity.application.service.UserRegistrationObserver;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
import it.unicam.coloni.hackhub.context.workspace.application.service.MeetingObserver;
import it.unicam.coloni.hackhub.context.workspace.domain.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationSender implements NotificationSender<SimpleMailMessage>, UserRegistrationObserver, MeetingObserver {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onUserRegistered(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("nicolachiarappa2001@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Welcome in HackHub");
        mailMessage.setText("Welcome to the hackathon platform");

        this.send(mailMessage);
    }

    @Override
    public void onMeetingScheduled(Meeting meeting) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("nicolachiarappa2001@gmail.com");
        mailMessage.setTo("mock@example.com");
        mailMessage.setSubject("Meeting scheduled");
        mailMessage.setText("this is the link for the meeting "+ meeting.getMeetingLink());

        this.send(mailMessage );
    }



    @Override
    public void send(SimpleMailMessage message ) {
        mailSender.send(message);
    }


}
