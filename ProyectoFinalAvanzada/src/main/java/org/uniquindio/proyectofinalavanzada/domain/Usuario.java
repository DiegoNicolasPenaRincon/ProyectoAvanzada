package org.uniquindio.proyectofinalavanzada.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Usuario {
    private String id;
    private String nombre;
    private String ciudadResidencia;
    private String telefono;
    private String direccion;
    private String correo;
    private String contraseña;
    private Ubicacion ubicacion;
    private Rol rol;
    private boolean verificado;
}