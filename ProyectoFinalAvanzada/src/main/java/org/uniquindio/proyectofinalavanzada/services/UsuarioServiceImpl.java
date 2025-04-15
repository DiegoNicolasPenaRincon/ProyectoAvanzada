package org.uniquindio.proyectofinalavanzada.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.mappers.UsuarioMapper;
import org.uniquindio.proyectofinalavanzada.services.UsuarioService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final Map<String, Usuario> usuarioStore = new ConcurrentHashMap<>();
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioResponseDTO registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) throws Exception {
        if (usuarioStore.values().stream().anyMatch(u -> u.getCorreo().equalsIgnoreCase(usuarioRegistroDTO.correo()))) {
            throw new ValueConflictException("El correo ya está registrado");
        }

        Usuario usuario = usuarioMapper.toUsuario(usuarioRegistroDTO);
        usuarioStore.put(usuario.getId(), usuario);

        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO verificarCuenta(VerificarCodigoDTO verificarCodigoDTO) throws Exception {
        Usuario usuario = usuarioStore.values().stream()
                .filter(u -> u.getCorreo().equals(verificarCodigoDTO.correo()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        // En una implementación real, aquí se verificaría el código
        usuario.setVerificado(true);

        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) throws Exception {
        Usuario usuario = usuarioStore.values().stream()
                .filter(u -> u.getCorreo().equals(loginDTO.correo()) &&
                        u.getContraseña().equals(loginDTO.contraseña()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Correo o contraseña incorrectos"));

        if (!usuario.isVerificado()) {
            throw new ValueConflictException("La cuenta no ha sido verificada");
        }

        return new LoginResponseDTO("token-simulado", usuarioMapper.toUsuarioResponseDTO(usuario));
    }

    @Override
    public UsuarioResponseDTO editarPerfil(String id, UsuarioEditarDTO usuarioEditarDTO) throws Exception {
        Usuario usuario = usuarioStore.get(id);
        if (usuario == null) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }

        // Actualizar campos
        if (usuarioEditarDTO.nombre() != null) usuario.setNombre(usuarioEditarDTO.nombre());
        if (usuarioEditarDTO.ciudadResidencia() != null) usuario.setCiudadResidencia(usuarioEditarDTO.ciudadResidencia());
        if (usuarioEditarDTO.telefono() != null) usuario.setTelefono(usuarioEditarDTO.telefono());
        if (usuarioEditarDTO.direccion() != null) usuario.setDireccion(usuarioEditarDTO.direccion());
        if (usuarioEditarDTO.correo() != null) usuario.setCorreo(usuarioEditarDTO.correo());
        if (usuarioEditarDTO.contraseña() != null) usuario.setContraseña(usuarioEditarDTO.contraseña());
        if (usuarioEditarDTO.ubicacion() != null) usuario.setUbicacion(usuarioEditarDTO.ubicacion());

        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    @Override
    public void eliminarPerfil(String id, boolean confirmar) throws Exception {
        if (!confirmar) {
            throw new ValueConflictException("Debes confirmar la eliminación de tu cuenta");
        }

        if (!usuarioStore.containsKey(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }

        usuarioStore.remove(id);
    }

    @Override
    public Optional<UsuarioResponseDTO> obtenerUsuario(String id) {
        return Optional.ofNullable(usuarioStore.get(id))
                .map(usuarioMapper::toUsuarioResponseDTO);
    }
}