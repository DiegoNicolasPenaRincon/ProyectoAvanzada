package org.uniquindio.proyectofinalavanzada.dtos;

import jakarta.validation.constraints.*;

public record CategoriaDTO(
        @NotBlank String nombre,
        @NotBlank String descripcion
) {}