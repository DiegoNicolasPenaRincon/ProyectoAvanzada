package org.uniquindio.proyectofinalavanzada.services.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.uniquindio.proyectofinalavanzada.data.TestDataLoader;
import org.uniquindio.proyectofinalavanzada.domain.Rol;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.domain.UsuarioEstado;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.repositories.UsuarioRepository;
import org.uniquindio.proyectofinalavanzada.services.UsuarioService;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceIntegrationTest {

    @Autowired
    private UsuarioService usuarioService;

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

    @Test
    void testRegistrarUsuarioSuccess() throws Exception {
        UsuarioRegistroDTO dto = new UsuarioRegistroDTO(
                UUID.randomUUID().toString(),
                "Nuevo Usuario",
                "Armenia",
                "1234567",
                "Calle 123",
                "nuevo@example.com",
                "password123",
                null
        );

        UsuarioResponseDTO result = usuarioService.registrarUsuario(dto);

        assertNotNull(result.id());
        assertEquals(dto.nombre(), result.nombre());
        assertEquals(dto.correo(), result.correo());

        Optional<Usuario> usuarioGuardado = usuarioRepository.findById(result.id());
        assertTrue(usuarioGuardado.isPresent());
        assertFalse(usuarioGuardado.get().isVerificado());
    }

    @Test
    void testRegistrarUsuarioThrowsValueConflictExceptionWhenEmailExists() {
        var usuarioExistente = usuarios.values().stream().findAny().orElseThrow();

        UsuarioRegistroDTO dto = new UsuarioRegistroDTO(
                UUID.randomUUID().toString(),
                "Nuevo Usuario",
                "Armenia",
                "1234567",
                "Calle 123",
                usuarioExistente.getCorreo(),
                "password123",
                null
        );

        assertThrows(ValueConflictException.class, () ->
                usuarioService.registrarUsuario(dto));
    }
/*
    @Test
    void testVerificarCuentaSuccess() throws Exception {
        var usuarioNoVerificado = usuarios.values().stream()
                .filter(u -> !u.isVerificado())
                .findAny()
                .orElseThrow();

        VerificarCodigoDTO dto = new VerificarCodigoDTO(usuarioNoVerificado.getCorreo(), "123456");

        UsuarioResponseDTO result = usuarioService.verificarCuenta(dto);

        assertTrue(result != null);

        Optional<Usuario> usuarioVerificado = usuarioRepository.findById(usuarioNoVerificado.getId());
        assertTrue(usuarioVerificado.isPresent());
        assertTrue(usuarioVerificado.get().isVerificado());
    }

 */
/*
    @Test
    void testLoginSuccess() {
        // Crear un usuario específico para el test de login
        String rawPassword = "testPassword123";
        Usuario usuarioLogin = new Usuario(
                UUID.randomUUID().toString(),
                "Usuario Login",
                "Armenia",
                "1234567",
                "Calle 123",
                "login@example.com",
                passwordEncoder.encode(rawPassword),
                null,
                Rol.USER,
                true,
                UsuarioEstado.ACTIVO
        );
        usuarioRepository.save(usuarioLogin);

        LoginDTO loginDTO = new LoginDTO("login@example.com", rawPassword);

        var result = assertDoesNotThrow(() -> usuarioService.login(loginDTO));

        assertNotNull(result);
        assertNotNull(result.token());
    }

 */

    @Test
    void testEditarPerfilSuccess() throws Exception {
        var usuarioExistente = usuarios.values().stream().findAny().orElseThrow();

        UsuarioEditarDTO dto = new UsuarioEditarDTO(
                "Nombre Modificado",
                "Ciudad Modificada",
                "9876543",
                "Dirección Modificada",
                "modificado@example.com",
                "nuevacontraseña",
                null
        );

        UsuarioResponseDTO result = usuarioService.editarPerfil(usuarioExistente.getId(), dto);

        assertNotNull(result);
        assertEquals(dto.nombre(), result.nombre());
        assertEquals(dto.ciudadResidencia(), result.ciudadResidencia());

        Optional<Usuario> usuarioModificado = usuarioRepository.findById(usuarioExistente.getId());
        assertTrue(usuarioModificado.isPresent());
        assertEquals(dto.nombre(), usuarioModificado.get().getNombre());
    }

    @Test
    void testObtenerUsuarioSuccess() {
        var usuarioExistente = usuarios.values().stream().findAny().orElseThrow();

        Optional<UsuarioResponseDTO> result = usuarioService.obtenerUsuario(usuarioExistente.getId());

        assertTrue(result.isPresent());
        assertEquals(usuarioExistente.getNombre(), result.get().nombre());
    }
}