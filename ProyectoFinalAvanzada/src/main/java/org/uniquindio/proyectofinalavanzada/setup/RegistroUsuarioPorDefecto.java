package org.uniquindio.proyectofinalavanzada.setup;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.repositories.UsuarioRepository;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistroUsuarioPorDefecto implements CommandLineRunner {
    private final UsuarioPorDefectoPropiedades defaultUserProperties;
    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            defaultUserProperties.getUsers().stream()

                    .map(this::createUser).forEach(userRepository::save);

        }
    }

    private Usuario createUser(UsuarioPorDefectoPropiedades.UsuarioPorDefecto usuario) {
        return new Usuario(UUID.randomUUID().toString(), usuario.usuario(),
                passwordEncoder.encode(usuario.contrasenia()),
                usuario.usuario(), LocalDate.of(1982, 8, 27),
                usuario.rol(), UserStatus.ACTIVE);

    }
}
