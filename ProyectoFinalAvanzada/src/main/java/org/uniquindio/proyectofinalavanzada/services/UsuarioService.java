package org.uniquindio.proyectofinalavanzada.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.uniquindio.proyectofinalavanzada.dtos.*;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioResponseDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) throws Exception;
    UsuarioResponseDTO verificarCuenta(VerificarCodigoDTO verificarCodigoDTO) throws Exception;
    LoginResponseDTO login(LoginDTO loginDTO) throws Exception;
    UsuarioResponseDTO editarPerfil(String id, UsuarioEditarDTO usuarioEditarDTO) throws Exception;
    void eliminarPerfil(String id, boolean confirmar) throws Exception;
    @PreAuthorize("hasAuthority('ADMIN') or @securityService.isCurrentUser(#id)")
    Optional<UsuarioResponseDTO> obtenerUsuario(String id);
}