package it.unicam.coloni.hackhub.context.event.domain.repository;

import it.unicam.coloni.hackhub.context.event.domain.model.Assignment;
import it.unicam.coloni.hackhub.context.event.domain.model.DateRange;
import it.unicam.coloni.hackhub.context.event.domain.model.Event;
import it.unicam.coloni.hackhub.context.event.domain.model.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {


    @Query("SELECT e.runningPeriod FROM Assignment a JOIN a.event e WHERE e.status <> :status AND a.userId = :userId")
    List<DateRange> findPeriodsOnEventStatusIsNot(@Param("status") EventStatus status, @Param("userId") Long userId);

    default List<DateRange> findBusyPeriodByUserId(Long userId){
        return findPeriodsOnEventStatusIsNot(EventStatus.CLOSED, userId);
    }

    List<Assignment> findAllByEventAndUserId(Event event, Long userId);
}
