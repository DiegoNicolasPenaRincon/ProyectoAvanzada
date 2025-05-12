package org.uniquindio.proyectofinalavanzada.controllers.integration;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.web.servlet.MockMvc;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.domain.Reporte;
import org.uniquindio.proyectofinalavanzada.domain.ReporteEstado;
import org.uniquindio.proyectofinalavanzada.repositories.ReporteRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReporteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReporteRepository reporteRepository;

    @BeforeEach
    void setUp() {
        // Limpiar la base de datos antes de cada prueba
        reporteRepository.deleteAll();
        ArrayList<Categoria> categorias=new ArrayList<>();
        // Crear algunos reportes de ejemplo para las pruebas
        Reporte reporte1 = new Reporte(
                "1", // id
                "Caida de arbol", // titulo
                categorias,
                "Un arbol bloquea la via", // descripcion
                LocalDateTime.now(), // fecha
                new GeoJsonPoint(-75.6900, 4.5400), // ubicacion
                List.of("image1.jpg", "image2.jpg"), // imagenes
                ReporteEstado.PENDIENTE, // estado
                new ObjectId(), // usuarioId
                0 // nivelImportancia
        );
        Reporte reporte2 = new Reporte(
                "2", // id
                "Fuga de agua", // titulo
                categorias,
                "Fuga en la calle principal", // descripcion
                LocalDateTime.now(), // fecha
                new GeoJsonPoint(-75.6900, 4.5400), // ubicacion
                List.of("image3.jpg"), // imagenes
                ReporteEstado.PENDIENTE, // estado
                new ObjectId(), // usuarioId
                0 // nivelImportancia
        );

        reporteRepository.save(reporte1);
        reporteRepository.save(reporte2);
    }
/*
    @Test
    void testCrearReporteSuccess() throws Exception {
        // Crear un reporte de prueba
        mockMvc.perform(post("/api/reportes")
                        .contentType("application/json")
                        .content("{\"titulo\":\"Fuga de gas\",\"categoria\":[\"Categoria3\"],\"descripcion\":\"Fuga cerca del parque central\",\"ubicacion\":{\"type\":\"Point\",\"coordinates\":[-75.6900, 4.5400]},\"imagenes\":[\"image4.jpg\"],\"estado\":\"PENDIENTE\",\"usuarioId\":\"userId1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Fuga de gas"))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    void testListarReportesSuccess() throws Exception {
        // Realizar la solicitud para listar los reportes
        mockMvc.perform(get("/api/reportes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)); // Verifica que haya 2 reportes en la base de datos
    }

    @Test
    void testEditarReporteSuccess() throws Exception {
        // Realizar la solicitud para editar un reporte existente
        mockMvc.perform(put("/api/reportes/1")
                        .contentType("application/json")
                        .content("{\"titulo\":\"Caida de arbol Modificado\",\"categoria\":[\"Categoria1\"],\"descripcion\":\"Arbol caído en la vía principal\",\"ubicacion\":{\"type\":\"Point\",\"coordinates\":[-75.6900, 4.5400]},\"imagenes\":[\"image1.jpg\"],\"estado\":\"RESUELTO\",\"usuarioId\":\"userId1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Reporte actualizado correctamente."));
    }

    @Test
    void testEliminarReporteSuccess() throws Exception {
        // Eliminar un reporte existente
        mockMvc.perform(delete("/api/reportes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Reporte eliminado correctamente."));
    }

 */
}
