package org.uniquindio.proyectofinalavanzada.dtos;

import org.uniquindio.proyectofinalavanzada.domain.GeoPoint;

public record UsuarioEditarDTO(
        String nombre,
        String ciudadResidencia,
        String telefono,
        String direccion,
        String correo,
        String contraseña,
        GeoPoint ubicacion
) {}