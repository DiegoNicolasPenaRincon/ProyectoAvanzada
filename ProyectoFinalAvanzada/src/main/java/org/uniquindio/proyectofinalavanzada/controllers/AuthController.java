package org.uniquindio.proyectofinalavanzada.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.services.UsuarioService;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<MensajeDTO> registrarUsuario(@Valid @RequestBody UsuarioRegistroDTO usuarioRegistroDTO) throws Exception {
        usuarioService.registrarUsuario(usuarioRegistroDTO);
        return ResponseEntity.ok(new MensajeDTO("Usuario registrado con éxito. Verifique su correo electrónico."));
    }

    @PostMapping("/verify")
    public ResponseEntity<MensajeDTO> verificarCuenta(@Valid @RequestBody VerificarCodigoDTO verificarCodigoDTO) throws Exception {
        usuarioService.verificarCuenta(verificarCodigoDTO);
        return ResponseEntity.ok(new MensajeDTO("Cuenta activada con éxito."));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        return ResponseEntity.ok(usuarioService.login(loginDTO));
    }
}