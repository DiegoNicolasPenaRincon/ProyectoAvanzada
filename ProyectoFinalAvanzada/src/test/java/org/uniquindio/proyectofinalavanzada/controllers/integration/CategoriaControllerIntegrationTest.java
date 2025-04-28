package org.uniquindio.proyectofinalavanzada.controllers.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.uniquindio.proyectofinalavanzada.data.TestDataLoaderCategoria;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.repositories.CategoriaRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CategoriaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    void setUp() {
        categoriaRepository.deleteAll();

        categoriaRepository.save(new Categoria("1", "Electrónica", "Productos electrónicos"));
        categoriaRepository.save(new Categoria("2", "Ropa", "Prendas de vestir"));
    }

    @Test
    void testCrearCategoriaSuccess() throws Exception {
        mockMvc.perform(post("/api/admin/categorias")
                        .contentType("application/json")
                        .content("{\"nombre\":\"Hogar\",\"descripcion\":\"Productos para el hogar\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Hogar"));
    }

    @Test
    void testListarCategoriasSuccess() throws Exception {
        mockMvc.perform(get("/api/admin/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}