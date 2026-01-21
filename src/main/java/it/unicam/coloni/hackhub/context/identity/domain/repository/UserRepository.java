package it.unicam.coloni.hackhub.context.identity.domain.repository;

import it.unicam.coloni.hackhub.context.identity.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);


}

