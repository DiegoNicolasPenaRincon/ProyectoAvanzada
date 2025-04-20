package org.uniquindio.proyectofinalavanzada.services;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.uniquindio.proyectofinalavanzada.domain.Comentario;
import org.uniquindio.proyectofinalavanzada.domain.Notificacion;
import org.uniquindio.proyectofinalavanzada.dtos.ComentarioDTO;
import org.uniquindio.proyectofinalavanzada.dtos.ComentarioResponseDTO;
import org.uniquindio.proyectofinalavanzada.dtos.NotificacionResponseDTO;
import org.uniquindio.proyectofinalavanzada.exception.ProblemasServidorException;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.mappers.ComentarioMapper;
import org.uniquindio.proyectofinalavanzada.repositories.ComentarioRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {

    private final Map<String, Comentario> comentarioStore = new ConcurrentHashMap<>();
    private final ComentarioMapper comentarioMapper;
    private final ComentarioRepository comentarioRepository;

    @Override
    public ComentarioResponseDTO agregarComentario(String reporteId, ComentarioDTO comentarioDTO) throws Exception {
        Comentario comentario = comentarioMapper.toComentario(comentarioDTO);
        boolean finalizarSeleccionId=false;
        int contador=0;
        while(!finalizarSeleccionId)
        {
            String comentarioId=String.valueOf((int) (Math.random() * 1000) + 1);
            if(comentarioRepository.findComentarioById(comentarioId).isEmpty())
            {
                finalizarSeleccionId=true;
            }

            contador++;

            if(contador==1000)
            {
                throw new ProblemasServidorException("El servidor esta presentando problemas con la creacion y asignacion de comentarios");
            }
        }
        ObjectId casteoAObjectId=new ObjectId(reporteId);
        comentario.setReporteId(casteoAObjectId);
        comentarioRepository.save(comentario);
        return comentarioMapper.toComentarioResponseDTO(comentario);
    }

    @Override
    public List<ComentarioResponseDTO> listarComentarios(String reporteId) {
        ObjectId reporteIdBuscableEnMongo=new ObjectId(reporteId);
        List<Comentario> listaBuenaComentario=new ArrayList<>();
        listaBuenaComentario=comentarioRepository.findAllByReporteId(reporteIdBuscableEnMongo);
        List<ComentarioResponseDTO> listaDevuelta=new ArrayList<>();
        for(int i=0;i<listaBuenaComentario.size();i++)
        {
            listaDevuelta.add(comentarioMapper.toComentarioResponseDTO(listaBuenaComentario.get(i)));
        }
        return listaDevuelta;
    }

    @Override
    public void editarComentario(String reporteId, String comentarioId, ComentarioDTO comentarioDTO) throws Exception {
        Optional<Comentario> comentarioEncontrado=comentarioRepository.findComentarioById(comentarioId);
        if (comentarioEncontrado.isEmpty())
        {
            throw new ResourceNotFoundException("Comentario no encontrado");
        }
        Comentario comentario=comentarioEncontrado.get();
        comentario.setContenido(comentarioDTO.contenido());
        comentarioRepository.save(comentario);
    }

    @Override
    public void eliminarComentario(String comentarioId) {
        Optional<Comentario> comentarioEncontrado=comentarioRepository.findComentarioById(comentarioId);
        if (comentarioEncontrado.isEmpty())
        {
            throw new ResourceNotFoundException("Comentario no encontrado");
        }
        comentarioRepository.delete(comentarioEncontrado.get());
    }

    /*@Override
    public ComentarioResponseDTO agregarComentario(String reporteId, ComentarioDTO comentarioDTO) throws Exception {
        Comentario comentario = comentarioMapper.toComentario(comentarioDTO);
        ObjectId casteoAObjectId=new ObjectId(reporteId);
        comentario.setReporteId(casteoAObjectId);
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

     */
}