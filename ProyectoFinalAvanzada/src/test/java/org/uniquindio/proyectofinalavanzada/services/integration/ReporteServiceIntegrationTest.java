package org.uniquindio.proyectofinalavanzada.services.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteDTO;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteResponseDTO;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.repositories.ReporteRepository;
import org.uniquindio.proyectofinalavanzada.services.ReporteService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.bson.types.ObjectId; // IMPORTANTE agregar esto

@SpringBootTest
public class ReporteServiceIntegrationTest {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private ReporteRepository reporteRepository;

    @BeforeEach
    void setUp() {
        reporteRepository.deleteAll();
    }

    private ReporteDTO crearReporteDTO(String titulo, String descripcion, String idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);

        ArrayList<Categoria> categorias = new ArrayList<>();
        ArrayList<String> imagenes = new ArrayList<>();

        return new ReporteDTO(
                titulo,
                categorias,
                descripcion,
                new GeoJsonPoint(-75.6900, 4.5400),
                imagenes,
                usuario.getId()
        );
    }

    private String generarIdValido() {
        return new ObjectId().toHexString(); // genera id válido de 24 caracteres
    }

    @Test
    void testCrearReporteSuccess() throws Exception {
        ReporteDTO dto = crearReporteDTO(
                "Caída de árbol",
                "Un árbol bloquea la vía principal",
                generarIdValido()  // usamos id válido
        );

        ReporteResponseDTO result = reporteService.crearReporte(dto);

        assertNotNull(result.id());
        assertEquals(dto.titulo(), result.titulo());
    }

    @Test
    void testCrearReporteThrowsValueConflictException() throws Exception {
        String idUsuario = generarIdValido();

        ReporteDTO dto = crearReporteDTO(
                "Caída de árbol",
                "Un árbol bloquea la vía principal",
                idUsuario
        );

        reporteService.crearReporte(dto);

        assertThrows(ValueConflictException.class, () -> {
            reporteService.crearReporte(dto);
        });
    }

    @Test
    void testListarReportesSuccess() throws Exception {
        ReporteDTO dto1 = crearReporteDTO(
                "Inundación en calle",
                "Hay una gran acumulación de agua en la calle 5",
                generarIdValido()
        );

        ReporteDTO dto2 = crearReporteDTO(
                "Hueco en la vía",
                "Un hueco enorme en la autopista",
                generarIdValido()
        );

        reporteService.crearReporte(dto1);
        reporteService.crearReporte(dto2);

        List<ReporteResponseDTO> result = reporteService.listarTodosReportes();

        assertEquals(2, result.size());
    }
}
