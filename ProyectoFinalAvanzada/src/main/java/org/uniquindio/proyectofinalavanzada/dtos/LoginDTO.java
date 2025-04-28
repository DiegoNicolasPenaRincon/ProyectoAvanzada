package org.uniquindio.proyectofinalavanzada.dtos;

import jakarta.validation.constraints.*;

public record LoginDTO(
        @NotBlank @Email String correo,
        @NotBlank String contraseña,
        @NotBlank String nombreUsuario
) {}