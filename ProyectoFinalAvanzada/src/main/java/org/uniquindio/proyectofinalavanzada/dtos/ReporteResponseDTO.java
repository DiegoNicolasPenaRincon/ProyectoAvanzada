package org.uniquindio.proyectofinalavanzada.dtos;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.uniquindio.proyectofinalavanzada.domain.GeoPoint;
import org.uniquindio.proyectofinalavanzada.domain.GeoPoint;

import java.util.List;

public record ReporteResponseDTO(
        String id,
        String titulo,
        String categoria,
        String descripcion,
        GeoJsonPoint ubicacion,
        List<String> imagenes,
        String estado
) {}