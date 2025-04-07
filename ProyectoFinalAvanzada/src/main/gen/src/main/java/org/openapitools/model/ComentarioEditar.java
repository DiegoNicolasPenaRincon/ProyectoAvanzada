package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ComentarioEditar
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-06T23:20:32.835196900-05:00[America/Bogota]", comments = "Generator version: 7.7.0")
public class ComentarioEditar {

  private String contenido;

  public ComentarioEditar() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ComentarioEditar(String contenido) {
    this.contenido = contenido;
  }

  public ComentarioEditar contenido(String contenido) {
    this.contenido = contenido;
    return this;
  }

  /**
   * Get contenido
   * @return contenido
   */
  @NotNull 
  @Schema(name = "contenido", example = "He actualizado mi comentario sobre este incidente.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("contenido")
  public String getContenido() {
    return contenido;
  }

  public void setContenido(String contenido) {
    this.contenido = contenido;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComentarioEditar comentarioEditar = (ComentarioEditar) o;
    return Objects.equals(this.contenido, comentarioEditar.contenido);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contenido);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComentarioEditar {\n");
    sb.append("    contenido: ").append(toIndentedString(contenido)).append("\n");
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

