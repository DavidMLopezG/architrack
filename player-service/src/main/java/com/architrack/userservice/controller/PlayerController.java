package com.architrack.userservice.controller;


import com.architrack.userservice.entity.Player;
import com.architrack.userservice.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API REST para gestión de usuarios.
 */
@RestController
//Cambiar ese endopoint por una constante
@RequestMapping("api/users")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService service;

    @GetMapping
    public List<Player> all(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Player> one(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Player> create(@RequestBody Player newPlayer){
        return ResponseEntity.ok(service.create(newPlayer));
    }

    @PutMapping("{/id}")
    public ResponseEntity<Player> update(@PathVariable Long id, @RequestBody Player updatePlayer) {
        return ResponseEntity.ok(service.update(id, updatePlayer));

    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}



