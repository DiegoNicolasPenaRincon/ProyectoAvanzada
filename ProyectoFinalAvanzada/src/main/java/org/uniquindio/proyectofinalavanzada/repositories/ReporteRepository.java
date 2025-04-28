package org.uniquindio.proyectofinalavanzada.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniquindio.proyectofinalavanzada.domain.Reporte;

import java.util.List;

@Repository
public interface ReporteRepository extends MongoRepository<Reporte, String> {
    List<Reporte> findByCategorias(String categoria);
    List<Reporte> findByEstado(String estado);
    List<Reporte> findByCategoriasAndEstado(String categoria, String estado);
    boolean existByTituloIgnoreCase(String titulo);
}
