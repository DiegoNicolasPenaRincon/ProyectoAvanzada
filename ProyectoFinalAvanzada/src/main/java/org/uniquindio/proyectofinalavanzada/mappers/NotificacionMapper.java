package org.uniquindio.proyectofinalavanzada.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.uniquindio.proyectofinalavanzada.domain.Notificacion;
import org.uniquindio.proyectofinalavanzada.dtos.NotificacionDTO;
import org.uniquindio.proyectofinalavanzada.dtos.NotificacionResponseDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificacionMapper {
    @Mapping(target = "id", expression = "java(generateId())")
    @Mapping(target = "fecha", expression = "java(getCurrentDateTime())")
    @Mapping(target = "leida", constant = "false")
    @Mapping(target = "enlaceReporte", expression = "java(\"https://api.reportes-ciudad.com/v1/reportes/\" + dto.reporteId())")
    Notificacion toNotificacion(NotificacionDTO dto);

    NotificacionResponseDTO toNotificacionResponseDTO(Notificacion notificacion);

    default String generateId() {
        return UUID.randomUUID().toString();
    }

    default LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}