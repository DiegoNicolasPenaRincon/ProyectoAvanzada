package org.uniquindio.proyectofinalavanzada.controllers.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.services.NotificacionService;
import org.uniquindio.proyectofinalavanzada.controllers.NotificacionController;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class NotificacionControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private NotificacionService notificacionService;

    @InjectMocks
    private NotificacionController notificacionController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(notificacionController).build();
    }

    @Test
    void testEnviarNotificacionSuccess() throws Exception {
        when(notificacionService.enviarNotificacion(any(NotificacionDTO.class)))
                .thenReturn(new NotificacionResponseDTO(
                        "1", "user1", "report1", "Mensaje", "2023-01-01T10:00", "enlace", false
                ));

        mockMvc.perform(post("/api/notificaciones")
                        .contentType("application/json")
                        .content("{\"usuarioId\":\"user1\",\"reporteId\":\"report1\",\"mensaje\":\"Mensaje\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Notificaciones enviadas correctamente."));
    }

    @Test
    void testListarNotificacionesSuccess() throws Exception {
        List<NotificacionResponseDTO> notificaciones = List.of(
                new NotificacionResponseDTO("1", "user1", "report1", "Mensaje 1", "2023-01-01T10:00", "enlace1", false),
                new NotificacionResponseDTO("2", "user1", "report2", "Mensaje 2", "2023-01-02T10:00", "enlace2", true)
        );

        when(notificacionService.listarNotificacionesUsuario(anyString())).thenReturn(notificaciones);

        mockMvc.perform(get("/api/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].mensaje").value("Mensaje 1"));
    }

    @Test
    void testMarcarComoLeidaSuccess() throws Exception {
        mockMvc.perform(put("/api/notificaciones/1/leer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Notificación marcada como leída."));
    }

    @Test
    void testObtenerNotificacionSuccess() throws Exception {
        NotificacionResponseDTO response = new NotificacionResponseDTO(
                "1", "user1", "report1", "Mensaje", "2023-01-01T10:00", "enlace", false
        );

        when(notificacionService.obtenerNotificacion(anyString())).thenReturn(response);

        mockMvc.perform(get("/api/notificaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.mensaje").value("Mensaje"));
    }
}