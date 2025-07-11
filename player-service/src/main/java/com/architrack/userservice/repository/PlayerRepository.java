package com.architrack.userservice.repository;


import com.architrack.userservice.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Player.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByPlayerName(String playerName);

}
