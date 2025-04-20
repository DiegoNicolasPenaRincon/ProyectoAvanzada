package org.uniquindio.proyectofinalavanzada.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaDTO;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaResponseDTO;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.mappers.CategoriaMapper;
import org.uniquindio.proyectofinalavanzada.repositories.CategoriaRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final Map<String, Categoria> categoriaStore = new ConcurrentHashMap<>();
    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepository repositorioCategoria;

    @Override
    public CategoriaResponseDTO crearCategoria(CategoriaDTO categoriaDTO) throws Exception {
        Optional<Categoria> categoriaBacana=repositorioCategoria.findByNombre(categoriaDTO.nombre());
        if(categoriaBacana.isPresent())
        {
            throw new ValueConflictException("Esa categoria ya se encuentra creada");
        }
        Categoria categoria = categoriaMapper.toCategoria(categoriaDTO);
        repositorioCategoria.save(categoria);
        return categoriaMapper.toCategoriaResponseDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO editarCategoria(String nombre, CategoriaDTO categoriaDTO) throws Exception {
        Optional<Categoria> categoriaOptional = repositorioCategoria.findByNombre(nombre);
        if (categoriaOptional.isEmpty())
        {
            throw new ResourceNotFoundException("Categoría no encontrada");
        }

        Categoria categoria=categoriaMapper.toCategoria(categoriaDTO);
        //if (categoriaDTO.nombre() != null) categoria.setNombre(categoriaDTO.nombre());
        if (categoriaDTO.descripcion() != null) categoria.setDescripcion(categoriaDTO.descripcion());
        return categoriaMapper.toCategoriaResponseDTO(categoria);
    }

    @Override
    public void eliminarCategoria(String nombre) throws Exception {
        Optional<Categoria> categoriaOptional = repositorioCategoria.findByNombre(nombre);
        if (categoriaOptional.isEmpty())
        {
            throw new ResourceNotFoundException("Categoría no encontrada");
        }
        Categoria categoria=categoriaOptional.get();
        repositorioCategoria.delete(categoria);
    }

    @Override
    public List<CategoriaResponseDTO> listarCategorias() {
        return repositorioCategoria.findAll().stream()
                .map(categoriaMapper::toCategoriaResponseDTO)
                .collect(Collectors.toList());
    }



    /*@Override
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

     */
}