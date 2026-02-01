package it.unicam.coloni.hackhub.context.notification.application;

import it.unicam.coloni.hackhub.context.workspace.application.service.MeetingObserver;
import it.unicam.coloni.hackhub.context.workspace.domain.model.Meeting;
import org.springframework.stereotype.Component;

@Component
public class CalendarServiceImpl implements CalendarService, MeetingObserver {
    @Override
    public void doActionOnMeetingScheduled(Meeting meeting) {
        sendCalendar(meeting.getMeetingLink());
    }

    @Override
    public void sendCalendar(String link) {
        System.out.println("Calendar sent to "+ link);
    }
}
