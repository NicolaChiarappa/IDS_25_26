package it.unicam.coloni.hackhub.context.assessment.domain.repository;

import it.unicam.coloni.hackhub.context.assessment.domain.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    List<Assessment> findByEventId(Long eventId);

    List<Assessment> findByTeamId(Long teamId);

    boolean existsByEventIdAndTeamIdAndJudgeId(Long eventId, Long teamId, Long judgeId);
}