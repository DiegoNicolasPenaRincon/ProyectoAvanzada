package org.uniquindio.proyectofinalavanzada.dtos;

import jakarta.validation.constraints.*;

public record VerificarCuentaDTO(
        @NotBlank(message = "El correo es requerido")
        @Email(message = "Debe ser un email válido")
        String correo,

        @NotBlank(message = "El código es requerido")
        String codigo
) {}