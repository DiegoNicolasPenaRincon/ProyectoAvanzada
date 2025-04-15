package org.uniquindio.proyectofinalavanzada.dtos;

public record LoginResponseDTO(
        String token,
        UsuarioResponseDTO usuario
) {}