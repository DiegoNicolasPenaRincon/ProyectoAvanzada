package org.uniquindio.proyectofinalavanzada.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.uniquindio.proyectofinalavanzada.config.TestSecurityConfig;
import org.uniquindio.proyectofinalavanzada.data.TestDataLoader;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.domain.UsuarioEstado;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ActiveProfiles("test")
@Import(TestSecurityConfig.class) // Importamos la configuración de seguridad para tests
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Map<String, Usuario> usuarios;

    @BeforeEach
    void setUp() {
        usuarios = TestDataLoader.loadTestData(usuarioRepository, mongoTemplate, passwordEncoder);
    }
/*
    @Test
    void testExistsByCorreoIgnoreCaseWhenUserExists() {
        var testUser = usuarios.values().stream().findAny().orElseThrow();

        boolean exists = usuarioRepository.existsByCorreoIgnoreCase(testUser.getCorreo());

        assertTrue(exists);
    }

    @Test
    void testExistsByCorreoIgnoreCaseWhenUserNotExists() {
        boolean exists = usuarioRepository.existsByCorreoIgnoreCase("noexiste@example.com");

        assertFalse(exists);
    }

    @Test
    void testFindByCorreoIgnoreCaseSuccess() {
        var testUser = usuarios.values().stream().findAny().orElseThrow();

        Optional<Usuario> result = usuarioRepository.findByCorreoIgnoreCase(testUser.getCorreo());

        assertTrue(result.isPresent());
        assertEquals(testUser.getNombre(), result.get().getNombre());
    }

    @Test
    void testFindByCorreoIgnoreCaseAndContraseñaSuccess() {
        var testUser = usuarios.values().stream()
                .filter(u -> u.getCorreo().equals("carlos@example.com"))
                .findAny().orElseThrow();

        Optional<Usuario> result = usuarioRepository.findByCorreoIgnoreCaseAndContraseña(
                testUser.getCorreo(),
                testUser.getContraseña());

        assertTrue(result.isPresent());
        assertEquals(testUser.getNombre(), result.get().getNombre());
    }

    @Test
    void testFindByCorreoIgnoreCaseAndContraseñaFailure() {
        var testUser = usuarios.values().stream().findAny().orElseThrow();

        Optional<Usuario> result = usuarioRepository.findByCorreoIgnoreCaseAndContraseña(
                testUser.getCorreo(),
                "contraseña-incorrecta");

        assertFalse(result.isPresent());
    }
    
 */
}