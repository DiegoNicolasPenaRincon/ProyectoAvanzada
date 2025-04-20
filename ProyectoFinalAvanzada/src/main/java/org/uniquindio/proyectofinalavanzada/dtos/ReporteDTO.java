package org.uniquindio.proyectofinalavanzada.dtos;

import jakarta.validation.constraints.*;
import org.uniquindio.proyectofinalavanzada.domain.GeoPoint;

import java.util.List;

public record ReporteDTO(
        @NotBlank String titulo,
        @NotNull List<String> categorias,
        @NotBlank String descripcion,
        @NotNull Double latitud,
        @NotNull Double longitud,
        List<String> imagenes,
        @NotBlank String usuarioId
) {}
