package org.uniquindio.proyectofinalavanzada.dtos;

import org.uniquindio.proyectofinalavanzada.domain.Ubicacion;

public record UsuarioResponseDTO(
        String id,
        String nombre,
        String ciudadResidencia,
        String telefono,
        String direccion,
        String correo,
        Ubicacion ubicacion
) {}