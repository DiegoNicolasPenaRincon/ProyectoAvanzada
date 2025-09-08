package org.uniquindio.proyectofinalavanzada.controllers.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.uniquindio.proyectofinalavanzada.controllers.UsuarioController;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.services.UsuarioService;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerUnitTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    // Declarar las variables como campos de clase
    private UsuarioEditarDTO usuarioEditarDTO;
    private UsuarioResponseDTO usuarioResponseDTO;
    private MensajeDTO mensajeDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        // Inicializar los DTOs
        usuarioEditarDTO = new UsuarioEditarDTO(
                "Juan Pérez Modificado",
                "Pereira",
                "7654321",
                "Carrera 456",
                "juan.modificado@example.com",
                "newpassword123",
                null
        );

        usuarioResponseDTO = new UsuarioResponseDTO(
                UUID.randomUUID().toString(),
                "Juan Pérez",
                "Armenia",
                "1234567",
                "Calle 123",
                "juan@example.com",
                null
        );

        mensajeDTO = new MensajeDTO("Mensaje de prueba");
    }

    @Test
    void testEditarPerfilSuccess() throws Exception {
        // Configurar el mock para devolver el mensajeDTO cuando se edite el perfil
        when(usuarioService.editarPerfil(anyString(), Mockito.any(UsuarioEditarDTO.class)))
                .thenReturn(usuarioResponseDTO);

        mockMvc.perform(put("/api/auth/perfil")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usuarioEditarDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Datos actualizados correctamente."));
    }

    @Test
    void testEliminarPerfilSuccess() throws Exception {
        // Configurar el mock para no hacer nada (método void)
        Mockito.doNothing().when(usuarioService).eliminarPerfil(anyString(), Mockito.anyBoolean());

        mockMvc.perform(delete("/api/auth/perfil")
                        .param("confirmar", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Cuenta eliminada correctamente."));
    }
    
/*
    @Test
    void testEliminarPerfilThrowsValueConflictException() throws Exception {
        Mockito.doThrow(ValueConflictException.class)
                .when(usuarioService).eliminarPerfil(anyString(), Mockito.anyBoolean());

        mockMvc.perform(delete("/api/auth/perfil")
                        .param("confirmar", "false"))
                .andExpect(status().isConflict());
    }

 */

    @Test
    void testObtenerUsuarioSuccess() throws Exception {
        when(usuarioService.obtenerUsuario(anyString()))
                .thenReturn(Optional.of(usuarioResponseDTO));

        mockMvc.perform(get("/api/auth/perfil/test-id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"));
    }

    @Test
    void testObtenerUsuarioNotFound() throws Exception {
        when(usuarioService.obtenerUsuario(anyString()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/auth/perfil/{id}", "usuario-inexistente"))
                .andExpect(status().isNotFound());
    }
}