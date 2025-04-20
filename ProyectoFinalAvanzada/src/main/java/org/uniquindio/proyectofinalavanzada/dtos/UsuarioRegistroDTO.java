package org.uniquindio.proyectofinalavanzada.dtos;

import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.uniquindio.proyectofinalavanzada.domain.GeoPoint;
import org.uniquindio.proyectofinalavanzada.domain.UsuarioEstado;

public record UsuarioRegistroDTO(
        @NotBlank String id,
        @NotBlank String nombre,
        @NotBlank String ciudadResidencia,
        @NotBlank String telefono,
        @NotBlank String direccion,
        @NotBlank @Email String correo,
        @NotBlank @Size(min = 8) String contraseña,
        GeoJsonPoint ubicacion
) {}