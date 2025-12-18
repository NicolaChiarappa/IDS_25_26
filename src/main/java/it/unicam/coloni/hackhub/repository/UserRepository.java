package it.unicam.coloni.hackhub.repository;

import it.unicam.coloni.hackhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
