package it.unicam.coloni.hackhub.context.workspace.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.coloni.hackhub.context.workspace.domain.model.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
