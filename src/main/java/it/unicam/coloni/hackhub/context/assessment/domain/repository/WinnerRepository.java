package it.unicam.coloni.hackhub.context.assessment.domain.repository;

import it.unicam.coloni.hackhub.context.assessment.domain.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, Long> {
    List<Winner> findByEventIdOrderByRankPositionAsc(Long eventId);
    Optional<Winner> findByEventIdAndTeamId(Long eventId, Long teamId);

}