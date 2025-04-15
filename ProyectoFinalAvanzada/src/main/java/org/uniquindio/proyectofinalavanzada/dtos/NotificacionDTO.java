package org.uniquindio.proyectofinalavanzada.dtos;

import jakarta.validation.constraints.*;

public record NotificacionDTO(
        @NotBlank String usuarioId,
        @NotBlank String reporteId,
        @NotBlank String mensaje
) {}