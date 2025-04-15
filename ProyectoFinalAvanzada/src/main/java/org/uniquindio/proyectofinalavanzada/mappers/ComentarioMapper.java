package org.uniquindio.proyectofinalavanzada.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.uniquindio.proyectofinalavanzada.domain.Comentario;
import org.uniquindio.proyectofinalavanzada.dtos.ComentarioDTO;
import org.uniquindio.proyectofinalavanzada.dtos.ComentarioResponseDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ComentarioMapper {
    @Mapping(target = "id", expression = "java(generateId())")
    @Mapping(target = "fecha", expression = "java(getCurrentDateTime())")
    Comentario toComentario(ComentarioDTO dto);

    ComentarioResponseDTO toComentarioResponseDTO(Comentario comentario);

    default String generateId() {
        return UUID.randomUUID().toString();
    }

    default LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}