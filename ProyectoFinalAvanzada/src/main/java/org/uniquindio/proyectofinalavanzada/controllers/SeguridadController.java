package org.uniquindio.proyectofinalavanzada.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.uniquindio.proyectofinalavanzada.dtos.LoginDTO;
import org.uniquindio.proyectofinalavanzada.dtos.TokenResponse;
import org.uniquindio.proyectofinalavanzada.services.SeguridadService;

@RestController()
@RequiredArgsConstructor
public class SeguridadController {
    private final SeguridadService securityService;
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @Valid @RequestBody LoginDTO request) {
        return ResponseEntity.ok(securityService.login(request));
    }
}
