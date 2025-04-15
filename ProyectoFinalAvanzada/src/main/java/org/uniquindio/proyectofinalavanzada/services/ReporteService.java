package org.uniquindio.proyectofinalavanzada.services;

import org.uniquindio.proyectofinalavanzada.dtos.*;

import java.util.List;

public interface ReporteService {
    ReporteResponseDTO crearReporte(ReporteDTO reporteDTO) throws Exception;
    List<ReporteResponseDTO> listarReportes(String categoria, String estado);
    ReporteResponseDTO editarReporte(String id, ReporteDTO reporteDTO) throws Exception;
    void eliminarReporte(String id) throws Exception;
}