package org.uniquindio.proyectofinalavanzada.controllers.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.uniquindio.proyectofinalavanzada.data.TestDataLoader;
import org.uniquindio.proyectofinalavanzada.domain.UsuarioEstado;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.repositories.UsuarioRepository;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.domain.Rol;

import java.util.Map;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

        mockMvc.perform(post("/api/auth/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").exists());
    }

    @Test
    void testRegistrarUsuarioThrowsValueConflictException() throws Exception {
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

        mockMvc.perform(post("/api/auth/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict());
    }
/*
    @Test
// @DisplayName("Debería autenticar usuario exitosamente") // Opcional si resolviste el primer error
    void testLoginSuccess() throws Exception {
        // 1. Configuración inicial
        String rawPassword = "testPassword123";
        String email = "login_test@example.com";

        // 2. Limpiar usuario existente si hay
        usuarioRepository.findByCorreo(email).ifPresent(usuarioRepository::delete);

        // 3. Crear usuario de prueba
        Usuario usuarioLogin = new Usuario(
                UUID.randomUUID().toString(),
                "Usuario Test Login",
                "Armenia",
                "1234567",
                "Calle Test 123",
                email,
                passwordEncoder.encode(rawPassword),
                null,
                Rol.USER,
                true,
                UsuarioEstado.ACTIVO
        );

        // 4. Verificar que se guardó
        Usuario guardado = usuarioRepository.save(usuarioLogin);
        assertNotNull(guardado.getId(), "El usuario no se guardó correctamente");

        // 5. Preparar solicitud
        LoginDTO dto = new LoginDTO(email, rawPassword);

        // 6. Ejecutar y verificar
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.usuario.correo").value(email));

        // 7. Limpieza
        usuarioRepository.delete(guardado);
    }

 */
}