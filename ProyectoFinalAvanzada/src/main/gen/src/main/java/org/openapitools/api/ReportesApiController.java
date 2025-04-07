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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-06T23:20:32.835196900-05:00[America/Bogota]", comments = "Generator version: 7.7.0")
@Controller
@RequestMapping("${openapi.plataformaDeReportesDeSeguridad.base-path:/v1}")
public class ReportesApiController implements ReportesApi {

    private final NativeWebRequest request;

    @Autowired
    public ReportesApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
