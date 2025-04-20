package org.uniquindio.proyectofinalavanzada.mappers;

import org.bson.types.ObjectId;
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

    @Mapping(target = "usuarioId", expression = "java(toString(comentario.getUsuarioId()))")
    @Mapping(target = "reporteId", expression = "java(toString(comentario.getReporteId()))")
    @Mapping(target ="contenido", expression = "java(toString(comentario.getContenido()))")
    Comentario toComentario(ComentarioDTO dto);

    @Mapping(target = "id", expression = "java(generateId())")
    @Mapping(target = "fecha", expression = "java(getCurrentDateTime())")
    @Mapping(target = "usuarioId", expression = "java(toObjectId(dto.usuarioId()))")
    @Mapping(target = "contenido", expression = "java(toObjectId(dto.contenido()))")
    ComentarioResponseDTO toComentarioResponseDTO(Comentario comentario);

    default String generateId() {
        return UUID.randomUUID().toString();
    }

    default LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    default ObjectId toObjectId(String id) {
        return id == null ? null : new ObjectId(id);
    }

    default String toString(ObjectId objectId) {
        return objectId == null ? null : objectId.toHexString();
    }


}