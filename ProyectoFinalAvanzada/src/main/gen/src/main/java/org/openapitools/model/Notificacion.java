package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Notificacion
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-06T23:20:32.835196900-05:00[America/Bogota]", comments = "Generator version: 7.7.0")
public class Notificacion {

  private String usuarioId;

  private String reporteId;

  private String mensaje;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime fecha;

  private URI enlaceReporte;

  public Notificacion() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Notificacion(String usuarioId, String reporteId, String mensaje, OffsetDateTime fecha) {
    this.usuarioId = usuarioId;
    this.reporteId = reporteId;
    this.mensaje = mensaje;
    this.fecha = fecha;
  }

  public Notificacion usuarioId(String usuarioId) {
    this.usuarioId = usuarioId;
    return this;
  }

  /**
   * Get usuarioId
   * @return usuarioId
   */
  @NotNull 
  @Schema(name = "usuarioId", example = "abc123", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("usuarioId")
  public String getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(String usuarioId) {
    this.usuarioId = usuarioId;
  }

  public Notificacion reporteId(String reporteId) {
    this.reporteId = reporteId;
    return this;
  }

  /**
   * Get reporteId
   * @return reporteId
   */
  @NotNull 
  @Schema(name = "reporteId", example = "reporte567", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("reporteId")
  public String getReporteId() {
    return reporteId;
  }

  public void setReporteId(String reporteId) {
    this.reporteId = reporteId;
  }

  public Notificacion mensaje(String mensaje) {
    this.mensaje = mensaje;
    return this;
  }

  /**
   * Get mensaje
   * @return mensaje
   */
  @NotNull 
  @Schema(name = "mensaje", example = "Nuevo reporte de accidente cercano a tu ubicación.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("mensaje")
  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public Notificacion fecha(OffsetDateTime fecha) {
    this.fecha = fecha;
    return this;
  }

  /**
   * Get fecha
   * @return fecha
   */
  @NotNull @Valid 
  @Schema(name = "fecha", example = "2024-03-10T14:30Z", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("fecha")
  public OffsetDateTime getFecha() {
    return fecha;
  }

  public void setFecha(OffsetDateTime fecha) {
    this.fecha = fecha;
  }

  public Notificacion enlaceReporte(URI enlaceReporte) {
    this.enlaceReporte = enlaceReporte;
    return this;
  }

  /**
   * Get enlaceReporte
   * @return enlaceReporte
   */
  @Valid 
  @Schema(name = "enlaceReporte", example = "https://miapp.com/reportes/reporte567", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("enlaceReporte")
  public URI getEnlaceReporte() {
    return enlaceReporte;
  }

  public void setEnlaceReporte(URI enlaceReporte) {
    this.enlaceReporte = enlaceReporte;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Notificacion notificacion = (Notificacion) o;
    return Objects.equals(this.usuarioId, notificacion.usuarioId) &&
        Objects.equals(this.reporteId, notificacion.reporteId) &&
        Objects.equals(this.mensaje, notificacion.mensaje) &&
        Objects.equals(this.fecha, notificacion.fecha) &&
        Objects.equals(this.enlaceReporte, notificacion.enlaceReporte);
  }

  @Override
  public int hashCode() {
    return Objects.hash(usuarioId, reporteId, mensaje, fecha, enlaceReporte);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Notificacion {\n");
    sb.append("    usuarioId: ").append(toIndentedString(usuarioId)).append("\n");
    sb.append("    reporteId: ").append(toIndentedString(reporteId)).append("\n");
    sb.append("    mensaje: ").append(toIndentedString(mensaje)).append("\n");
    sb.append("    fecha: ").append(toIndentedString(fecha)).append("\n");
    sb.append("    enlaceReporte: ").append(toIndentedString(enlaceReporte)).append("\n");
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

