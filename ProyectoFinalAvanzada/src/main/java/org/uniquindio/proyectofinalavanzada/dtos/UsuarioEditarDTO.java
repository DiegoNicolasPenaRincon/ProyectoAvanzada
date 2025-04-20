package org.uniquindio.proyectofinalavanzada.dtos;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.uniquindio.proyectofinalavanzada.domain.GeoPoint;

public record UsuarioEditarDTO(
        String nombre,
        String ciudadResidencia,
        String telefono,
        String direccion,
        String correo,
        String contraseña,
        GeoJsonPoint ubicacion
) {}