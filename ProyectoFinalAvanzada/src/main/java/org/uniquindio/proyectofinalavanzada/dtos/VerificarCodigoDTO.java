package org.uniquindio.proyectofinalavanzada.dtos;

import jakarta.validation.constraints.*;

public record VerificarCodigoDTO(
        @NotBlank @Email String correo,
        @NotBlank String codigo
) {}