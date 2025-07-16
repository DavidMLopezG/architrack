package com.architrack.playerservice.controller;

import com.architrack.playerservice.dto.AuthResponse;
import com.architrack.playerservice.entity.Player;
import com.architrack.playerservice.service.PlayerService;
import com.architrack.playerservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controlador que expone endpoints de autenticación.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PlayerService playerService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    /**
     * Registra un jugador nuevo en la base de datos.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Player newPlayer) {
        newPlayer.setPassword(new BCryptPasswordEncoder().encode(newPlayer.getPassword()));
        playerService.create(newPlayer);
        return ResponseEntity.ok("Jugador registrado con exito");
    }

    /**
     * Autentica al jugador y devuelve un token JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody Player playerLogin) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(playerLogin.getId(), playerLogin.getPassword())
        );
        String token = jwtUtil.generateToken(playerLogin.getPlayerName());
        Player player = playerService.findById(playerLogin.getId());
        player.setPassword(null);
        return ResponseEntity.ok(
                AuthResponse.builder()
                        .token(token)
                        .player(String.valueOf(playerLogin))
                        .build()
        );
    }
}
