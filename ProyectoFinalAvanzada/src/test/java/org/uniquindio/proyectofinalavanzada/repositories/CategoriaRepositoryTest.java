package org.uniquindio.proyectofinalavanzada.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    void setUp() {
        categoriaRepository.deleteAll();
    }

    @Test
    void testFindByNombreSuccess() {
        Categoria categoria = new Categoria("1", "Electrónica", "Descripción");
        categoriaRepository.save(categoria);

        Optional<Categoria> result = categoriaRepository.findByNombre("Electrónica");

        assertTrue(result.isPresent());
        assertEquals("Electrónica", result.get().getNombre());
    }

    @Test
    void testFindByNombreNotFound() {
        Optional<Categoria> result = categoriaRepository.findByNombre("NoExistente");

        assertFalse(result.isPresent());
    }
}