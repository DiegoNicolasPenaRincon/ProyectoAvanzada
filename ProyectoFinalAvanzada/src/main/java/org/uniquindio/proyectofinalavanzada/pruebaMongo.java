package org.uniquindio.proyectofinalavanzada;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniquindio.proyectofinalavanzada.domain.GeoPoint;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.domain.Rol;
import org.uniquindio.proyectofinalavanzada.domain.UsuarioEstado;
import org.uniquindio.proyectofinalavanzada.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class pruebaMongo{

    private final UsuarioRepository usuarioRepository;

    @GetMapping("/mongo")
    public ResponseEntity<String> testConexionMongo() {
        try {
            long total = usuarioRepository.count(); // también puedes usar findAll() o existsById()
            return ResponseEntity.ok("✅ Conexión a MongoDB exitosa. Usuarios encontrados: " + total);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("❌ Error conectando a MongoDB: " + e.getMessage());
        }
    }

    @PostMapping("/crear-usuario")
    public ResponseEntity<String> crearUsuarioPrueba() {

        // Crear el punto geográfico (longitud, latitud)
        GeoJsonPoint ubicacion = new GeoJsonPoint(-75.681114, 4.533889); // Armenia

        Usuario nuevoUsuario = Usuario.builder()
                .nombre("Juan Pérez")
                .correo("juan.perez@test.com")
                .telefono("3214567890")
                .direccion("Calle 123 #45-67")
                .ciudadResidencia("Armenia")
                .contraseña("1234segura")
                .ubicacion(ubicacion)
                .rol(Rol.USER)
                .estado(UsuarioEstado.ACTIVO)
                .verificado(false)
                .build();

        usuarioRepository.save(nuevoUsuario);

        return ResponseEntity.ok("✅ Usuario creado exitosamente con ID: " + nuevoUsuario.getId());
    }

}
