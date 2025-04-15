package org.uniquindio.proyectofinalavanzada.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.services.NotificacionService;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<MensajeDTO> enviarNotificacion(@Valid @RequestBody NotificacionDTO notificacionDTO) throws Exception {
        notificacionService.enviarNotificacion(notificacionDTO);
        return ResponseEntity.ok(new MensajeDTO("Notificaciones enviadas correctamente."));
    }

    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>> listarNotificaciones() {
        // En una implementación real, el ID del usuario vendría del token
        String usuarioId = "usuario-simulado";
        return ResponseEntity.ok(notificacionService.listarNotificacionesUsuario(usuarioId));
    }

    @PutMapping("/{id}/leer")
    public ResponseEntity<MensajeDTO> marcarComoLeida(@PathVariable String id) throws Exception {
        notificacionService.marcarNotificacionComoLeida(id);
        return ResponseEntity.ok(new MensajeDTO("Notificación marcada como leída."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> obtenerNotificacion(@PathVariable String id) {
        return ResponseEntity.ok(notificacionService.obtenerNotificacion(id));
    }
}