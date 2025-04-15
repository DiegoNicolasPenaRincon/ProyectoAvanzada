package org.uniquindio.proyectofinalavanzada.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaDTO;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaResponseDTO;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapper {
    @Mapping(target = "id", expression = "java(generateId())")
    Categoria toCategoria(CategoriaDTO dto);

    CategoriaResponseDTO toCategoriaResponseDTO(Categoria categoria);

    default String generateId() {
        return UUID.randomUUID().toString();
    }
}