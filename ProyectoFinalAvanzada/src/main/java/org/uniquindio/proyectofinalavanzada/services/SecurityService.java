package org.uniquindio.proyectofinalavanzada.services;

import org.uniquindio.proyectofinalavanzada.dtos.LoginRequest;
import org.uniquindio.proyectofinalavanzada.dtos.TokenResponse;

public interface SecurityService {
    TokenResponse login(LoginRequest request);
}