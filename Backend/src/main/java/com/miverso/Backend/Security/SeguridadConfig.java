package com.miverso.Backend.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration // Marca esta clase como configuración de Spring
@EnableMethodSecurity // Permite usar anotaciones como @PreAuthorize en tus métodos
public class SeguridadConfig {

    // Bean para encriptar contraseñas con BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configura la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Desactiva CSRF si estás haciendo una API REST
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll() // Endpoints públicos
                .requestMatchers("/api/admin/**").hasRole("ADMIN") // Solo para ADMIN
                .anyRequest().authenticated() // Todo lo demás necesita autenticación
            )
            .httpBasic(); // Usa autenticación básica (puedes cambiar por formLogin si usas frontend con login)

        return http.build();
    }
}

