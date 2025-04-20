package org.uniquindio.proyectofinalavanzada.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("comentarios")
public class Comentario {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("usuario_id")
    @NotNull
    private ObjectId usuarioId;

    @Field("reporte_id")
    @NotNull
    private ObjectId reporteId;

    @Field("contenido")
    @NotBlank
    @Size(min = 5, max = 300)
    private String contenido;

    @Field("fecha")
    @NotNull
    private LocalDateTime fecha;
}
