package org.uniquindio.proyectofinalavanzada.data;

import org.uniquindio.proyectofinalavanzada.domain.*;
import org.uniquindio.proyectofinalavanzada.repositories.UsuarioRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TestDataLoader {

    /*public static Map<String, Usuario> loadTestData(
            UsuarioRepository usuarioRepository,
            MongoTemplate mongoTemplate,
            PasswordEncoder passwordEncoder) {

        // Limpiar la base de datos primero
        mongoTemplate.getDb().listCollectionNames()
                .forEach(mongoTemplate::dropCollection);

        // Crear usuarios con contraseñas conocidas para testing
        List<Usuario> usuarios = new ArrayList<>();

        // Usuario 1 - Para pruebas generales
        String password1 = "123456Abc";
        Usuario usuario1 = new Usuario(
                UUID.randomUUID().toString(),
                "Ana López",
                "Armenia",
                "1234567",
                "Calle 123",
                "ana@example.com",
                passwordEncoder.encode(password1),
                null,
                Rol.USER,
                true,
                UsuarioEstado.ACTIVO
        );

        // Usuario 2 - Para pruebas de login
        String password2 = "testPassword123";
        Usuario usuario2 = new Usuario(
                UUID.randomUUID().toString(),
                "Carlos Pérez",
                "Pereira",
                "7654321",
                "Carrera 456",
                "carlos@example.com",
                passwordEncoder.encode(password2),
                null,
                Rol.USER,
                true,
                UsuarioEstado.ACTIVO
        );

        usuarios.add(usuario1);
        usuarios.add(usuario2);

        return usuarioRepository.saveAll(usuarios).stream()
                .collect(Collectors.toMap(Usuario::getId, usuario -> usuario));
    }

     */

    public static Map<String, Usuario> loadTestData(
            UsuarioRepository usuarioRepositorio, MongoTemplate mongoTemplate) {
        return loadTestData(
                List.of(
                        new Usuario(UUID.randomUUID().toString(),"ana@example.com", "{noop}12346Abc",
                                "Ana López",8,27), Rol.USER, UsuarioEstado.ACTIVO),
                        new Usuario(UUID.randomUUID().toString(),"carlos@example.com","{noop}12346Abc"
                                "Carlos Pérez", LocalDate.of(1984,10,28), Rol.USER, UsuarioEstado.ACTIVO),
                        new Usuario(UUID.randomUUID().toString(),"juan@example.com","{noop}12346Abc",
                                "Juan Root", Rol.ADMIN, UsuarioEstado.ACTIVO)
                ),
                usuarioRepositorio,
                mongoTemplate
        );
    }
}