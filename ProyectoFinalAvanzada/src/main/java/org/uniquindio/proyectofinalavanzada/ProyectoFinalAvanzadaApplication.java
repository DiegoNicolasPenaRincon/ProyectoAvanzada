package org.uniquindio.proyectofinalavanzada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Plataforma de Reportes de Seguridad",
                description = "API para reportar incidentes ciudadanos en tiempo real",
                version = "1.0.0"
        )
)
public class ProyectoFinalAvanzadaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProyectoFinalAvanzadaApplication.class, args);
    }
}

