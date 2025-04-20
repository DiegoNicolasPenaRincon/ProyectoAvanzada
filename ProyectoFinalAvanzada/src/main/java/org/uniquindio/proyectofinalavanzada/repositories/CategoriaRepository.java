package org.uniquindio.proyectofinalavanzada.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, String> {
    Optional<Categoria> findByNombre(String nombre);
}
