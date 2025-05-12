package org.uniquindio.proyectofinalavanzada.data;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.domain.Reporte;
import org.uniquindio.proyectofinalavanzada.domain.ReporteEstado;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.repositories.ReporteRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TestDataLoaderReporte {
    public static Map<String, Reporte> loadReporteTestData(
            ReporteRepository reporteRepository,
            MongoTemplate mongoTemplate,
            Map<String, Usuario> usuarios) {

        mongoTemplate.getDb().listCollectionNames()
                .forEach(mongoTemplate::dropCollection);

        List<Reporte> reportes = new ArrayList<>();

        Usuario usuario = usuarios.values().stream().findFirst().orElseThrow();

        ArrayList<Categoria> categorias=new ArrayList<>();
        ArrayList<String> imagenes=new ArrayList<>();

        Reporte reporte1 = new Reporte(
                UUID.randomUUID().toString(), // id
                "Hueco en la calle", // titulo
                categorias,
                "Se encuentra un hueco peligroso en la calle principal", // descripcion
                LocalDateTime.now(), // fecha
                new GeoJsonPoint(-75.6900, 4.5400), // ubicacion (GeoJsonPoint con latitud y longitud)
                imagenes,
                ReporteEstado.PENDIENTE,
                new ObjectId(usuario.getId()), // usuarioId (ObjectId debe usarse con un id de usuario)
                0 // nivelImportancia (valor predeterminado, se puede modificar)
        );

        Reporte reporte2 = new Reporte(
                UUID.randomUUID().toString(), // id
                "Arbol Caido", // titulo
                categorias,
                "Un arbol cayo en la via y bloquea el paso", // descripcion
                LocalDateTime.now(), // fecha
                new GeoJsonPoint(-75.6900, 4.5400), // ubicacion (GeoJsonPoint con latitud y longitud)
                imagenes,
                ReporteEstado.PENDIENTE,
                new ObjectId(usuario.getId()), // usuarioId (ObjectId debe usarse con un id de usuario)
                0 // nivelImportancia (valor predeterminado, se puede modificar)
        );

        reportes.add(reporte1);
        reportes.add(reporte2);

        return reporteRepository.saveAll(reportes).stream()
                .collect(Collectors.toMap(Reporte::getId, r -> r));
    }
}
