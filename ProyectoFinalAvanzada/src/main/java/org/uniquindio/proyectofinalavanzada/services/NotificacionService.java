package org.uniquindio.proyectofinalavanzada.services;

import org.uniquindio.proyectofinalavanzada.dtos.*;

import java.util.List;

public interface NotificacionService {
    NotificacionResponseDTO enviarNotificacion(NotificacionDTO notificacionDTO) throws Exception;
    List<NotificacionResponseDTO> listarNotificacionesUsuario(String usuarioId);
    void marcarNotificacionComoLeida(String id) throws Exception;
    NotificacionResponseDTO obtenerNotificacion(String id);
}