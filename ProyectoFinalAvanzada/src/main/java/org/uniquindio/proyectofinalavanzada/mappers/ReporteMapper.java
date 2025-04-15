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
    @Mapping(target = "id", expression = "java(generateId())")
    @Mapping(target = "estado", constant = "PENDIENTE")
    Reporte toReporte(ReporteDTO dto);

    ReporteResponseDTO toReporteResponseDTO(Reporte reporte);

    default String generateId() {
        return UUID.randomUUID().toString();
    }
}