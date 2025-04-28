package org.uniquindio.proyectofinalavanzada.controllers.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.uniquindio.proyectofinalavanzada.controllers.CategoriaController;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaDTO;
import org.uniquindio.proyectofinalavanzada.dtos.CategoriaResponseDTO;
import org.uniquindio.proyectofinalavanzada.dtos.MensajeDTO;
import org.uniquindio.proyectofinalavanzada.services.CategoriaService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController).build();
    }

    @Test
    void testCrearCategoriaSuccess() throws Exception {
        CategoriaResponseDTO responseDTO = new CategoriaResponseDTO("1", "Electrónica", "Productos electrónicos");

        when(categoriaService.crearCategoria(any(CategoriaDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/admin/categorias")
                        .contentType("application/json")
                        .content("{\"nombre\":\"Electrónica\",\"descripcion\":\"Productos electrónicos\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nombre").value("Electrónica"));
    }

    @Test
    void testEditarCategoriaSuccess() throws Exception {
        mockMvc.perform(put("/api/admin/categorias/1")
                        .contentType("application/json")
                        .content("{\"nombre\":\"Electrónica Mod\",\"descripcion\":\"Descripción mod\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Categoría editada correctamente."));
    }

    @Test
    void testEliminarCategoriaSuccess() throws Exception {
        mockMvc.perform(delete("/api/admin/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Categoría eliminada correctamente."));
    }

    @Test
    void testListarCategoriasSuccess() throws Exception {
        List<CategoriaResponseDTO> categorias = List.of(
                new CategoriaResponseDTO("1", "Electrónica", "Productos electrónicos"),
                new CategoriaResponseDTO("2", "Ropa", "Prendas de vestir")
        );

        when(categoriaService.listarCategorias()).thenReturn(categorias);

        mockMvc.perform(get("/api/admin/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Electrónica"));
    }
}