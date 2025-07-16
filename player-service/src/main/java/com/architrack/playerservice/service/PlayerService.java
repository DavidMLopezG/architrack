package com.architrack.playerservice.service;

import com.architrack.playerservice.entity.Player;
import com.architrack.playerservice.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Servicio para operaciones CRUD de Player.
 */
@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository repository;

    public List<Player> findAll() {
        return repository.findAll();
    }

    public Player findById(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Usuario no encontrado con ID:" + id));
    }

    /**
     * Busca un jugador por nombre.
     */
    public Optional<Player> findByName(String name) {
        return repository.findByPlayerName(name);
    }

    public Player create(Player newPlayer){
        return repository.save(newPlayer);
    }

    public Player update(Long id, Player updatePlayer){
        Player existing = findById(id);
        existing.setPlayerName(updatePlayer.getPlayerName());
        existing.setPassword(updatePlayer.getPassword());
        existing.setRole(updatePlayer.getRole());
        return repository.save(existing);
    }

    public void delete(Long id){
        repository.deleteById(id);

    }


}

