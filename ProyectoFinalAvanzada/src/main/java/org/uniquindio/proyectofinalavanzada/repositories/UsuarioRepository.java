package org.uniquindio.proyectofinalavanzada.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    boolean existsByCorreoIgnoreCase(String correo);
    Optional<Usuario> findByCorreoIgnoreCase(String correo);
    Optional<Usuario> findByCorreoIgnoreCaseAndContraseña(String correo, String contraseña);
}
