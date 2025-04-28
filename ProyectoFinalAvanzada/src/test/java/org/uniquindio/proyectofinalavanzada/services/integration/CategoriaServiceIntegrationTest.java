package org.uniquindio.proyectofinalavanzada.services.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaDTO;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaResponseDTO;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.repositories.CategoriaRepository;
import org.uniquindio.proyectofinalavanzada.services.CategoriaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoriaServiceIntegrationTest {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    void setUp() {
        categoriaRepository.deleteAll();
    }

    @Test
    void testCrearCategoriaSuccess() throws Exception {  // Aquí añadimos throws Exception
        CategoriaDTO dto = new CategoriaDTO("Electrónica", "Productos electrónicos");

        CategoriaResponseDTO result = categoriaService.crearCategoria(dto);

        assertNotNull(result.id());
        assertEquals(dto.nombre(), result.nombre());
    }

    @Test
    void testCrearCategoriaThrowsValueConflictException() throws Exception {  // Añadimos throws Exception
        CategoriaDTO dto = new CategoriaDTO("Electrónica", "Productos electrónicos");
        categoriaService.crearCategoria(dto);

        assertThrows(ValueConflictException.class, () -> {
            categoriaService.crearCategoria(dto);
        });
    }

    @Test
    void testListarCategoriasSuccess() throws Exception {  // Añadimos throws Exception
        categoriaService.crearCategoria(new CategoriaDTO("Cat1", "Desc1"));
        categoriaService.crearCategoria(new CategoriaDTO("Cat2", "Desc2"));

        List<CategoriaResponseDTO> result = categoriaService.listarCategorias();

        assertEquals(2, result.size());
    }
}