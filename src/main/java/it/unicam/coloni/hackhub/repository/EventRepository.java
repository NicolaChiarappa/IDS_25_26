package it.unicam.coloni.hackhub.repository;

import it.unicam.coloni.hackhub.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
