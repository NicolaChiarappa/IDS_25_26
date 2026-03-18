package it.unicam.coloni.hackhub.context.team.domain.repository;

import it.unicam.coloni.hackhub.context.team.domain.model.TeamEventAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamEventRepository extends JpaRepository<TeamEventAssignment, Long> {

    List<TeamEventAssignment> getAllByEventId(Long id);
}
