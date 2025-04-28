package org.uniquindio.proyectofinalavanzada.data;

import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.repositories.CategoriaRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Map;

public class TestDataLoaderCategoria {

    public static Map<String, Categoria> loadTestData(
            CategoriaRepository categoriaRepository,
            MongoTemplate mongoTemplate) {

        mongoTemplate.getDb().listCollectionNames()
                .forEach(mongoTemplate::dropCollection);

        Categoria cat1 = new Categoria("1", "Electrónica", "Productos electrónicos");
        Categoria cat2 = new Categoria("2", "Ropa", "Prendas de vestir");

        categoriaRepository.save(cat1);
        categoriaRepository.save(cat2);

        return Map.of(
                cat1.getId(), cat1,
                cat2.getId(), cat2
        );
    }
}