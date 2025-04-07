/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.7.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import org.openapitools.model.Comentario;
import org.openapitools.model.ComentarioEditar;
import org.openapitools.model.Reporte;
import org.openapitools.model.ReporteEditar;
import org.openapitools.model.ReportesGet500Response;
import org.openapitools.model.ReportesIdComentariosComentarioIdDelete200Response;
import org.openapitools.model.ReportesIdComentariosComentarioIdDelete403Response;
import org.openapitools.model.ReportesIdComentariosComentarioIdDelete404Response;
import org.openapitools.model.ReportesIdComentariosPost201Response;
import org.openapitools.model.ReportesIdComentariosPost400Response;
import org.openapitools.model.ReportesIdDelete200Response;
import org.openapitools.model.ReportesIdDelete400Response;
import org.openapitools.model.ReportesIdPut200Response;
import org.openapitools.model.ReportesIdPut400Response;
import org.openapitools.model.ReportesIdPut404Response;
import org.openapitools.model.ReportesPost201Response;
import org.openapitools.model.ReportesPost400Response;
import org.openapitools.model.ReportesPost500Response;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-06T23:20:32.835196900-05:00[America/Bogota]", comments = "Generator version: 7.7.0")
@Validated
@Tag(name = "report", description = "the report API")
public interface ReportesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /reportes : Obtener todos los reportes
     * Devuelve la lista de reportes registrados en el sistema.
     *
     * @param categoria  (optional)
     * @param estado  (optional)
     * @return Lista de reportes obtenida con éxito (status code 200)
     *         or Error interno del servidor (status code 500)
     */
    @Operation(
        operationId = "reportesGet",
        summary = "Obtener todos los reportes",
        description = "Devuelve la lista de reportes registrados en el sistema.",
        tags = { "report" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de reportes obtenida con éxito", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Reporte.class)))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesGet500Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/reportes",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<Reporte>> reportesGet(
        @Parameter(name = "categoria", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "categoria", required = false) String categoria,
        @Parameter(name = "estado", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "estado", required = false) String estado
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"descripcion\" : \"descripcion\", \"ubicacion\" : { \"latitud\" : 0.8008281904610115, \"longitud\" : 6.027456183070403 }, \"imagenes\" : [ \"https://openapi-generator.tech\", \"https://openapi-generator.tech\" ], \"categoria\" : \"categoria\", \"titulo\" : \"titulo\", \"id\" : \"id\" }, { \"descripcion\" : \"descripcion\", \"ubicacion\" : { \"latitud\" : 0.8008281904610115, \"longitud\" : 6.027456183070403 }, \"imagenes\" : [ \"https://openapi-generator.tech\", \"https://openapi-generator.tech\" ], \"categoria\" : \"categoria\", \"titulo\" : \"titulo\", \"id\" : \"id\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"error\" : \"Error inesperado en el servidor.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /reportes/{id}/comentarios/{comentarioId} : Eliminar un comentario
     * Permite a un usuario eliminar su comentario o a un administrador eliminar cualquier comentario.
     *
     * @param id ID del reporte donde está el comentario. (required)
     * @param comentarioId ID del comentario a eliminar. (required)
     * @return Comentario eliminado con éxito. (status code 200)
     *         or No autorizado para eliminar el comentario. (status code 403)
     *         or Comentario no encontrado. (status code 404)
     */
    @Operation(
        operationId = "reportesIdComentariosComentarioIdDelete",
        summary = "Eliminar un comentario",
        description = "Permite a un usuario eliminar su comentario o a un administrador eliminar cualquier comentario.",
        tags = { "report" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Comentario eliminado con éxito.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdComentariosComentarioIdDelete200Response.class))
            }),
            @ApiResponse(responseCode = "403", description = "No autorizado para eliminar el comentario.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdComentariosComentarioIdDelete403Response.class))
            }),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdComentariosComentarioIdDelete404Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/reportes/{id}/comentarios/{comentarioId}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<ReportesIdComentariosComentarioIdDelete200Response> reportesIdComentariosComentarioIdDelete(
        @Parameter(name = "id", description = "ID del reporte donde está el comentario.", required = true, in = ParameterIn.PATH) @PathVariable("id") String id,
        @Parameter(name = "comentarioId", description = "ID del comentario a eliminar.", required = true, in = ParameterIn.PATH) @PathVariable("comentarioId") String comentarioId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"mensaje\" : \"Comentario eliminado correctamente.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"error\" : \"No tienes permiso para eliminar este comentario.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"error\" : \"Comentario no encontrado.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /reportes/{id}/comentarios/{comentarioId} : Editar un comentario
     * Permite a un usuario modificar su comentario en un reporte.
     *
     * @param id  (required)
     * @param comentarioId  (required)
     * @param comentarioEditar  (required)
     * @return Comentario actualizado con éxito. (status code 200)
     *         or No autorizado para editar este comentario. (status code 403)
     *         or Comentario no encontrado. (status code 404)
     */
    @Operation(
        operationId = "reportesIdComentariosComentarioIdPut",
        summary = "Editar un comentario",
        description = "Permite a un usuario modificar su comentario en un reporte.",
        tags = { "report" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Comentario actualizado con éxito."),
            @ApiResponse(responseCode = "403", description = "No autorizado para editar este comentario."),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado.")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/reportes/{id}/comentarios/{comentarioId}",
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Void> reportesIdComentariosComentarioIdPut(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") String id,
        @Parameter(name = "comentarioId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("comentarioId") String comentarioId,
        @Parameter(name = "ComentarioEditar", description = "", required = true) @Valid @RequestBody ComentarioEditar comentarioEditar
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /reportes/{id}/comentarios : Obtener comentarios de un reporte
     * Devuelve la lista de comentarios asociados a un reporte.
     *
     * @param id  (required)
     * @return Lista de comentarios obtenida con éxito. (status code 200)
     *         or Reporte no encontrado. (status code 404)
     */
    @Operation(
        operationId = "reportesIdComentariosGet",
        summary = "Obtener comentarios de un reporte",
        description = "Devuelve la lista de comentarios asociados a un reporte.",
        tags = { "report" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de comentarios obtenida con éxito.", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comentario.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado.")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/reportes/{id}/comentarios",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<Comentario>> reportesIdComentariosGet(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") String id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"fecha\" : \"2024-03-10T14:30:00Z\", \"contenido\" : \"Este incidente también afectó mi zona.\", \"usuarioId\" : \"123\" }, { \"fecha\" : \"2024-03-10T14:30:00Z\", \"contenido\" : \"Este incidente también afectó mi zona.\", \"usuarioId\" : \"123\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /reportes/{id}/comentarios : Agregar un comentario a un reporte
     * Permite a los usuarios agregar comentarios en un reporte existente.
     *
     * @param id  (required)
     * @param comentario  (required)
     * @return Comentario agregado con éxito. (status code 201)
     *         or Datos inválidos. (status code 400)
     *         or Reporte no encontrado. (status code 404)
     */
    @Operation(
        operationId = "reportesIdComentariosPost",
        summary = "Agregar un comentario a un reporte",
        description = "Permite a los usuarios agregar comentarios en un reporte existente.",
        tags = { "report" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Comentario agregado con éxito.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdComentariosPost201Response.class))
            }),
            @ApiResponse(responseCode = "400", description = "Datos inválidos.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdComentariosPost400Response.class))
            }),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdPut404Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/reportes/{id}/comentarios",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<ReportesIdComentariosPost201Response> reportesIdComentariosPost(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") String id,
        @Parameter(name = "Comentario", description = "", required = true) @Valid @RequestBody Comentario comentario
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"id\", \"mensaje\" : \"Comentario agregado correctamente.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"error\" : \"El comentario no puede estar vacío.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"error\" : \"Reporte no encontrado.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /reportes/{id} : Eliminar un reporte
     * Permite eliminar un reporte por su ID.
     *
     * @param id  (required)
     * @return Reporte eliminado con éxito. (status code 200)
     *         or Falta confirmación para eliminar el reporte. (status code 400)
     *         or Reporte no encontrado. (status code 404)
     */
    @Operation(
        operationId = "reportesIdDelete",
        summary = "Eliminar un reporte",
        description = "Permite eliminar un reporte por su ID.",
        tags = { "report" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Reporte eliminado con éxito.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdDelete200Response.class))
            }),
            @ApiResponse(responseCode = "400", description = "Falta confirmación para eliminar el reporte.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdDelete400Response.class))
            }),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdPut404Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/reportes/{id}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<ReportesIdDelete200Response> reportesIdDelete(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") String id
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"mensaje\" : \"Reporte eliminado correctamente.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"error\" : \"Debes confirmar la eliminación.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"error\" : \"Reporte no encontrado.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /reportes/{id} : Editar un reporte
     * Permite modificar un reporte existente.
     *
     * @param id  (required)
     * @param reporteEditar  (required)
     * @return Reporte actualizado con éxito. (status code 200)
     *         or Datos inválidos. (status code 400)
     *         or Reporte no encontrado. (status code 404)
     */
    @Operation(
        operationId = "reportesIdPut",
        summary = "Editar un reporte",
        description = "Permite modificar un reporte existente.",
        tags = { "report" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Reporte actualizado con éxito.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdPut200Response.class))
            }),
            @ApiResponse(responseCode = "400", description = "Datos inválidos.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdPut400Response.class))
            }),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesIdPut404Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/reportes/{id}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<ReportesIdPut200Response> reportesIdPut(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") String id,
        @Parameter(name = "ReporteEditar", description = "", required = true) @Valid @RequestBody ReporteEditar reporteEditar
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"mensaje\" : \"Reporte actualizado correctamente.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"error\" : \"Los datos proporcionados no son válidos.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"error\" : \"Reporte no encontrado.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /reportes : Crear un reporte
     * Permite a un usuario reportar un incidente.
     *
     * @param reporte  (required)
     * @return Reporte creado con éxito (status code 201)
     *         or Datos inválidos (status code 400)
     *         or Error interno del servidor (status code 500)
     */
    @Operation(
        operationId = "reportesPost",
        summary = "Crear un reporte",
        description = "Permite a un usuario reportar un incidente.",
        tags = { "report" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Reporte creado con éxito", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesPost201Response.class))
            }),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesPost400Response.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ReportesPost500Response.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/reportes",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<ReportesPost201Response> reportesPost(
        @Parameter(name = "Reporte", description = "", required = true) @Valid @RequestBody Reporte reporte
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"id\" : \"id\", \"mensaje\" : \"Reporte creado y pendiente de verificación.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"error\" : \"Faltan datos obligatorios en el reporte.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"error\" : \"Error inesperado en el servidor. Inténtelo más tarde.\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
