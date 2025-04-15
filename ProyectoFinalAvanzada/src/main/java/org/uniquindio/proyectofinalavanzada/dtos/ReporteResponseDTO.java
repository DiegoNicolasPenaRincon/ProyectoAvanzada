package org.uniquindio.proyectofinalavanzada.dtos;

import org.uniquindio.proyectofinalavanzada.domain.Ubicacion;

import java.util.List;

public record ReporteResponseDTO(
        String id,
        String titulo,
        String categoria,
        String descripcion,
        Ubicacion ubicacion,
        List<String> imagenes,
        String estado
) {}