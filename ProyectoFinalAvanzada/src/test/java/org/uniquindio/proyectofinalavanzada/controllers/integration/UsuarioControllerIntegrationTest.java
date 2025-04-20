package org.uniquindio.proyectofinalavanzada.controllers.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.uniquindio.proyectofinalavanzada.data.TestDataLoader;
import org.uniquindio.proyectofinalavanzada.domain.Rol;
import org.uniquindio.proyectofinalavanzada.domain.UsuarioEstado;
import org.uniquindio.proyectofinalavanzada.dtos.UsuarioEditarDTO;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.dtos.UsuarioResponseDTO;
import org.uniquindio.proyectofinalavanzada.repositories.UsuarioRepository;
import org.springframework.security.test.context.support.WithMockUser;
import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Map<String, Usuario> usuarios;

    @BeforeEach
    void setUp() {
        usuarios = TestDataLoader.loadTestData(usuarioRepository, mongoTemplate, passwordEncoder);
    }

    @Test
    void testObtenerUsuarioSuccess() throws Exception {
        var usuarioExistente = usuarios.values().stream().findAny().orElseThrow();

        mockMvc.perform(get("/api/auth/perfil/{id}", usuarioExistente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(usuarioExistente.getNombre()))
                .andExpect(jsonPath("$.correo").value(usuarioExistente.getCorreo()));
    }

    @Test
    void testObtenerUsuarioNotFound() throws Exception {
        mockMvc.perform(get("/api/auth/perfil/{id}", "id-inexistente"))
                .andExpect(status().isNotFound());
    }
/*
    @Test
    @WithMockUser // Añadir autenticación mock
    void testEditarPerfilSuccess() throws Exception {
        // Crear y guardar usuario primero
        Usuario usuario = new Usuario(
                UUID.randomUUID().toString(),
                "Original",
                "Original",
                "123",
                "Original",
                "original@example.com",
                passwordEncoder.encode("password"),
                null,
                Rol.USER,
                true,
                UsuarioEstado.ACTIVO
        );
        usuarioRepository.save(usuario);

        UsuarioEditarDTO dto = new UsuarioEditarDTO(
                "Nombre Modificado",
                "Ciudad Modificada",
                "9876543",
                "Dirección Modificada",
                "modificado@example.com",
                "nuevacontraseña",
                null
        );

        mockMvc.perform(put("/api/auth/perfil")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Datos actualizados correctamente."));
    }

 */
}