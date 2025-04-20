package org.uniquindio.proyectofinalavanzada.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.services.ComentarioService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reportes/{id}/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @PostMapping
    public ResponseEntity<ComentarioResponseDTO> agregarComentario(
            @PathVariable String id,
            @Valid @RequestBody ComentarioDTO comentarioDTO) throws Exception {

        ComentarioResponseDTO response = comentarioService.agregarComentario(id, comentarioDTO);
        return ResponseEntity.created(URI.create("/api/reportes/" + id + "/comentarios/" + response.id()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ComentarioResponseDTO>> listarComentarios(@PathVariable String id) {
        return ResponseEntity.ok(comentarioService.listarComentarios(id));
    }

    @PutMapping("/{comentarioId}")
    public ResponseEntity<MensajeDTO> editarComentario(
            @PathVariable String id,
            @PathVariable String comentarioId,
            @Valid @RequestBody ComentarioDTO comentarioDTO) throws Exception {

        comentarioService.editarComentario(id, comentarioId, comentarioDTO);
        return ResponseEntity.ok(new MensajeDTO("Comentario actualizado con éxito."));
    }

    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<MensajeDTO> eliminarComentario(
            @PathVariable String id,
            @PathVariable String comentarioId) throws Exception {

        comentarioService.eliminarComentario(comentarioId);
        return ResponseEntity.ok(new MensajeDTO("Comentario eliminado correctamente."));
    }
}