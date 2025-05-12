package org.uniquindio.proyectofinalavanzada.dtos;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class DetallerConsumidor implements UserDetails {
    private final Usuario usuario;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(usuario.getRol().toString()));
    }
    @Override
    public String getPassword() {
        return usuario.getContraseña();
    }
    @Override
    public String getUsername() {
        return usuario.getCorreo();
    }
}
