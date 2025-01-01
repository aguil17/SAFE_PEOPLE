package com.unir.buscador.controller;

import com.unir.buscador.model.response.GetIncidenteResponse;
import com.unir.buscador.service.IIncidenteService;
import com.unir.buscador.util.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping()
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
    public ResponseEntity<GetIncidenteResponse> getIncidentes() {

        var response = incidenteService.getIncidentes();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
