package org.uniquindio.proyectofinalavanzada.repositories;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniquindio.proyectofinalavanzada.domain.Notificacion;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificacionRepository extends MongoRepository<Notificacion, String> {
    Optional<Notificacion> findNotificacionById(String id);
    List<Notificacion> findAllByUsuarioId(@NotNull ObjectId usuarioId);

}
