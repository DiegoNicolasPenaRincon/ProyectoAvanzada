package org.uniquindio.proyectofinalavanzada.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.uniquindio.proyectofinalavanzada.domain.Reporte;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteDTO;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteResponseDTO;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReporteMapper {

    // Convertir de DTO a entidad
    @Mapping(target = "id", expression = "java(generateId())")
    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "estado", expression = "java(org.uniquindio.proyectofinalavanzada.domain.ReporteEstado.PENDIENTE)")
    @Mapping(target = "nivelImportancia", constant = "0")
    @Mapping(target = "titulo", source = "dto.titulo")
    @Mapping(target = "categorias", source = "dto.categorias")
    @Mapping(target = "descripcion", source = "dto.descripcion")
    @Mapping(target = "ubicacion", source = "dto.ubicacion")
    @Mapping(target = "imagenes", source = "dto.imagenes")
    @Mapping(target = "usuarioId", expression = "java(new org.bson.types.ObjectId(dto.usuarioId()))")
    Reporte toReporte(ReporteDTO dto);

    // Convertir de entidad a ResponseDTO
    @Mapping(target = "id", source = "reporte.id")
    @Mapping(target = "titulo", source = "reporte.titulo")
    @Mapping(target = "categoria", expression = "java(getFirstCategoriaNombre(reporte))")
    @Mapping(target = "descripcion", source = "reporte.descripcion")
    @Mapping(target = "ubicacion", source = "reporte.ubicacion")
    @Mapping(target = "imagenes", source = "reporte.imagenes")
    @Mapping(target = "estado", expression = "java(reporte.getEstado().name())")
    ReporteResponseDTO toReporteResponseDTO(Reporte reporte);

    // Métodos auxiliares

    default String generateId() {
        return UUID.randomUUID().toString();
    }

    default String getFirstCategoriaNombre(Reporte reporte) {
        if (reporte.getCategorias() != null && !reporte.getCategorias().isEmpty()) {
            return reporte.getCategorias().get(0).getNombre();
        }
        return null;
    }
}
