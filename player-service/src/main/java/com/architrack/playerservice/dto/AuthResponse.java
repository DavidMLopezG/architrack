package com.architrack.playerservice.dto;

import com.architrack.playerservice.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que contiene el JWT y la información del jugador.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    public String token; // JSON Web Token generado
    private String player; // Datos del jugador (sin password)
}
