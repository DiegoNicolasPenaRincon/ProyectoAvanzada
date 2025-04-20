package org.uniquindio.proyectofinalavanzada.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
