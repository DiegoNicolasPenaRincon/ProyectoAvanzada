package org.uniquindio.proyectofinalavanzada.services.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaDTO;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaResponseDTO;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.mappers.CategoriaMapper;
import org.uniquindio.proyectofinalavanzada.repositories.CategoriaRepository;
import org.uniquindio.proyectofinalavanzada.services.CategoriaServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceUnitTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    private CategoriaDTO categoriaDTO;
    private Categoria categoria;
    private CategoriaResponseDTO categoriaResponseDTO;

    @BeforeEach
    void setUp() {
        categoriaDTO = new CategoriaDTO("Electrónica", "Productos electrónicos");
        categoria = new Categoria("1", "Electrónica", "Productos electrónicos");
        categoriaResponseDTO = new CategoriaResponseDTO("1", "Electrónica", "Productos electrónicos");
    }

    @Test
    void testCrearCategoriaSuccess() throws Exception {
        when(categoriaRepository.findByNombre(anyString())).thenReturn(Optional.empty());
        when(categoriaMapper.toCategoria(any(CategoriaDTO.class))).thenReturn(categoria);
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);
        when(categoriaMapper.toCategoriaResponseDTO(any(Categoria.class))).thenReturn(categoriaResponseDTO);

        CategoriaResponseDTO result = categoriaService.crearCategoria(categoriaDTO);

        assertNotNull(result);
        assertEquals("Electrónica", result.nombre());
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void testCrearCategoriaThrowsValueConflictException() {
        when(categoriaRepository.findByNombre(anyString())).thenReturn(Optional.of(categoria));

        assertThrows(ValueConflictException.class, () -> {
            categoriaService.crearCategoria(categoriaDTO);
        });
    }
/*
    @Test
    void testEditarCategoriaSuccess() throws Exception {
        when(categoriaRepository.findByNombre(anyString())).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);
        when(categoriaMapper.toCategoriaResponseDTO(any(Categoria.class))).thenReturn(categoriaResponseDTO);

        CategoriaResponseDTO result = categoriaService.editarCategoria("1", categoriaDTO);

        assertNotNull(result);
        assertEquals("Electrónica", result.nombre());
    }
    
 */

    @Test
    void testEliminarCategoriaSuccess() throws Exception {
        when(categoriaRepository.findByNombre(anyString())).thenReturn(Optional.of(categoria));

        categoriaService.eliminarCategoria("1");

        verify(categoriaRepository).delete(any(Categoria.class));
    }
}