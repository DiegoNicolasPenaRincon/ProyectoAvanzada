package org.uniquindio.proyectofinalavanzada.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    @Transactional
    void deleteByCorreo(String correo);
    boolean existsByCorreoIgnoreCase(String correo);
    Optional<Usuario> findByCorreoIgnoreCase(String correo);
    Optional<Usuario> findUsuarioByCorreo(String correo);
    Optional<Usuario> findByCorreoIgnoreCaseAndContraseña(String correo, String contraseña);
    Optional<Usuario> findByCorreo(String correo);
}
