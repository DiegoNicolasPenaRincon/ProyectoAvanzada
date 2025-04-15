package org.uniquindio.proyectofinalavanzada.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaDTO;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaResponseDTO;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.mappers.CategoriaMapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final Map<String, Categoria> categoriaStore = new ConcurrentHashMap<>();
    private final CategoriaMapper categoriaMapper;

    @Override
    public CategoriaResponseDTO crearCategoria(CategoriaDTO categoriaDTO) throws Exception {
        Categoria categoria = categoriaMapper.toCategoria(categoriaDTO);
        categoriaStore.put(categoria.getId(), categoria);
        return categoriaMapper.toCategoriaResponseDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO editarCategoria(String id, CategoriaDTO categoriaDTO) throws Exception {
        Categoria categoria = categoriaStore.get(id);
        if (categoria == null) {
            throw new ResourceNotFoundException("Categoría no encontrada");
        }

        if (categoriaDTO.nombre() != null) categoria.setNombre(categoriaDTO.nombre());
        if (categoriaDTO.descripcion() != null) categoria.setDescripcion(categoriaDTO.descripcion());

        return categoriaMapper.toCategoriaResponseDTO(categoria);
    }

    @Override
    public void eliminarCategoria(String id) throws Exception {
        if (!categoriaStore.containsKey(id)) {
            throw new ResourceNotFoundException("Categoría no encontrada");
        }
        categoriaStore.remove(id);
    }

    @Override
    public List<CategoriaResponseDTO> listarCategorias() {
        return categoriaStore.values().stream()
                .map(categoriaMapper::toCategoriaResponseDTO)
                .collect(Collectors.toList());
    }
}