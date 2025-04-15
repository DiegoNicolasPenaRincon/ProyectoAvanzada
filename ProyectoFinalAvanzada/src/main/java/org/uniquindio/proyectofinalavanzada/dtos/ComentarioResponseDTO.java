package org.uniquindio.proyectofinalavanzada.dtos;

public record ComentarioResponseDTO(
        String id,
        String usuarioId,
        String contenido,
        String fecha
) {}