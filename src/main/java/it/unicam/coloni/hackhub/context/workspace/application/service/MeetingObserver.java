package it.unicam.coloni.hackhub.context.workspace.application.service;

import it.unicam.coloni.hackhub.context.workspace.domain.model.Meeting;

public interface MeetingObserver {

    void doActionOnMeetingScheduled(Meeting meeting);

}
