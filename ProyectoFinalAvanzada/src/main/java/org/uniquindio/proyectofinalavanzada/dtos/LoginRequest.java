package org.uniquindio.proyectofinalavanzada.dtos;

import jakarta.validation.constraints.NotBlank;
public record LoginRequest(@NotBlank String username, @NotBlank String password) {
}