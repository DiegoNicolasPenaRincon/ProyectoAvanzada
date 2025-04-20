package org.uniquindio.proyectofinalavanzada.services.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.uniquindio.proyectofinalavanzada.domain.Rol;
import org.uniquindio.proyectofinalavanzada.domain.Usuario;
import org.uniquindio.proyectofinalavanzada.domain.UsuarioEstado;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.mappers.UsuarioMapper;
import org.uniquindio.proyectofinalavanzada.repositories.UsuarioRepository;
import org.uniquindio.proyectofinalavanzada.services.UsuarioServiceImpl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceUnitTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private UsuarioRegistroDTO usuarioRegistroDTO;
    private UsuarioEditarDTO usuarioEditarDTO;
    private LoginDTO loginDTO;
    private VerificarCodigoDTO verificarCodigoDTO;
    private Usuario usuario;
    private UsuarioResponseDTO usuarioResponseDTO;
    private LoginResponseDTO loginResponseDTO;

    @BeforeEach
    void setUp() {
        // Configuración de datos de prueba
        usuario = new Usuario(
                UUID.randomUUID().toString(),
                "Juan Pérez",
                "Armenia",
                "1234567",
                "Calle 123",
                "juan@example.com",
                "hashed-password",
                null,
                Rol.USER,
                false,
                UsuarioEstado.REGISTRADO
        );

        usuarioRegistroDTO = new UsuarioRegistroDTO(
                UUID.randomUUID().toString(),
                "Juan Pérez",
                "Armenia",
                "1234567",
                "Calle 123",
                "juan@example.com",
                "password123",
                null
        );

        usuarioEditarDTO = new UsuarioEditarDTO(
                "Juan Pérez Modificado",
                "Pereira",
                "7654321",
                "Carrera 456",
                "juan.modificado@example.com",
                "newpassword123",
                null
        );

        loginDTO = new LoginDTO("juan@example.com", "password123");
        verificarCodigoDTO = new VerificarCodigoDTO("juan@example.com", "123456");

        usuarioResponseDTO = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCiudadResidencia(),
                usuario.getTelefono(),
                usuario.getDireccion(),
                usuario.getCorreo(),
                usuario.getUbicacion()
        );

        loginResponseDTO = new LoginResponseDTO("token-simulado", usuarioResponseDTO);
    }

    @Test
    void testRegistrarUsuarioSuccess() throws Exception {
        when(usuarioRepository.existsByCorreoIgnoreCase(usuarioRegistroDTO.correo()))
                .thenReturn(false);
        when(usuarioMapper.toUsuario(usuarioRegistroDTO)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.toUsuarioResponseDTO(usuario)).thenReturn(usuarioResponseDTO);

        UsuarioResponseDTO result = usuarioService.registrarUsuario(usuarioRegistroDTO);

        assertNotNull(result);
        assertEquals(usuarioResponseDTO.id(), result.id());
        assertEquals(usuarioResponseDTO.correo(), result.correo());

        verify(usuarioRepository).existsByCorreoIgnoreCase(usuarioRegistroDTO.correo());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testRegistrarUsuarioThrowsValueConflictExceptionWhenEmailExists() {
        when(usuarioRepository.existsByCorreoIgnoreCase(usuarioRegistroDTO.correo()))
                .thenReturn(true);

        assertThrows(ValueConflictException.class, () ->
                usuarioService.registrarUsuario(usuarioRegistroDTO));

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void testVerificarCuentaSuccess() throws Exception {
        when(usuarioRepository.findByCorreoIgnoreCase(verificarCodigoDTO.correo()))
                .thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.toUsuarioResponseDTO(usuario)).thenReturn(usuarioResponseDTO);

        UsuarioResponseDTO result = usuarioService.verificarCuenta(verificarCodigoDTO);

        assertNotNull(result);
        assertTrue(usuario.isVerificado());

        verify(usuarioRepository).findByCorreoIgnoreCase(verificarCodigoDTO.correo());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testVerificarCuentaThrowsResourceNotFoundException() {
        when(usuarioRepository.findByCorreoIgnoreCase(verificarCodigoDTO.correo()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                usuarioService.verificarCuenta(verificarCodigoDTO));

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void testLoginSuccess() throws Exception {
        Usuario usuarioActivo = new Usuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCiudadResidencia(),
                usuario.getTelefono(),
                usuario.getDireccion(),
                usuario.getCorreo(),
                usuario.getContraseña(),
                usuario.getUbicacion(),
                usuario.getRol(),
                true,
                UsuarioEstado.ACTIVO
        );

        when(usuarioRepository.findByCorreoIgnoreCaseAndContraseña(
                loginDTO.correo(), loginDTO.contraseña()))
                .thenReturn(Optional.of(usuarioActivo));
        when(usuarioMapper.toUsuarioResponseDTO(usuarioActivo)).thenReturn(usuarioResponseDTO);

        LoginResponseDTO result = usuarioService.login(loginDTO);

        assertNotNull(result);
        assertEquals(loginResponseDTO.token(), result.token());

        verify(usuarioRepository).findByCorreoIgnoreCaseAndContraseña(
                loginDTO.correo(), loginDTO.contraseña());
    }

    @Test
    void testLoginThrowsResourceNotFoundException() {
        when(usuarioRepository.findByCorreoIgnoreCaseAndContraseña(
                loginDTO.correo(), loginDTO.contraseña()))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                usuarioService.login(loginDTO));
    }

    @Test
    void testLoginThrowsValueConflictExceptionWhenNotVerified() {
        when(usuarioRepository.findByCorreoIgnoreCaseAndContraseña(
                loginDTO.correo(), loginDTO.contraseña()))
                .thenReturn(Optional.of(usuario));

        assertThrows(ValueConflictException.class, () ->
                usuarioService.login(loginDTO));
    }

    @Test
    void testEditarPerfilSuccess() throws Exception {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.toUsuarioResponseDTO(usuario)).thenReturn(usuarioResponseDTO);

        UsuarioResponseDTO result = usuarioService.editarPerfil(
                usuario.getId(), usuarioEditarDTO);

        assertNotNull(result);
        assertEquals(usuarioEditarDTO.nombre(), usuario.getNombre());
        assertEquals(usuarioEditarDTO.ciudadResidencia(), usuario.getCiudadResidencia());

        verify(usuarioRepository).findById(usuario.getId());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testEliminarPerfilSuccess() throws Exception {
        when(usuarioRepository.existsById(usuario.getId())).thenReturn(true);

        usuarioService.eliminarPerfil(usuario.getId(), true);

        verify(usuarioRepository).deleteById(usuario.getId());
    }

    @Test
    void testEliminarPerfilThrowsValueConflictExceptionWhenNotConfirmed() {
        assertThrows(ValueConflictException.class, () ->
                usuarioService.eliminarPerfil(usuario.getId(), false));

        verify(usuarioRepository, never()).deleteById(any());
    }

    @Test
    void testObtenerUsuarioSuccess() {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toUsuarioResponseDTO(usuario)).thenReturn(usuarioResponseDTO);

        Optional<UsuarioResponseDTO> result = usuarioService.obtenerUsuario(usuario.getId());

        assertTrue(result.isPresent());
        assertEquals(usuarioResponseDTO.id(), result.get().id());
    }

    @Test
    void testObtenerUsuarioNotFound() {
        when(usuarioRepository.findById("id-inexistente")).thenReturn(Optional.empty());

        Optional<UsuarioResponseDTO> result = usuarioService.obtenerUsuario("id-inexistente");

        assertFalse(result.isPresent());
    }
}