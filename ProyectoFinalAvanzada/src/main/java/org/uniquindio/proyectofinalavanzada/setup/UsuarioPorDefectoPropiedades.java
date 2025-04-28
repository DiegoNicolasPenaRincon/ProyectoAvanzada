package org.uniquindio.proyectofinalavanzada.setup;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.uniquindio.proyectofinalavanzada.domain.Rol;

import java.util.List;

@Getter
@ConfigurationProperties(prefix = "default-users")
@RequiredArgsConstructor(onConstructor_ = @ConstructorBinding)
public class UsuarioPorDefectoPropiedades {
    private final List<UsuarioPorDefecto> users;
    public record UsuarioPorDefecto(String usuario, String contrasenia, Rol rol) {}
}
