package org.uniquindio.proyectofinalavanzada.services.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.uniquindio.proyectofinalavanzada.domain.Notificacion;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;
import org.uniquindio.proyectofinalavanzada.mappers.NotificacionMapper;
import org.uniquindio.proyectofinalavanzada.repositories.NotificacionRepository;
import org.uniquindio.proyectofinalavanzada.services.NotificacionServiceImpl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceUnitTest {

    @Mock
    private NotificacionRepository notificacionRepository;

    @Mock
    private NotificacionMapper notificacionMapper;

    @InjectMocks
    private NotificacionServiceImpl notificacionService;

    private NotificacionDTO notificacionDTO;
    private Notificacion notificacion;
    private NotificacionResponseDTO notificacionResponseDTO;

    @BeforeEach
    void setUp() {
        notificacionDTO = new NotificacionDTO(
                "507f1f77bcf86cd799439011",
                "507f1f77bcf86cd799439012",
                "Mensaje de prueba"
        );

        notificacion = new Notificacion();
        notificacion.setId("1");
        notificacion.setMensaje("Mensaje de prueba");
        notificacion.setLeida(false);

        notificacionResponseDTO = new NotificacionResponseDTO(
                "1",
                "507f1f77bcf86cd799439011",
                "507f1f77bcf86cd799439012",
                "Mensaje de prueba",
                LocalDateTime.now().toString(),
                "enlace",
                false
        );
    }

    @Test
    void testEnviarNotificacionSuccess() {
        when(notificacionRepository.findNotificacionById(anyString())).thenReturn(Optional.empty());
        when(notificacionMapper.toNotificacion(any(NotificacionDTO.class))).thenReturn(notificacion);
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacion);
        when(notificacionMapper.toNotificacionResponseDTO(any(Notificacion.class))).thenReturn(notificacionResponseDTO);

        NotificacionResponseDTO result = notificacionService.enviarNotificacion(notificacionDTO);

        assertNotNull(result);
        assertEquals("Mensaje de prueba", result.mensaje());
        verify(notificacionRepository).save(any(Notificacion.class));
    }

    @Test
    void testEnviarNotificacionThrowsValueConflictException() {
        when(notificacionRepository.findNotificacionById(anyString())).thenReturn(Optional.of(notificacion));

        assertThrows(ValueConflictException.class, () -> {
            notificacionService.enviarNotificacion(notificacionDTO);
        });
    }

    @Test
    void testListarNotificacionesUsuarioSuccess() {
        when(notificacionRepository.findAllByUsuarioId(any())).thenReturn(List.of(notificacion));
        when(notificacionMapper.toNotificacionResponseDTO(any(Notificacion.class))).thenReturn(notificacionResponseDTO);

        List<NotificacionResponseDTO> result = notificacionService.listarNotificacionesUsuario("507f1f77bcf86cd799439011");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Mensaje de prueba", result.get(0).mensaje());
    }

    @Test
    void testMarcarNotificacionComoLeidaSuccess() throws Exception {
        when(notificacionRepository.findNotificacionById(anyString())).thenReturn(Optional.of(notificacion));

        notificacionService.marcarNotificacionComoLeida("1");

        assertTrue(notificacion.isLeida());
        verify(notificacionRepository).save(notificacion);
    }

    @Test
    void testObtenerNotificacionSuccess() {
        when(notificacionRepository.findById(anyString())).thenReturn(Optional.of(notificacion));
        when(notificacionMapper.toNotificacionResponseDTO(any(Notificacion.class))).thenReturn(notificacionResponseDTO);

        NotificacionResponseDTO result = notificacionService.obtenerNotificacion("1");

        assertNotNull(result);
        assertEquals("1", result.id());
    }
}