package org.uniquindio.proyectofinalavanzada.controllers;

import org.uniquindio.proyectofinalavanzada.repositories.ReporteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniquindio.proyectofinalavanzada.domain.Reporte;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.services.ReporteService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;
    private final ReporteRepository reporteRepository;
/*
    @PostMapping
    public ResponseEntity<ReporteResponseDTO> crearReporte(@Valid @RequestBody ReporteDTO reporteDTO) throws Exception {
        ReporteResponseDTO response = reporteService.crearReporte(reporteDTO);
        return ResponseEntity.created(URI.create("/api/reportes/" + response.id()))
                .body(response);
    }


 */

    @PostMapping
    public ResponseEntity<Reporte> crearReporte(@RequestBody Reporte reporte) {
        return ResponseEntity.ok(reporteRepository.save(reporte));
    }

    @GetMapping
    public ResponseEntity<List<ReporteResponseDTO>> listarReportes(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String estado) {

        return ResponseEntity.ok(reporteService.listarReportes(categoria, estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO> editarReporte(
            @PathVariable String id,
            @Valid @RequestBody ReporteDTO reporteDTO) throws Exception {

        reporteService.editarReporte(id, reporteDTO);
        return ResponseEntity.ok(new MensajeDTO("Reporte actualizado correctamente."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO> eliminarReporte(@PathVariable String id) throws Exception {
        reporteService.eliminarReporte(id);
        return ResponseEntity.ok(new MensajeDTO("Reporte eliminado correctamente."));
    }
}