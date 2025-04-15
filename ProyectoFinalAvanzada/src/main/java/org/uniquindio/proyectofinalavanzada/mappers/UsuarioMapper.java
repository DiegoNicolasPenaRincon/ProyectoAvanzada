package org.uniquindio.proyectofinalavanzada.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.dtos.UsuarioRegistroDTO;
import org.uniquindio.proyectofinalavanzada.dtos.UsuarioResponseDTO;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    @Mapping(target = "id", expression = "java(generateId())")
    @Mapping(target = "rol", constant = "USER")
    @Mapping(target = "verificado", constant = "false")
    Usuario toUsuario(UsuarioRegistroDTO dto);

    UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario);

    default String generateId() {
        return UUID.randomUUID().toString();
    }
}