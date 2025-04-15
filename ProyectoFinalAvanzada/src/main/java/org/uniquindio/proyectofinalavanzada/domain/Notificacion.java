package org.uniquindio.proyectofinalavanzada.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Notificacion {
    private String id;
    private String usuarioId;
    private String reporteId;
    private String mensaje;
    private LocalDateTime fecha;
    private String enlaceReporte;
    private boolean leida;
}