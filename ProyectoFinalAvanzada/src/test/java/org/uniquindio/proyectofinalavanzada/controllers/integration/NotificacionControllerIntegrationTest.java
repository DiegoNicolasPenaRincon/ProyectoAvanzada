package org.uniquindio.proyectofinalavanzada.controllers.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.uniquindio.proyectofinalavanzada.domain.Notificacion;
import org.uniquindio.proyectofinalavanzada.repositories.NotificacionRepository;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NotificacionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NotificacionRepository notificacionRepository;

    @BeforeEach
    void setUp() {
        notificacionRepository.deleteAll();
    }

    @Test
    void testEnviarNotificacionSuccess() throws Exception {
        mockMvc.perform(post("/api/notificaciones")
                        .contentType("application/json")
                        .content("{\"usuarioId\":\"507f1f77bcf86cd799439011\",\"reporteId\":\"507f1f77bcf86cd799439012\",\"mensaje\":\"Mensaje de prueba\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Notificaciones enviadas correctamente."));
    }
/*
    @Test
    void testListarNotificacionesSuccess() throws Exception {
        // Insertar datos de prueba
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuarioId(new org.bson.types.ObjectId("507f1f77bcf86cd799439011"));
        notificacion.setReporteId(new org.bson.types.ObjectId("507f1f77bcf86cd799439012"));
        notificacion.setMensaje("Mensaje de prueba");
        notificacion.setFecha(LocalDateTime.now());
        notificacionRepository.save(notificacion);

        mockMvc.perform(get("/api/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

 */
}