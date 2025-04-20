package org.uniquindio.proyectofinalavanzada.services;

import org.uniquindio.proyectofinalavanzada.dtos.*;

import java.util.List;

public interface ComentarioService {
    ComentarioResponseDTO agregarComentario(String reporteId, ComentarioDTO comentarioDTO) throws Exception;
    List<ComentarioResponseDTO> listarComentarios(String reporteId);
    void editarComentario(String reporteId, String comentarioId, ComentarioDTO comentarioDTO) throws Exception;

    void eliminarComentario(String comentarioId);
    //void eliminarComentario(String reporteId, String comentarioId) throws Exception;
}