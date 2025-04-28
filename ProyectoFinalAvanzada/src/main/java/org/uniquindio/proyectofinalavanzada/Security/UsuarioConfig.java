package org.uniquindio.proyectofinalavanzada.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.uniquindio.proyectofinalavanzada.repositories.UsuarioRepository;
import org.uniquindio.proyectofinalavanzada.dtos.DetallesConsumidor;

@Configuration
public class UsuarioConfig {
    @Bean
    public UserDetailsService userDetailsServiceFromDataBase(
            UsuarioRepository userRepository){
        return username -> userRepository.findUsuarioByCorreo(username)
                .map(DetallesConsumidor::new)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Usuario no encontrado"));

    }
}
