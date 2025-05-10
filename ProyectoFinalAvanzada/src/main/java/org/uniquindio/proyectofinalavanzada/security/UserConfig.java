package org.uniquindio.proyectofinalavanzada.security;

import org.uniquindio.proyectofinalavanzada.dtos.CustomUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.uniquindio.proyectofinalavanzada.repositories.UsuarioRepository;

@Configuration
public class UserConfig {
    @Bean
    public UserDetailsService userDetailsServiceFromDataBase(
            UsuarioRepository userRepository){
        return username -> userRepository.findUserByEmail(username)
                .map(CustomUserDetails::new)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Usuario no encontrado"));

    }
}