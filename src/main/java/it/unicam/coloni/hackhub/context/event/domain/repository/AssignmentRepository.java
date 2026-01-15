package it.unicam.coloni.hackhub.context.event.domain.repository;

import it.unicam.coloni.hackhub.context.event.domain.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {


}
