package it.unicam.coloni.hackhub.context.team.domain.repository;

import it.unicam.coloni.hackhub.context.team.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
