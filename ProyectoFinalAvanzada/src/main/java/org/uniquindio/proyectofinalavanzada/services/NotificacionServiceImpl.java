package org.uniquindio.proyectofinalavanzada.services;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.uniquindio.proyectofinalavanzada.domain.Notificacion;
import org.uniquindio.proyectofinalavanzada.dtos.NotificacionDTO;
import org.uniquindio.proyectofinalavanzada.dtos.NotificacionResponseDTO;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.mappers.NotificacionMapper;
import org.uniquindio.proyectofinalavanzada.repositories.NotificacionRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements NotificacionService {

    private final Map<String, Notificacion> notificacionStore = new ConcurrentHashMap<>();
    private final NotificacionMapper notificacionMapper;
    private final NotificacionRepository repositorioNotificacion;


    @Override
    public NotificacionResponseDTO enviarNotificacion(NotificacionDTO notificacionDTO) {
        if(repositorioNotificacion.findNotificacionById(notificacionDTO.reporteId()).isPresent())
        {
            throw new ValueConflictException("Esa notificacion ya se encuentra creada");
        }
        Notificacion notificacion = notificacionMapper.toNotificacion(notificacionDTO);
        repositorioNotificacion.save(notificacion);
        return notificacionMapper.toNotificacionResponseDTO(notificacion);
    }

    @Override
    public List<NotificacionResponseDTO> listarNotificacionesUsuario(String usuarioId) {
        ObjectId usuariIdBuscableEnMongo=new ObjectId(usuarioId);
        List<Notificacion> listaBuenaNotificaciones=new ArrayList<>();
        listaBuenaNotificaciones=repositorioNotificacion.findAllByUsuarioId(usuariIdBuscableEnMongo);
        List<NotificacionResponseDTO> listaDevuelta=new ArrayList<>();
        for(int i=0;i<listaBuenaNotificaciones.size();i++)
        {
            listaDevuelta.add(notificacionMapper.toNotificacionResponseDTO(listaBuenaNotificaciones.get(i)));
        }
        return listaDevuelta;
    }

    @Override
    public void marcarNotificacionComoLeida(String id) throws Exception {;
        if (repositorioNotificacion.findNotificacionById(id).isEmpty())
        {
            throw new ResourceNotFoundException("Notificación no encontrada");
        }
        Notificacion notificacion=repositorioNotificacion.findNotificacionById(id).get();
        notificacion.setLeida(true);
        repositorioNotificacion.save(notificacion);
    }

    @Override
    public NotificacionResponseDTO obtenerNotificacion(String id) {
        Optional<Notificacion> notificacionBacana=repositorioNotificacion.findById(id);
        if(notificacionBacana.isEmpty())
        {
            throw new ResourceNotFoundException("Notificación no encontrada");
        }
        Notificacion notificacion=notificacionBacana.get();
        return notificacionMapper.toNotificacionResponseDTO(notificacion);

    }

    /*@Override
    public NotificacionResponseDTO enviarNotificacion(NotificacionDTO notificacionDTO) throws Exception {
        Notificacion notificacion = notificacionMapper.toNotificacion(notificacionDTO);
        notificacionStore.put(notificacion.getId(), notificacion);
        return notificacionMapper.toNotificacionResponseDTO(notificacion);
    }

    @Override
    public List<NotificacionResponseDTO> listarNotificacionesUsuario(String usuarioId) {
        return notificacionStore.values().stream()
                .filter(n -> n.getUsuarioId().equals(usuarioId))
                .map(notificacionMapper::toNotificacionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void marcarNotificacionComoLeida(String id) throws Exception {
        Notificacion notificacion = notificacionStore.get(id);
        if (notificacion == null) {
            throw new ResourceNotFoundException("Notificación no encontrada");
        }
        notificacion.setLeida(true);
    }

    @Override
    public NotificacionResponseDTO obtenerNotificacion(String id) {
        return Optional.ofNullable(notificacionStore.get(id))
                .map(notificacionMapper::toNotificacionResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Notificación no encontrada"));
    }

     */
}