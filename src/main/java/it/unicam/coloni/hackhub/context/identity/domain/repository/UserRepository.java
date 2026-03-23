package it.unicam.coloni.hackhub.context.identity.domain.repository;

import it.unicam.coloni.hackhub.context.identity.application.dto.UserDto;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
import it.unicam.coloni.hackhub.shared.domain.enums.PlatformRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);


    List<User> getAllByRole(PlatformRoles platformRoles);
}

