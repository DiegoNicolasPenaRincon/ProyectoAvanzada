package org.uniquindio.proyectofinalavanzada.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.uniquindio.proyectofinalavanzada.dtos.LoginRequest;
import org.uniquindio.proyectofinalavanzada.dtos.TokenResponse;
import org.uniquindio.proyectofinalavanzada.services.SecurityService;

@RestController()
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(securityService.login(request));
    }
}