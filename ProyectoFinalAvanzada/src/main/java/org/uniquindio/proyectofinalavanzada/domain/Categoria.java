package org.uniquindio.proyectofinalavanzada.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("categorias")
public class Categoria {

    @Indexed(unique = true)
    private String id;

    @Field("nombre")
    @NotBlank
    @Size(min = 2, max = 50)
    @Indexed
    @Id
    @EqualsAndHashCode.Include
    private String nombre;

    @Field("descripcion")
    @Size(max = 200)
    private String descripcion;
}