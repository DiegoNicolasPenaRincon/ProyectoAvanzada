package org.uniquindio.proyectofinalavanzada.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection= "usuarios")
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("nombre")
    @NotBlank
    @Size(min = 2, max = 50)
    private String nombre;

    @Field("ciudad_residencia")
    @NotBlank
    @Size(max = 50)
    private String ciudadResidencia;

    @Field("telefono")
    @NotBlank
    @Size(min = 7, max = 20)
    private String telefono;

    @Field("direccion")
    @Size(max = 100)
    private String direccion;

    @Field("correo")
    @Email
    @NotBlank
    private String correo;

    @Field("contrasena")
    @NotBlank
    @Size(min = 6)
    private String contraseña;

    @Field("ubicacion")
    private GeoJsonPoint ubicacion;

    @Field("rol")
    @NotNull
    private Rol rol;

    @Field("verificado")
    private boolean verificado;

    @Field("estado")
    @NotNull
    private UsuarioEstado estado;



    public Usuario(String id, String correo, String contraseña, String nombre, Rol rol, UsuarioEstado usuarioEstado) {
        this.id=id;
        this.correo=correo;
        this.contraseña=contraseña;
        this.nombre=nombre;
        this.rol=rol;
        this.estado=usuarioEstado;

    }

    public Usuario(String string, String mail, String s, Object o, String carlosPérez, LocalDate of, Rol rol, UsuarioEstado usuarioEstado) {
    }
}