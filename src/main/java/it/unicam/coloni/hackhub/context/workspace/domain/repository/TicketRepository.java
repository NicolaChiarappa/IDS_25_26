package it.unicam.coloni.hackhub.context.workspace.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.coloni.hackhub.context.workspace.domain.model.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByEventId(Long eventId);
    List<Ticket> findAllByEventIdAndTeamId(Long eventId, Long teamId);
}
