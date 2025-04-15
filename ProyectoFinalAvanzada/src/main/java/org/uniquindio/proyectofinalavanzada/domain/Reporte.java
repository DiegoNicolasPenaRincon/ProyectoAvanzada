package org.uniquindio.proyectofinalavanzada.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Reporte {
    private String id;
    private String titulo;
    private String categoria;
    private String descripcion;
    private Ubicacion ubicacion;
    private List<String> imagenes;
    private String estado;
    private String usuarioId;
}