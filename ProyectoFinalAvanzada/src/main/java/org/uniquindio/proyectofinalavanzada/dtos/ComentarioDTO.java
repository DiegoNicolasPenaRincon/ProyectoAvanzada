package org.uniquindio.proyectofinalavanzada.dtos;

import jakarta.validation.constraints.*;

public record ComentarioDTO(
        @NotBlank String usuarioId,
        @NotBlank String reporteId,
        @NotBlank String contenido
) {}