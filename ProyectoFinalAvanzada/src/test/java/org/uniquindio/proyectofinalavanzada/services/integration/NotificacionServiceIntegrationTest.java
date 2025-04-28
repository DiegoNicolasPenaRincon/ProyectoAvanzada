package org.uniquindio.proyectofinalavanzada.services.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.uniquindio.proyectofinalavanzada.dtos.NotificacionDTO;
import org.uniquindio.proyectofinalavanzada.dtos.NotificacionResponseDTO;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.repositories.NotificacionRepository;
import org.uniquindio.proyectofinalavanzada.services.NotificacionService;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NotificacionServiceIntegrationTest {

    @Autowired
    private NotificacionService notificacionService;

    @Autowired
    private NotificacionRepository notificacionRepository;

    @BeforeEach
    void setUp() {
        notificacionRepository.deleteAll();
    }

    @Test
    void testEnviarNotificacionSuccess() throws Exception {
        NotificacionDTO dto = new NotificacionDTO(
                "507f1f77bcf86cd799439011",
                "507f1f77bcf86cd799439012",
                "Mensaje de prueba"
        );

        NotificacionResponseDTO result = notificacionService.enviarNotificacion(dto);

        assertNotNull(result.id());
        assertEquals(dto.mensaje(), result.mensaje());
    }
/*
    @Test
    void testEnviarNotificacionThrowsValueConflictException() throws Exception {
        NotificacionDTO dto = new NotificacionDTO(
                "507f1f77bcf86cd799439011",
                "507f1f77bcf86cd799439012",
                "Mensaje de prueba"
        );

        notificacionService.enviarNotificacion(dto);

        assertThrows(ValueConflictException.class, () -> {
            notificacionService.enviarNotificacion(dto);
        });
    }

 */

    @Test
    void testListarNotificacionesUsuarioSuccess() throws Exception {
        notificacionService.enviarNotificacion(new NotificacionDTO(
                "507f1f77bcf86cd799439011",
                "507f1f77bcf86cd799439012",
                "Mensaje 1"
        ));

        notificacionService.enviarNotificacion(new NotificacionDTO(
                "507f1f77bcf86cd799439011",
                "507f1f77bcf86cd799439013",
                "Mensaje 2"
        ));

        List<NotificacionResponseDTO> result = notificacionService.listarNotificacionesUsuario("507f1f77bcf86cd799439011");

        assertEquals(2, result.size());
    }
}