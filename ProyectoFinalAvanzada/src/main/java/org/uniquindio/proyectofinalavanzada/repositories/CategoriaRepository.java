package org.uniquindio.proyectofinalavanzada.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, String> {

}
