package org.uniquindio.proyectofinalavanzada.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniquindio.proyectofinalavanzada.domain.Comentario;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentario, String> {
}
