package org.uniquindio.proyectofinalavanzada.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.uniquindio.proyectofinalavanzada.domain.Reporte;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteDTO;
import org.uniquindio.proyectofinalavanzada.dtos.ReporteResponseDTO;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.mappers.ReporteMapper;
import org.uniquindio.proyectofinalavanzada.repositories.ReporteRepository;
import org.uniquindio.proyectofinalavanzada.services.ReporteService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final Map<String, Reporte> reporteStore = new ConcurrentHashMap<>();
    private final ReporteMapper reporteMapper;
    private final ReporteRepository reporteRepository;

    @Override
    public ReporteResponseDTO crearReporte(ReporteDTO reporteDTO) throws Exception {
        if(reporteRepository.existByTituloIgnoreCase(reporteDTO.titulo())){
            throw new ValueConflictException("El titulo de este reporte ya existe");
        }
        Reporte reporte = reporteMapper.toReporte(reporteDTO);
        reporte = reporteRepository.save(reporte); 
        return reporteMapper.toReporteResponseDTO(reporte);
    }

    @Override
    public List<ReporteResponseDTO> listarReportes(String categoria, String estado) {
        List<Reporte> reportes;

        if (categoria != null && estado != null) {
            reportes = reporteRepository.findByCategoriasAndEstado(categoria, estado);
        } else if (categoria != null) {
            reportes = reporteRepository.findByCategorias(categoria);
        } else if (estado != null) {
            reportes = reporteRepository.findByEstado(estado);
        } else {
            reportes = reporteRepository.findAll();
        }

        return reportes.stream()
                .map(reporteMapper::toReporteResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReporteResponseDTO> listarTodosReportes() {
        List<Reporte> reportes = reporteRepository.findAll();

        return reportes.stream()
                .map(reporteMapper::toReporteResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReporteResponseDTO editarReporte(String id, ReporteDTO reporteDTO) throws Exception {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado"));

        if (reporteDTO.titulo() != null) reporte.setTitulo(reporteDTO.titulo());
        if (reporteDTO.categorias() != null) reporte.getCategorias().addAll(reporteDTO.categorias());
        if (reporteDTO.descripcion() != null) reporte.setDescripcion(reporteDTO.descripcion());
        if (reporteDTO.ubicacion() != null) reporte.setUbicacion(reporteDTO.ubicacion());
        if (reporteDTO.imagenes() != null) reporte.setImagenes(reporteDTO.imagenes());

        reporte = reporteRepository.save(reporte);

        return reporteMapper.toReporteResponseDTO(reporte);
    }

    @Override
    public void eliminarReporte(String id) throws Exception {
        if (!reporteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reporte no encontrado");
        }

        reporteRepository.deleteById(id);
    }

    /*
    @Override
    public ReporteResponseDTO crearReporte(ReporteDTO reporteDTO) throws Exception {
        Reporte reporte = reporteMapper.toReporte(reporteDTO);
        reporteStore.put(reporte.getId(), reporte);

        return reporteMapper.toReporteResponseDTO(reporte);
    }

    @Override
    public List<ReporteResponseDTO> listarReportes(String categoria, String estado) {
        return reporteStore.values().stream()
                .filter(r -> categoria == null || r.getCategorias().equals(categoria))
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
        if (reporteDTO.categorias() != null) reporte.getCategorias().addAll(reporteDTO.categorias());
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

     */
}