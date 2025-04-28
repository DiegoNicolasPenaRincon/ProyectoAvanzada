package org.uniquindio.proyectofinalavanzada.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.uniquindio.proyectofinalavanzada.domain.Notificacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class NotificacionRepositoryTest {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @BeforeEach
    void setUp() {
        notificacionRepository.deleteAll();
    }

    @Test
    void testFindNotificacionByIdSuccess() {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje("Mensaje de prueba");
        notificacionRepository.save(notificacion);

        Optional<Notificacion> result = notificacionRepository.findNotificacionById(notificacion.getId());

        assertTrue(result.isPresent());
        assertEquals("Mensaje de prueba", result.get().getMensaje());
    }

    @Test
    void testFindAllByUsuarioIdSuccess() {
        org.bson.types.ObjectId usuarioId = new org.bson.types.ObjectId("507f1f77bcf86cd799439011");

        Notificacion notificacion1 = new Notificacion();
        notificacion1.setUsuarioId(usuarioId);
        notificacion1.setMensaje("Mensaje 1");

        Notificacion notificacion2 = new Notificacion();
        notificacion2.setUsuarioId(usuarioId);
        notificacion2.setMensaje("Mensaje 2");

        notificacionRepository.save(notificacion1);
        notificacionRepository.save(notificacion2);

        List<Notificacion> result = notificacionRepository.findAllByUsuarioId(usuarioId);

        assertEquals(2, result.size());
    }
}