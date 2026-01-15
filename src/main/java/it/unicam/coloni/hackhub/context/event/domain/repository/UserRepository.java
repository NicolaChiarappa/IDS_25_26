package it.unicam.coloni.hackhub.context.event.domain.repository;

import it.unicam.coloni.hackhub.context.event.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
