package org.uniquindio.proyectofinalavanzada.dtos;

import java.time.Instant;
import java.util.Collection;

public record TokenResponse(String token, String tipo, Instant expireAt,
                            Collection<String> roles) {
}
