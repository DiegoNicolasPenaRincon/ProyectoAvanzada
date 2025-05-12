package org.uniquindio.proyectofinalavanzada.setup;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.domain.UsuarioEstado;
import org.uniquindio.proyectofinalavanzada.repositories.UsuarioRepository;

import java.time.LocalDate;
import java.util.UUID;
@Component
@RequiredArgsConstructor
public class DefaultUserInitializer implements CommandLineRunner {
    private final DefaultUserProperties defaultUserProperties;
    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            defaultUserProperties.getUsers().stream()

                    .map(this::createUser).forEach(userRepository::save);

        }
    }
    private Usuario createUser(DefaultUserProperties.DefaultUser defaultUser){
        return Usuario.builder()
                .id(UUID.randomUUID().toString())
                .nombre(defaultUser.username())
                .ciudadResidencia("Desconocida")
                .telefono("0000000")
                .direccion("No especificada")
                .correo(defaultUser.username() + "@mail.com") // ajusta si quieres
                .contraseña(passwordEncoder.encode(defaultUser.password()))
                .rol(defaultUser.role())
                .verificado(true)
                .estado(UsuarioEstado.ACTIVO)
                .build();
    }
}