package it.unicam.coloni.hackhub.context.workspace.domain.repository;

import it.unicam.coloni.hackhub.context.workspace.domain.model.Workspace;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    Workspace findByEventIdAndTeamId(Long eventId, Long teamId);

    List<Workspace> findByEventIdAndMentorId(Long eventId, Long mentorId);

}
