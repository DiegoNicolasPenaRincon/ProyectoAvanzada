package org.uniquindio.proyectofinalavanzada.controllers.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.uniquindio.proyectofinalavanzada.controllers.AuthController;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.services.UsuarioService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthControllerUnitTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private AuthController authController;

    private UsuarioRegistroDTO usuarioRegistroDTO;
    private LoginDTO loginDTO;
    private VerificarCodigoDTO verificarCodigoDTO;
    private UsuarioResponseDTO usuarioResponseDTO;
    private LoginResponseDTO loginResponseDTO;

    @BeforeEach
    void setUp() {
        // Configuración manual sin @Autowired
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();

        usuarioRegistroDTO = new UsuarioRegistroDTO(
                UUID.randomUUID().toString(),
                "Juan Pérez",
                "Armenia",
                "1234567",
                "Calle 123",
                "juan@example.com",
                "password123",
                null
        );

        loginDTO = new LoginDTO("juan@example.com", "password123");
        verificarCodigoDTO = new VerificarCodigoDTO("juan@example.com", "123456");

        usuarioResponseDTO = new UsuarioResponseDTO(
                UUID.randomUUID().toString(),
                "Juan Pérez",
                "Armenia",
                "1234567",
                "Calle 123",
                "juan@example.com",
                null
        );

        loginResponseDTO = new LoginResponseDTO("token-simulado", usuarioResponseDTO);
    }

    @Test
    void testRegistrarUsuarioSuccess() throws Exception {
        // Configuración del mock usando when() importado estáticamente
        when(usuarioService.registrarUsuario(any(UsuarioRegistroDTO.class)))
                .thenReturn(usuarioResponseDTO);

        mockMvc.perform(post("/api/auth/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usuarioRegistroDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Usuario registrado con éxito. Verifique su correo electrónico."));
    }
/*
    @Test
    void testRegistrarUsuarioThrowsValueConflictException() throws Exception {
        when(usuarioService.registrarUsuario(any(UsuarioRegistroDTO.class)))
                .thenThrow(new ValueConflictException("El correo ya está registrado"));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioRegistroDTO)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.mensaje").value("El correo ya está registrado"));
    }

 */

    @Test
    void testVerificarCuentaSuccess() throws Exception {
        Mockito.when(usuarioService.verificarCuenta(Mockito.any(VerificarCodigoDTO.class)))
                .thenReturn(usuarioResponseDTO);

        mockMvc.perform(post("/api/auth/verify")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(verificarCodigoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Cuenta activada con éxito."));
    }
/*
    @Test
    void testVerificarCuentaThrowsResourceNotFoundException() throws Exception {
        Mockito.when(usuarioService.verificarCuenta(Mockito.any(VerificarCodigoDTO.class)))
                .thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(post("/api/auth/verify")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(verificarCodigoDTO)))
                .andExpect(status().isNotFound());
    }

 */

    @Test
    void testLoginSuccess() throws Exception {
        Mockito.when(usuarioService.login(Mockito.any(LoginDTO.class)))
                .thenReturn(loginResponseDTO);

        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token-simulado"))
                .andExpect(jsonPath("$.usuario.correo").value("juan@example.com"));
    }
/*
    @Test
    void testLoginThrowsResourceNotFoundException() throws Exception {
        Mockito.when(usuarioService.login(Mockito.any(LoginDTO.class)))
                .thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isNotFound());
    }

 */
}