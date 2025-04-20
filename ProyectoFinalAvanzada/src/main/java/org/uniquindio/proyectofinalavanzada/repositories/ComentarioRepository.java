package org.uniquindio.proyectofinalavanzada.repositories;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniquindio.proyectofinalavanzada.domain.Comentario;
import org.uniquindio.proyectofinalavanzada.domain.Notificacion;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentario, String> {
    Optional<Comentario> findComentarioById(String id);
    List<Comentario> findAllByReporteId(@NotNull ObjectId reporteId);
}
