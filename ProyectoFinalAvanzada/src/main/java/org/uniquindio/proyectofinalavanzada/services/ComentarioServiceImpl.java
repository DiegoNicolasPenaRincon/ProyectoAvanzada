package org.uniquindio.proyectofinalavanzada.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.uniquindio.proyectofinalavanzada.domain.Comentario;
import org.uniquindio.proyectofinalavanzada.dtos.ComentarioDTO;
import org.uniquindio.proyectofinalavanzada.dtos.ComentarioResponseDTO;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.mappers.ComentarioMapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {

    private final Map<String, Comentario> comentarioStore = new ConcurrentHashMap<>();
    private final ComentarioMapper comentarioMapper;

    @Override
    public ComentarioResponseDTO agregarComentario(String reporteId, ComentarioDTO comentarioDTO) throws Exception {
        Comentario comentario = comentarioMapper.toComentario(comentarioDTO);
        comentario.setReporteId(reporteId);
        comentarioStore.put(comentario.getId(), comentario);
        return comentarioMapper.toComentarioResponseDTO(comentario);
    }

    @Override
    public List<ComentarioResponseDTO> listarComentarios(String reporteId) {
        return comentarioStore.values().stream()
                .filter(c -> c.getReporteId().equals(reporteId))
                .map(comentarioMapper::toComentarioResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void editarComentario(String reporteId, String comentarioId, ComentarioDTO comentarioDTO) throws Exception {
        Comentario comentario = comentarioStore.get(comentarioId);
        if (comentario == null || !comentario.getReporteId().equals(reporteId)) {
            throw new ResourceNotFoundException("Comentario no encontrado");
        }
        comentario.setContenido(comentarioDTO.contenido());
    }

    @Override
    public void eliminarComentario(String reporteId, String comentarioId) throws Exception {
        Comentario comentario = comentarioStore.get(comentarioId);
        if (comentario == null || !comentario.getReporteId().equals(reporteId)) {
            throw new ResourceNotFoundException("Comentario no encontrado");
        }
        comentarioStore.remove(comentarioId);
    }
}