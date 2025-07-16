package com.architrack.playerservice.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

/**
 * Utilidad para generar y validar JSON Web Tokens (JWT).
 */
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;              // Clave secreta para firmar tokens

    @Value("${jwt.expiration-ms}")
    private long expirationMs;         // Tiempo de vida en milisegundos

    /**
     * Genera un JWT para el usuario especificado.
     * @param username nombre de usuario (subject)
     * @return token JWT firmado
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * Valida que el token coincida con el usuario y no esté expirado.
     * @param token JWT recibido
     * @param username nombre de usuario esperado
     * @return true si es válido
     */
    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username)
                && !isTokenExpired(token);
    }

    /**
     * Comprueba si el token ha expirado.
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration)
                .before(new Date());
    }

    /**
     * Extrae un claim específico usando una función resolutora.
     */
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return resolver.apply(claims);
    }

    /**
     * Obtiene el subject (username) del token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}

