package org.uniquindio.proyectofinalavanzada.dtos;

import org.uniquindio.proyectofinalavanzada.domain.GeoPoint;

public record UsuarioResponseDTO(
        String id,
        String nombre,
        String ciudadResidencia,
        String telefono,
        String direccion,
        String correo,
        GeoPoint ubicacion
) {}