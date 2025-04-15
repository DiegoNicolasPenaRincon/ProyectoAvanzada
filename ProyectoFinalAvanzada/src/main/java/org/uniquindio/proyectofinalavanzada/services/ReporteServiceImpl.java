package org.uniquindio.proyectofinalavanzada.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.uniquindio.proyectofinalavanzada.domain.Reporte;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteDTO;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteResponseDTO;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.mappers.ReporteMapper;
import org.uniquindio.proyectofinalavanzada.services.ReporteService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final Map<String, Reporte> reporteStore = new ConcurrentHashMap<>();
    private final ReporteMapper reporteMapper;

    @Override
    public ReporteResponseDTO crearReporte(ReporteDTO reporteDTO) throws Exception {
        Reporte reporte = reporteMapper.toReporte(reporteDTO);
        reporteStore.put(reporte.getId(), reporte);

        return reporteMapper.toReporteResponseDTO(reporte);
    }

    @Override
    public List<ReporteResponseDTO> listarReportes(String categoria, String estado) {
        return reporteStore.values().stream()
                .filter(r -> categoria == null || r.getCategoria().equals(categoria))
                .filter(r -> estado == null || r.getEstado().equals(estado))
                .map(reporteMapper::toReporteResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReporteResponseDTO editarReporte(String id, ReporteDTO reporteDTO) throws Exception {
        Reporte reporte = reporteStore.get(id);
        if (reporte == null) {
            throw new ResourceNotFoundException("Reporte no encontrado");
        }

        if (reporteDTO.titulo() != null) reporte.setTitulo(reporteDTO.titulo());
        if (reporteDTO.categoria() != null) reporte.setCategoria(reporteDTO.categoria());
        if (reporteDTO.descripcion() != null) reporte.setDescripcion(reporteDTO.descripcion());
        if (reporteDTO.ubicacion() != null) reporte.setUbicacion(reporteDTO.ubicacion());
        if (reporteDTO.imagenes() != null) reporte.setImagenes(reporteDTO.imagenes());

        return reporteMapper.toReporteResponseDTO(reporte);
    }

    @Override
    public void eliminarReporte(String id) throws Exception {
        if (!reporteStore.containsKey(id)) {
            throw new ResourceNotFoundException("Reporte no encontrado");
        }

        reporteStore.remove(id);
    }
}