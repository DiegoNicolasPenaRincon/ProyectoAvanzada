package org.uniquindio.proyectofinalavanzada.controllers.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.uniquindio.proyectofinalavanzada.controllers.ReporteController;
import org.uniquindio.proyectofinalavanzada.domain.ReporteEstado;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteDTO;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteResponseDTO;
import org.uniquindio.proyectofinalavanzada.services.ReporteService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ReporteControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private ReporteService reporteService;

    @InjectMocks
    private ReporteController reporteController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reporteController).build();
    }

    private ReporteResponseDTO crearReporteResponseDTO(String id, String titulo, String categoria, String descripcion, List<String> imagenes) {
        return new ReporteResponseDTO(
                id,
                titulo,
                categoria,
                descripcion,
                new GeoJsonPoint(-75.6900, 4.5400),
                imagenes,
                ReporteEstado.PENDIENTE.toString()
        );
    }
/*
    @Test
    void testCrearReporteSuccess() throws Exception {
        List<String> imagenes = new ArrayList<>();
        imagenes.add("imagen1.jpg");
        imagenes.add("imagen2.jpg");

        ReporteResponseDTO responseDTO = crearReporteResponseDTO("1", "Caída de árbol", "Naturaleza", "Un árbol bloquea la vía", imagenes);

        when(reporteService.crearReporte(any(ReporteDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/reportes")
                        .contentType("application/json")
                        .content("{\"titulo\":\"Caída de árbol\",\"descripcion\":\"Un árbol bloquea la vía\",\"usuarioId\":\"usuario123\",\"categorias\":[],\"imagenes\":[]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.titulo").value("Caída de árbol"))
                .andExpect(jsonPath("$.categoria").value("Naturaleza"))
                .andExpect(jsonPath("$.descripcion").value("Un árbol bloquea la vía"));
    }

    @Test
    void testEditarReporteSuccess() throws Exception {
        List<String> imagenes = new ArrayList<>();
        imagenes.add("imagenModificada.jpg");

        ReporteResponseDTO responseDTO = crearReporteResponseDTO("1", "Caída de árbol modificada", "Naturaleza", "Árbol retirado de la vía", imagenes);

        when(reporteService.editarReporte(anyString(), any(ReporteDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/reportes/1")
                        .contentType("application/json")
                        .content("{\"titulo\":\"Caída de árbol modificada\",\"descripcion\":\"Árbol retirado de la vía\",\"usuarioId\":\"usuario123\",\"categorias\":[],\"imagenes\":[\"imagenModificada.jpg\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Reporte editado correctamente."));
    }


 */
    @Test
    void testEliminarReporteSuccess() throws Exception {
        mockMvc.perform(delete("/api/reportes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Reporte eliminado correctamente."));
    }
/*
    @Test
    void testListarReportesSuccess() throws Exception {
        List<String> imagenes = new ArrayList<>();
        imagenes.add("imagen1.jpg");

        List<ReporteResponseDTO> reportes = List.of(
                crearReporteResponseDTO("1", "Inundación en calle", "Clima", "Hay una gran acumulación de agua en la calle 5", imagenes),
                crearReporteResponseDTO("2", "Hueco en la vía", "Infraestructura", "Un hueco enorme en la autopista", imagenes)
        );

        when(reporteService.listarTodosReportes()).thenReturn(reportes);

        mockMvc.perform(get("/api/reportes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titulo").value("Inundación en calle"));
    }

 */
}
