package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.model.UsuarioRegistroUbicacion;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UsuarioEditar
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-06T23:20:32.835196900-05:00[America/Bogota]", comments = "Generator version: 7.7.0")
public class UsuarioEditar {

  private String nombre;

  private String ciudadResidencia;

  private String telefono;

  private String direccion;

  private String correo;

  private String contraseña;

  private UsuarioRegistroUbicacion ubicacion;

  public UsuarioEditar() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UsuarioEditar(String correo) {
    this.correo = correo;
  }

  public UsuarioEditar nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Get nombre
   * @return nombre
   */
  
  @Schema(name = "nombre", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nombre")
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public UsuarioEditar ciudadResidencia(String ciudadResidencia) {
    this.ciudadResidencia = ciudadResidencia;
    return this;
  }

  /**
   * Get ciudadResidencia
   * @return ciudadResidencia
   */
  
  @Schema(name = "ciudadResidencia", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ciudadResidencia")
  public String getCiudadResidencia() {
    return ciudadResidencia;
  }

  public void setCiudadResidencia(String ciudadResidencia) {
    this.ciudadResidencia = ciudadResidencia;
  }

  public UsuarioEditar telefono(String telefono) {
    this.telefono = telefono;
    return this;
  }

  /**
   * Get telefono
   * @return telefono
   */
  
  @Schema(name = "telefono", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("telefono")
  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public UsuarioEditar direccion(String direccion) {
    this.direccion = direccion;
    return this;
  }

  /**
   * Get direccion
   * @return direccion
   */
  
  @Schema(name = "direccion", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("direccion")
  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public UsuarioEditar correo(String correo) {
    this.correo = correo;
    return this;
  }

  /**
   * Get correo
   * @return correo
   */
  @NotNull @jakarta.validation.constraints.Email 
  @Schema(name = "correo", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("correo")
  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public UsuarioEditar contraseña(String contraseña) {
    this.contraseña = contraseña;
    return this;
  }

  /**
   * Get contraseña
   * @return contraseña
   */
  
  @Schema(name = "contraseña", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contraseña")
  public String getContraseña() {
    return contraseña;
  }

  public void setContraseña(String contraseña) {
    this.contraseña = contraseña;
  }

  public UsuarioEditar ubicacion(UsuarioRegistroUbicacion ubicacion) {
    this.ubicacion = ubicacion;
    return this;
  }

  /**
   * Get ubicacion
   * @return ubicacion
   */
  @Valid 
  @Schema(name = "ubicacion", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ubicacion")
  public UsuarioRegistroUbicacion getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(UsuarioRegistroUbicacion ubicacion) {
    this.ubicacion = ubicacion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsuarioEditar usuarioEditar = (UsuarioEditar) o;
    return Objects.equals(this.nombre, usuarioEditar.nombre) &&
        Objects.equals(this.ciudadResidencia, usuarioEditar.ciudadResidencia) &&
        Objects.equals(this.telefono, usuarioEditar.telefono) &&
        Objects.equals(this.direccion, usuarioEditar.direccion) &&
        Objects.equals(this.correo, usuarioEditar.correo) &&
        Objects.equals(this.contraseña, usuarioEditar.contraseña) &&
        Objects.equals(this.ubicacion, usuarioEditar.ubicacion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre, ciudadResidencia, telefono, direccion, correo, contraseña, ubicacion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsuarioEditar {\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    ciudadResidencia: ").append(toIndentedString(ciudadResidencia)).append("\n");
    sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
    sb.append("    direccion: ").append(toIndentedString(direccion)).append("\n");
    sb.append("    correo: ").append(toIndentedString(correo)).append("\n");
    sb.append("    contraseña: ").append("*").append("\n");
    sb.append("    ubicacion: ").append(toIndentedString(ubicacion)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

