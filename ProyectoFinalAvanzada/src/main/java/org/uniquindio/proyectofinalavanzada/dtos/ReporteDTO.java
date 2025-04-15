package org.uniquindio.proyectofinalavanzada.dtos;

import jakarta.validation.constraints.*;
import org.uniquindio.proyectofinalavanzada.domain.Ubicacion;

import java.util.List;

public record ReporteDTO(
        @NotBlank String titulo,
        @NotBlank String categoria,
        @NotBlank String descripcion,
        @NotNull Ubicacion ubicacion,
        List<String> imagenes
) {}