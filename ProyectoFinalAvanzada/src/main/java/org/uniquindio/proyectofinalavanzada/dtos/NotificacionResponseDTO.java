package org.uniquindio.proyectofinalavanzada.dtos;

public record NotificacionResponseDTO(
        String id,
        String usuarioId,
        String reporteId,
        String mensaje,
        String fecha,
        String enlaceReporte,
        boolean leida
) {}