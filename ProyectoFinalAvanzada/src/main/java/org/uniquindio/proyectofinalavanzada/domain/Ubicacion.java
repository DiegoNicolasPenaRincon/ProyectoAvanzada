package org.uniquindio.proyectofinalavanzada.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Ubicacion {
    private double latitud;
    private double longitud;
}