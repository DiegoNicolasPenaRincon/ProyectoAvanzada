package org.uniquindio.proyectofinalavanzada.data;

import org.bson.types.ObjectId;
import org.uniquindio.proyectofinalavanzada.domain.Notificacion;
import org.uniquindio.proyectofinalavanzada.repositories.NotificacionRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.Map;

public class TestDataLoaderNotificacion {

    public static Map<String, Notificacion> loadTestData(
            NotificacionRepository notificacionRepository,
            MongoTemplate mongoTemplate) {

        mongoTemplate.getDb().listCollectionNames()
                .forEach(mongoTemplate::dropCollection);

        ObjectId usuarioId1 = new ObjectId("507f1f77bcf86cd799439011");
        ObjectId reporteId1 = new ObjectId("507f1f77bcf86cd799439012");

        Notificacion notificacion1 = new Notificacion();
        notificacion1.setUsuarioId(usuarioId1);
        notificacion1.setReporteId(reporteId1);
        notificacion1.setMensaje("Mensaje de prueba 1");
        notificacion1.setFecha(LocalDateTime.now());

        Notificacion notificacion2 = new Notificacion();
        notificacion2.setUsuarioId(usuarioId1);
        notificacion2.setReporteId(new ObjectId("507f1f77bcf86cd799439013"));
        notificacion2.setMensaje("Mensaje de prueba 2");
        notificacion2.setFecha(LocalDateTime.now());

        notificacionRepository.save(notificacion1);
        notificacionRepository.save(notificacion2);

        return Map.of(
                notificacion1.getId(), notificacion1,
                notificacion2.getId(), notificacion2
        );
    }
}