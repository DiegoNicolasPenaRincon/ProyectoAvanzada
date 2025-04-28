package org.uniquindio.proyectofinalavanzada.services;

import org.uniquindio.proyectofinalavanzada.dtos.LoginDTO;
import org.uniquindio.proyectofinalavanzada.dtos.TokenResponse;

public interface SeguridadService {
    TokenResponse login(LoginDTO solicitud);
}
