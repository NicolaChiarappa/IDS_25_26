package it.unicam.coloni.hackhub.context.event.domain.repository;

import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
