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

    @Mapping(target = "titulo", expression = "java(reporte.getTitulo())")
    @Mapping(target = "categorias", expression = "java(reporte.getCategorias())")
    @Mapping(target = "descripcion", expression = "java(reporte.getDescripcion())")
    @Mapping(target = "ubicacion", expression = "java(reporte.getUbicacion())")
    @Mapping(target = "imagenes", expression = "java(reporte.getImagenes())")
    @Mapping(target = "usuarioId", expression = "java(reporte.getUsuarioId())")
    Reporte toReporte(ReporteDTO dto);

    @Mapping(target = "id", expression = "java(generateId())")
    @Mapping(target = "estado", constant = "PENDIENTE")
    ReporteResponseDTO toReporteResponseDTO(Reporte reporte);

    default String generateId() {
        return UUID.randomUUID().toString();
    }
}