package com.architrack.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad JPA que representa un usuario en el sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Players")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String playerName;

    @Column(nullable = false)
    private String password;

    private String role;

}
