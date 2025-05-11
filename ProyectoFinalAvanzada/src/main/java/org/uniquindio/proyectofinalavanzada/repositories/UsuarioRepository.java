package org.uniquindio.proyectofinalavanzada.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.domain.UsuarioEstado;
import org.uniquindio.proyectofinalavanzada.dtos.UsuarioResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    @Transactional
    void deleteByCorreo(String correo);
    boolean existsByCorreoIgnoreCase(String correo);
    Optional<Usuario> findByCorreoIgnoreCase(String correo);
    Optional<Usuario> findByCorreoIgnoreCaseAndContraseña(String correo, String contraseña);
    Optional<Usuario> findUserByCorreo(String correo);
    Optional<Usuario> findById(String id);

    @Query(value = "{ 'status': { $ne: 'DELETED' }, 'email': ?0 }")
    Optional<Usuario> findExistingUserByEmail(String email);

    @Query(value = "{ 'status': { $ne: 'DELETED' }, " +
            "  'fullName': { $regex: ?0, $options: 'i' }, " +
            "  'email': { $regex: ?1, $options: 'i' }, " +
            "  ?#{ [2] != null ? 'dateBirth' : '_ignore' } : ?2 }",
            sort = "{ 'fullName': 1 }")
    Page<Usuario> findExistingUsersByFilters(String fullName, String email, LocalDate dateBirth, Pageable pageable);

    List<UsuarioResponseDTO> findByEstadoNot(UsuarioEstado status);
}
