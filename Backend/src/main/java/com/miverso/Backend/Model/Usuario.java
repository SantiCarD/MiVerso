package com.miverso.Backend.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Data               // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Constructor vacío (necesario para JPA)
@AllArgsConstructor // Constructor con todos los campos
@Builder            // Permite usar patrón Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;
}
