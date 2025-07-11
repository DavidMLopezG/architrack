package com.architrack.userservice.service;

import com.architrack.userservice.entity.Player;
import com.architrack.userservice.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

