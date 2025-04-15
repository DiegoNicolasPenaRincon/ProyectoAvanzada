package org.uniquindio.proyectofinalavanzada.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniquindio.proyectofinalavanzada.dtos.*;
import org.uniquindio.proyectofinalavanzada.services.CategoriaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) throws Exception {
        CategoriaResponseDTO response = categoriaService.crearCategoria(categoriaDTO);
        return ResponseEntity.created(URI.create("/api/admin/categorias/" + response.id()))
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO> editarCategoria(
            @PathVariable String id,
            @Valid @RequestBody CategoriaDTO categoriaDTO) throws Exception {

        categoriaService.editarCategoria(id, categoriaDTO);
        return ResponseEntity.ok(new MensajeDTO("Categoría editada correctamente."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO> eliminarCategoria(@PathVariable String id) throws Exception {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok(new MensajeDTO("Categoría eliminada correctamente."));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
}