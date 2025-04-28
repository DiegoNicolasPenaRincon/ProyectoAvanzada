package org.uniquindio.proyectofinalavanzada.services.unit;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.domain.Reporte;
import org.uniquindio.proyectofinalavanzada.domain.ReporteEstado;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteDTO;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteResponseDTO;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.mappers.ReporteMapper;
import org.uniquindio.proyectofinalavanzada.repositories.ReporteRepository;
import org.uniquindio.proyectofinalavanzada.services.ReporteServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceUnitTest {

    @Mock
    private ReporteRepository reporteRepository;

    @Mock
    private ReporteMapper reporteMapper;

    @InjectMocks
    private ReporteServiceImpl reporteService;

    private ReporteDTO reporteDTO;
    private Reporte reporte;
    private ReporteResponseDTO reporteResponseDTO;

    @BeforeEach
    void setUp() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        ArrayList<Categoria> categorias=new ArrayList<>();
        ArrayList<String> imagenes=new ArrayList<>();
        reporteDTO = new ReporteDTO(
                "Caida de arbol",
                categorias,
                "Un arbol bloquea la via",
                new GeoJsonPoint(-75.6900, 4.5400),
                imagenes,
                usuario.getId());

        reporte = new Reporte(
                "1", // id
                "Caida de arbol", // titulo
                categorias,
                "Un arbol bloquea la via", // descripcion
                LocalDateTime.now(), // fecha
                new GeoJsonPoint(-75.6900, 4.5400), // ubicacion (GeoJsonPoint con latitud y longitud)
                imagenes,
                ReporteEstado.PENDIENTE,
                new ObjectId(), // usuarioId (ObjectId debe usarse con un id de usuario)
                0 // nivelImportancia (valor predeterminado, se puede modificar)
        );

        reporteResponseDTO = new ReporteResponseDTO("1",
                "Caida de arbol",
                "Categoria",
                "Un arbol bloque la via",
                new GeoJsonPoint(-75.6900, 4.5400),
                imagenes,
                ReporteEstado.PENDIENTE.toString());
    }

    @Test
    void testCrearReporteSuccess() throws Exception {
        when(reporteMapper.toReporte(any(ReporteDTO.class))).thenReturn(reporte);
        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporte);
        when(reporteMapper.toReporteResponseDTO(any(Reporte.class))).thenReturn(reporteResponseDTO);

        ReporteResponseDTO result = reporteService.crearReporte(reporteDTO);

        assertNotNull(result);
        assertEquals("Caida de arbol", result.titulo());
        verify(reporteRepository).save(any(Reporte.class));
    }

    @Test
    void testEditarReporteSuccess() throws Exception {
        when(reporteRepository.findById(anyString())).thenReturn(Optional.of(reporte));
        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporte);
        when(reporteMapper.toReporteResponseDTO(any(Reporte.class))).thenReturn(reporteResponseDTO);

        ReporteResponseDTO result = reporteService.editarReporte("1", reporteDTO);

        assertNotNull(result);
        assertEquals("Caida de arbol", result.titulo());
    }

    @Test
    void testEditarReporteThrowsExceptionWhenNotFound() {
        when(reporteRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            reporteService.editarReporte("1", reporteDTO);
        });
    }

    /*
    @Test
    void testEliminarReporteSuccess() throws Exception {
        when(reporteRepository.findById(anyString())).thenAnswer(invocation -> {
            String id = invocation.getArgument(0);
            System.out.println("Mock: buscando id = " + id);
            return Optional.of(reporte);
        });

        reporteService.eliminarReporte("1");

        verify(reporteRepository).delete(any(Reporte.class));
    }

    @Test
    void testEliminarReporteThrowsExceptionWhenNotFound() {
        when(reporteRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            reporteService.eliminarReporte("1");
        });
    }

     */
}
