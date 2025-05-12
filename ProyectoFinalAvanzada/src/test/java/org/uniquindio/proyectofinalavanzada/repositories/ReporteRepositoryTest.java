package org.uniquindio.proyectofinalavanzada.repositories;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.uniquindio.proyectofinalavanzada.config.TestSecurityConfig;
import org.uniquindio.proyectofinalavanzada.data.TestDataLoader;
import org.uniquindio.proyectofinalavanzada.data.TestDataLoaderReporte;
import org.uniquindio.proyectofinalavanzada.domain.Reporte;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;

import java.util.Map;

@DataMongoTest
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
public class ReporteRepositoryTest {

        @Autowired
        private ReporteRepository reporteRepository;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private MongoTemplate mongoTemplate;

        @Autowired
        private PasswordEncoder passwordEncoder;

        private Map<String, Reporte> reportes;

        @BeforeEach
        void setUp() {
            Map<String, Usuario> usuarios = TestDataLoader.loadTestData(usuarioRepository, mongoTemplate, passwordEncoder);
            reportes = TestDataLoaderReporte.loadReporteTestData(reporteRepository, mongoTemplate, usuarios);
        }
/*
        @Test
        void testFindByEstadoSuccess() {
            List<Reporte> pendientes = reporteRepository.findByEstado(EstadoReporte.PENDIENTE);
            assertFalse(pendientes.isEmpty());
            assertEquals(EstadoReporte.PENDIENTE, pendientes.get(0).getEstado());
        }

        @Test
        void testFindByUsuarioIdSuccess() {
            String usuarioId = reportes.values().stream().findFirst().orElseThrow().getUsuario().getId();

            List<Reporte> usuarioReportes = reporteRepository.findByUsuarioId(usuarioId);

            assertFalse(usuarioReportes.isEmpty());
            assertEquals(usuarioId, usuarioReportes.get(0).getUsuario().getId());
        }
    }

 */
}
