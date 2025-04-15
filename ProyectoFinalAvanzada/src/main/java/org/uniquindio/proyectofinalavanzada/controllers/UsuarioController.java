package org.uniquindio.proyectofinalavanzada.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.services.UsuarioService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/perfil")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PutMapping
    public ResponseEntity<MensajeDTO> editarPerfil(@Valid @RequestBody UsuarioEditarDTO usuarioEditarDTO) throws Exception {
        // En una implementación real, el ID vendría del token de autenticación
        String usuarioId = "usuario-simulado";
        usuarioService.editarPerfil(usuarioId, usuarioEditarDTO);
        return ResponseEntity.ok(new MensajeDTO("Datos actualizados correctamente."));
    }

    @DeleteMapping
    public ResponseEntity<MensajeDTO> eliminarPerfil(@RequestParam boolean confirmar) throws Exception {
        // En una implementación real, el ID vendría del token de autenticación
        String usuarioId = "usuario-simulado";
        usuarioService.eliminarPerfil(usuarioId, confirmar);
        return ResponseEntity.ok(new MensajeDTO("Cuenta eliminada correctamente."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuario(@PathVariable String id) {
        Optional<UsuarioResponseDTO> usuario = usuarioService.obtenerUsuario(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}