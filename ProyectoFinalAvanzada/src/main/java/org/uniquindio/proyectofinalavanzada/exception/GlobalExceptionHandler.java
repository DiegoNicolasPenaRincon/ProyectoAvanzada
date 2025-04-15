package org.uniquindio.proyectofinalavanzada.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.uniquindio.proyectofinalavanzada.dtos.MensajeDTO;
import org.uniquindio.proyectofinalavanzada.exception.ResourceNotFoundException;
import org.uniquindio.proyectofinalavanzada.exception.ValueConflictException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Maneja errores de validación (@Valid en DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MensajeDTO("Error de validación: " + errors));
    }

    // Maneja ConstraintViolationException (validaciones a nivel de parámetros)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MensajeDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MensajeDTO("Error en parámetros: " + ex.getMessage()));
    }

    // Maneja conflictos (ej: email ya registrado)
    @ExceptionHandler(ValueConflictException.class)
    public ResponseEntity<MensajeDTO> handleValueConflictException(ValueConflictException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new MensajeDTO(ex.getMessage()));
    }

    // Maneja recursos no encontrados
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MensajeDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new MensajeDTO(ex.getMessage()));
    }

    // Maneja errores inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MensajeDTO("Error interno: " + ex.getMessage()));
    }
}