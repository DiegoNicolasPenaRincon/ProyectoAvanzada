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

    @Mapping(target = "usuarioId", expression = "java(generateIdObject())")
    @Mapping(target = "reporteId", expression = "java(generateIdObject())")
    Comentario toComentario(ComentarioDTO dto);

    @Mapping(target = "id", expression = "java(toString(comentario.getUsuarioId()))")
    @Mapping(target = "fecha", expression = "java(getCurrentDateTimeString() )")
    @Mapping(target = "usuarioId", expression = "java(toString(comentario.getReporteId()))")
    ComentarioResponseDTO toComentarioResponseDTO(Comentario comentario);

    default String generateId() {
        return UUID.randomUUID().toString();
    }

    default LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    default String getCurrentDateTimeString() {
        return String.valueOf(LocalDateTime.now());
    }

    default ObjectId toObjectId(String id) {
        return id == null ? null : new ObjectId(id);
    }

    default String toString(ObjectId objectId) {
        return objectId == null ? null : objectId.toHexString();
    }


    default ObjectId generateIdObject() {
        return new ObjectId();
    }


}