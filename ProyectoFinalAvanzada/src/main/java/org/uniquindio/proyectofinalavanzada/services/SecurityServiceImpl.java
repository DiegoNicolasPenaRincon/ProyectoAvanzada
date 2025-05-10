package org.uniquindio.proyectofinalavanzada.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.uniquindio.proyectofinalavanzada.dtos.LoginRequest;
import org.uniquindio.proyectofinalavanzada.dtos.TokenResponse;
import org.uniquindio.proyectofinalavanzada.security.JwtTokenProvider;
import org.uniquindio.proyectofinalavanzada.repositories.UsuarioRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
@Service("securityService")
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    @Value("${jwt.expiry}")
    private long expiry;
    @Override
    public TokenResponse login(LoginRequest request) {
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password())
        );
        final var roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        final var now = Instant.now();
        final var expire = now.plus(expiry, ChronoUnit.MINUTES);
        return new TokenResponse(
                jwtTokenProvider.generateTokenAsString(
                        authentication.getName(),roles,now,expire),
                "Bearer",expire,roles);

    }

    public boolean isCurrentUser(String id) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return usuarioRepository.findById(id)
                .map(user -> user.getCorreo().equals(username))
                .orElse(false);

    }
}