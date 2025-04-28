package org.uniquindio.proyectofinalavanzada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.uniquindio.proyectofinalavanzada.domain.Categoria;
import org.uniquindio.proyectofinalavanzada.domain.Comentario;
import org.uniquindio.proyectofinalavanzada.domain.Reporte;
import org.uniquindio.proyectofinalavanzada.dtos.ComentarioResponseDTO;
import org.uniquindio.proyectofinalavanzada.setup.UsuarioPorDefectoPropiedades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Plataforma de Reportes de Seguridad",
                description = "API para reportar incidentes ciudadanos en tiempo real",
                version = "1.0.0"
        )
)
@EnableConfigurationProperties(UsuarioPorDefectoPropiedades.class)
public class ProyectoFinalAvanzadaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProyectoFinalAvanzadaApplication.class, args);

    }

}

