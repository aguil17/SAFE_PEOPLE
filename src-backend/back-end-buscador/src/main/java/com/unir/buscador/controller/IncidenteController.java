package com.unir.buscador.controller;

import com.unir.buscador.model.request.CreateIncidenteRequest;
import com.unir.buscador.model.response.CreateIncidenteResponse;
import com.unir.buscador.model.response.GetIncidenteResponse;
import com.unir.buscador.service.IIncidenteService;
import com.unir.buscador.util.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/incidente")
@Slf4j
@Tag(name = "Incidente Controller", description = "Información sobre los incidentes registrados")
public class IncidenteController {

    private final IIncidenteService incidenteService;

    public IncidenteController(IIncidenteService incidenteService) {
        this.incidenteService = incidenteService;
    }

    @GetMapping("/fechaCreacionInicial/{fechaCreacionInicial}/fechaCreacionFinal/{fechaCreacionFinal}")
    @Operation(
            operationId = "incidente-get",
            description = "Operación de lectura para devolver los incidentes",
            summary = "Se devuelve una lista de los incidentes almacenados.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetIncidenteResponse.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetIncidenteResponse.class)),
            description = ResponseMessage.INCIDENTES_NOT_FOUND)
    public ResponseEntity<GetIncidenteResponse> getIncidentes(
            @Parameter(name = "fechaCreacionInicial",
                       description = "fecha de creación inicial", example = "2025-01-01", required = true)
            @PathVariable String fechaCreacionInicial,
            @Parameter(name = "fechaCreacionFinal",
                       description = "fecha de creación final", example = "2025-01-01", required = true)
            @PathVariable String fechaCreacionFinal) {

        var serviceResult = incidenteService.getIncidentes(fechaCreacionInicial,fechaCreacionFinal);

        if (serviceResult.isError() && serviceResult.getCode() == "404")
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(serviceResult);
        }

        return ResponseEntity.status(HttpStatus.OK).body(serviceResult);
    }

    @PostMapping()
    @Operation(
            operationId = "incidente-post",
            description = "Creación de incidente",
            summary = "Creación de incidente.")
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateIncidenteResponse.class)))
    public ResponseEntity<CreateIncidenteResponse> createIncidente(@Valid @RequestBody CreateIncidenteRequest request)
    {
        var createIncidenteResponse = incidenteService.createIncidente(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createIncidenteResponse);
    }
}

