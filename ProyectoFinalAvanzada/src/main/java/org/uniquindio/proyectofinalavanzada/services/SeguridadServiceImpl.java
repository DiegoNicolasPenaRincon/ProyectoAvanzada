package org.uniquindio.proyectofinalavanzada.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.uniquindio.proyectofinalavanzada.Security.JwtTokenProvider;
import org.uniquindio.proyectofinalavanzada.dtos.LoginDTO;
import org.uniquindio.proyectofinalavanzada.dtos.TokenResponse;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service("securityService")
@RequiredArgsConstructor
public class SeguridadServiceImpl implements SeguridadService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.expiry}")
    private long expirado;
    @Override
    public TokenResponse login(LoginDTO request) {
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.nombreUsuario(), request.contraseña())
        );
        final var roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        final var now = Instant.now();
        final var expire = now.plus(expirado, ChronoUnit.MINUTES);
        return new TokenResponse(
                jwtTokenProvider.generateTokenAsString(
                        authentication.getName(),roles,now,expire),
                "Bearer",expire,roles);

    }
}
