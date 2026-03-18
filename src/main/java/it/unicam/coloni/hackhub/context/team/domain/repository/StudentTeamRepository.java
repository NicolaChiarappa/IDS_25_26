package it.unicam.coloni.hackhub.context.team.domain.repository;

import it.unicam.coloni.hackhub.context.team.domain.model.StudentTeamAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentTeamRepository extends JpaRepository<StudentTeamAssignment, Long> {


   List<StudentTeamAssignment> findAllByUserId(Long id);
}
