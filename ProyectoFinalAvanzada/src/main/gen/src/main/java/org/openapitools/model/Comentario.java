package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Comentario
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-06T23:20:32.835196900-05:00[America/Bogota]", comments = "Generator version: 7.7.0")
public class Comentario {

  private String usuarioId;

  private String contenido;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime fecha;

  public Comentario() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Comentario(String usuarioId, String contenido) {
    this.usuarioId = usuarioId;
    this.contenido = contenido;
  }

  public Comentario usuarioId(String usuarioId) {
    this.usuarioId = usuarioId;
    return this;
  }

  /**
   * Get usuarioId
   * @return usuarioId
   */
  @NotNull 
  @Schema(name = "usuarioId", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("usuarioId")
  public String getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(String usuarioId) {
    this.usuarioId = usuarioId;
  }

  public Comentario contenido(String contenido) {
    this.contenido = contenido;
    return this;
  }

  /**
   * Get contenido
   * @return contenido
   */
  @NotNull 
  @Schema(name = "contenido", example = "Este incidente también afectó mi zona.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("contenido")
  public String getContenido() {
    return contenido;
  }

  public void setContenido(String contenido) {
    this.contenido = contenido;
  }

  public Comentario fecha(OffsetDateTime fecha) {
    this.fecha = fecha;
    return this;
  }

  /**
   * Get fecha
   * @return fecha
   */
  @Valid 
  @Schema(name = "fecha", example = "2024-03-10T14:30Z", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fecha")
  public OffsetDateTime getFecha() {
    return fecha;
  }

  public void setFecha(OffsetDateTime fecha) {
    this.fecha = fecha;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comentario comentario = (Comentario) o;
    return Objects.equals(this.usuarioId, comentario.usuarioId) &&
        Objects.equals(this.contenido, comentario.contenido) &&
        Objects.equals(this.fecha, comentario.fecha);
  }

  @Override
  public int hashCode() {
    return Objects.hash(usuarioId, contenido, fecha);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comentario {\n");
    sb.append("    usuarioId: ").append(toIndentedString(usuarioId)).append("\n");
    sb.append("    contenido: ").append(toIndentedString(contenido)).append("\n");
    sb.append("    fecha: ").append(toIndentedString(fecha)).append("\n");
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

