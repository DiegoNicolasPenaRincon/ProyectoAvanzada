package org.uniquindio.proyectofinalavanzada.dtos;

import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.domain.GeoPoint;

import java.util.List;

public record ReporteDTO(
        @NotBlank String titulo,
        @NotNull List<Categoria> categorias,
        @NotBlank String descripcion,
        //@NotNull Double latitud,
       // @NotNull Double longitud,
        @NotNull GeoJsonPoint ubicacion,
        List<String> imagenes,
        @NotBlank String usuarioId
) {}
