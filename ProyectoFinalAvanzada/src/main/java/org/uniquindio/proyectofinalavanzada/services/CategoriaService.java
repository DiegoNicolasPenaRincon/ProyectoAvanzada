package org.uniquindio.proyectofinalavanzada.services;

import org.uniquindio.proyectofinalavanzada.dtos.*;

import java.util.List;

public interface CategoriaService {
    CategoriaResponseDTO crearCategoria(CategoriaDTO categoriaDTO) throws Exception;
    CategoriaResponseDTO editarCategoria(String id, CategoriaDTO categoriaDTO) throws Exception;
    void eliminarCategoria(String id) throws Exception;
    List<CategoriaResponseDTO> listarCategorias();
}