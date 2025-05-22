package org.uniquindio.proyectofinalavanzada.dtos;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.uniquindio.proyectofinalavanzada.domain.GeoPoint;
import org.uniquindio.proyectofinalavanzada.domain.Rol;

public record UsuarioResponseDTO(
        String id,
        String nombre,
        String ciudadResidencia,
        String telefono,
        String direccion,
        String correo,
        GeoJsonPoint ubicacion,
        Rol rol
) {}