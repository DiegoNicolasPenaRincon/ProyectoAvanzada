package org.uniquindio.proyectofinalavanzada.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("reportes")
public class Reporte {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("titulo")
    @NotBlank
    @Size(min = 3, max = 100)
    private String titulo;

    @DBRef
    @Field("categorias")
    @NotNull
    private List<Categoria> categorias;

    @Field("descripcion")
    @Size(max = 500)
    private String descripcion;

    @Field("fecha")
    @NotNull
    private LocalDateTime fecha;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    @Field("ubicacion")
    @NotNull
    private GeoJsonPoint ubicacion;

    @Field("imagenes")
    private List<String> imagenes;

    @Field("estado")
    @NotNull
    private ReporteEstado estado;

    @Field("usuario_id")
    @NotNull
    private ObjectId usuarioId;

    @Field("importancia")
    @NotNull
    private int nivelImportancia=0;
}