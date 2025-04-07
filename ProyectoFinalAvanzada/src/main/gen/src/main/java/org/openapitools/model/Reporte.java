package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.UsuarioRegistroUbicacion;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Reporte
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-06T23:20:32.835196900-05:00[America/Bogota]", comments = "Generator version: 7.7.0")
public class Reporte {

  private String id;

  private String titulo;

  private String categoria;

  private String descripcion;

  private UsuarioRegistroUbicacion ubicacion;

  @Valid
  private List<URI> imagenes = new ArrayList<>();

  public Reporte() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Reporte(String titulo, String categoria, String descripcion, UsuarioRegistroUbicacion ubicacion) {
    this.titulo = titulo;
    this.categoria = categoria;
    this.descripcion = descripcion;
    this.ubicacion = ubicacion;
  }

  public Reporte id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Reporte titulo(String titulo) {
    this.titulo = titulo;
    return this;
  }

  /**
   * Get titulo
   * @return titulo
   */
  @NotNull 
  @Schema(name = "titulo", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("titulo")
  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Reporte categoria(String categoria) {
    this.categoria = categoria;
    return this;
  }

  /**
   * Get categoria
   * @return categoria
   */
  @NotNull 
  @Schema(name = "categoria", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("categoria")
  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public Reporte descripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * Get descripcion
   * @return descripcion
   */
  @NotNull 
  @Schema(name = "descripcion", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("descripcion")
  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Reporte ubicacion(UsuarioRegistroUbicacion ubicacion) {
    this.ubicacion = ubicacion;
    return this;
  }

  /**
   * Get ubicacion
   * @return ubicacion
   */
  @NotNull @Valid 
  @Schema(name = "ubicacion", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ubicacion")
  public UsuarioRegistroUbicacion getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(UsuarioRegistroUbicacion ubicacion) {
    this.ubicacion = ubicacion;
  }

  public Reporte imagenes(List<URI> imagenes) {
    this.imagenes = imagenes;
    return this;
  }

  public Reporte addImagenesItem(URI imagenesItem) {
    if (this.imagenes == null) {
      this.imagenes = new ArrayList<>();
    }
    this.imagenes.add(imagenesItem);
    return this;
  }

  /**
   * Get imagenes
   * @return imagenes
   */
  @Valid 
  @Schema(name = "imagenes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("imagenes")
  public List<URI> getImagenes() {
    return imagenes;
  }

  public void setImagenes(List<URI> imagenes) {
    this.imagenes = imagenes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reporte reporte = (Reporte) o;
    return Objects.equals(this.id, reporte.id) &&
        Objects.equals(this.titulo, reporte.titulo) &&
        Objects.equals(this.categoria, reporte.categoria) &&
        Objects.equals(this.descripcion, reporte.descripcion) &&
        Objects.equals(this.ubicacion, reporte.ubicacion) &&
        Objects.equals(this.imagenes, reporte.imagenes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, titulo, categoria, descripcion, ubicacion, imagenes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Reporte {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    titulo: ").append(toIndentedString(titulo)).append("\n");
    sb.append("    categoria: ").append(toIndentedString(categoria)).append("\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
    sb.append("    ubicacion: ").append(toIndentedString(ubicacion)).append("\n");
    sb.append("    imagenes: ").append(toIndentedString(imagenes)).append("\n");
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

